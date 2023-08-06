package com.inditex.product.visibility.search.adapter.csv.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.inditex.product.visibility.search.adapter.csv.CSVDataLoader;

@Component
public class CSVDataLoaderImpl implements CSVDataLoader{
	
	private Resource productResource;
	
	private Resource sizeResource;
	
	private Resource stockResource;
	
	public CSVDataLoaderImpl(@Qualifier(value = "productCSVResource") Resource productResource, 
			@Qualifier(value = "sizeCSVResource") Resource sizeResource, 
			@Qualifier(value = "stockCSVResource") Resource stockResource) {
		super();
		this.productResource = productResource;
		this.sizeResource = sizeResource;
		this.stockResource = stockResource;
	}

	@Override
	public List<ProductRecord> loadProducts() {
		
		return loadDataRecord(productResource, rec -> {
			return new ProductRecord(Long.valueOf(rec.get(0)), Integer.valueOf(rec.get(1)));
		});
		
	}

	@Override
	public List<SizeRecord> loadSizes() {
		
		return loadDataRecord(sizeResource, rec -> {
			return new SizeRecord(Long.valueOf(rec.get(0)),
					Long.valueOf(rec.get(1)), 
					Boolean.valueOf(rec.get(2)),
					Boolean.valueOf(rec.get(3)));
		});
	}
	
	@Override
	public List<StockRecord> loadStocks() {
		
		return loadDataRecord(stockResource, rec -> {
			return new StockRecord(Long.valueOf(rec.get(0)),
					Integer.valueOf(rec.get(1)));
		});
	}
	
	private <T> List<T> loadDataRecord(Resource resource, Function<CSVRecord,T> mapFunction){
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
				CSVParser csvParser = buildCSVParser(fileReader) ;) {

			return csvParser.getRecords().stream().map(rec -> {
				return mapFunction.apply(rec);
			}).toList();

		} catch (IOException e) {
			throw new RuntimeException("Fail to parse CSV file " + resource.getFilename() + e.getMessage());
		}
	}
	
	private CSVParser buildCSVParser(BufferedReader bufferedReader) throws IOException {
		return new CSVParser(bufferedReader, 
				CSVFormat.Builder.create().setAllowMissingColumnNames(true).setTrim(true).build());
	}

	
	
}
