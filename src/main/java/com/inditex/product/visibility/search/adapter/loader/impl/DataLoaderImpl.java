package com.inditex.product.visibility.search.adapter.loader.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.product.visibility.search.adapter.bbdd.ProductRepository;
import com.inditex.product.visibility.search.adapter.bbdd.SizeRepository;
import com.inditex.product.visibility.search.adapter.csv.CSVDataLoader;
import com.inditex.product.visibility.search.adapter.csv.impl.ProductRecord;
import com.inditex.product.visibility.search.adapter.csv.impl.SizeRecord;
import com.inditex.product.visibility.search.adapter.csv.impl.StockRecord;
import com.inditex.product.visibility.search.adapter.loader.DataLoader;
import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.domain.Size;
import com.inditex.product.visibility.search.domain.Stock;

@Component
public class DataLoaderImpl implements DataLoader, ApplicationListener<ApplicationReadyEvent> {

	private CSVDataLoader csvDataLoader;
	private ProductRepository productRepository;
	private SizeRepository sizeRepository;
	
	public DataLoaderImpl(@Autowired CSVDataLoader csvDataLoader, 
			@Autowired ProductRepository productRepository, @Autowired SizeRepository sizeRepository) {
		super();
		this.csvDataLoader = csvDataLoader;
		this.productRepository = productRepository;
		this.sizeRepository = sizeRepository;
	}
	
	@Override
	@Transactional
	public void initDataLoad() {
		// Reads ProductRecords From CSV and save products in DDBB
		List<ProductRecord> productRecords = csvDataLoader.loadProducts();
		productRecords.stream().map(rec -> new Product(rec.id(), rec.sequence())).forEach(
				product -> productRepository.save(product)
			);
		
		// Reads Size and Stock Records From CSV
		List<SizeRecord> sizeRecords = csvDataLoader.loadSizes();
		List<StockRecord> stockRecords = csvDataLoader.loadStocks();
		
		
		sizeRecords.stream().map(rec -> {
				Optional<Product> product = productRepository.findById(rec.productId());
				Size size = new Size.Builder(product.get()).stock(findStockRecord(rec.id(),stockRecords)).
						id(rec.id()).backSoon(rec.backSoon()).special(rec.isSpecial()).build();
				product.get().addSize(size);
				return size;
			}
			
		).forEach(size -> sizeRepository.save(size));
		
	}
	
	private Stock findStockRecord(Long sizeId, List<StockRecord> stockRecords) {
		Stock stock = null;
		Optional<StockRecord> stockSizeRecord = stockRecords.stream().filter(stockRec -> sizeId.equals(stockRec.sizeId())).findFirst();
		if(stockSizeRecord.isPresent()) {
			stock = new Stock(stockSizeRecord.get().quantity());
		}
		return stock;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		this.initDataLoad();
	}

}
