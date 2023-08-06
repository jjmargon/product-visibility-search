package com.inditex.product.visibility.search.adapter.csv.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.inditex.product.visibility.search.adapter.csv.CSVDataLoader;
import com.inditex.product.visibility.search.adapter.csv.impl.CSVDataLoaderImpl;
import com.inditex.product.visibility.search.adapter.csv.impl.ProductRecord;
import com.inditex.product.visibility.search.adapter.csv.impl.SizeRecord;
import com.inditex.product.visibility.search.adapter.csv.impl.StockRecord;

public class CSVDataLoaderImplTest {
	
	@Test
	void testLoadProductRecordCSVFile() throws IOException {
		// GIVEN a Resource to product.csv file
		Resource productResource = new ClassPathResource("csv/product.csv");
		
		// WHEN trying to get the list of product records
		CSVDataLoader dataLoaderFromCSV = new CSVDataLoaderImpl(productResource, null, null);
		List<ProductRecord> products = dataLoaderFromCSV.loadProducts();
		
		// THEN the products list contains the expected ones
		assertEquals(5, products.size());
		assertTrue(products.contains(getProductRecord(1L,10)));
		assertTrue(products.contains(getProductRecord(2L,7)));
		assertTrue(products.contains(getProductRecord(3L,15)));
		assertTrue(products.contains(getProductRecord(4L,13)));
		assertTrue(products.contains(getProductRecord(5L,6)));
	}
	
	@Test
	void testLoadSizeRecordCSVFile() throws IOException {
		// GIVEN a Resource to size.csv file
		Resource sizeResource = new ClassPathResource("csv/size.csv");
		
		// WHEN trying to get the list of products
		CSVDataLoader dataLoaderFromCSV = new CSVDataLoaderImpl(null, sizeResource, null);
		List<SizeRecord> sizes = dataLoaderFromCSV.loadSizes();
		
		// THEN the products list contains the expected ones
		assertEquals(17, sizes.size());
		assertTrue(sizes.contains(getSizeRecord(11L, 1L, true, false)));
		assertTrue(sizes.contains(getSizeRecord(12L, 1L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(13L, 1L, true, false)));
		assertTrue(sizes.contains(getSizeRecord(21L, 2L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(22L, 2L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(23L, 2L, true, true)));
		assertTrue(sizes.contains(getSizeRecord(31L, 3L, true, false)));
		assertTrue(sizes.contains(getSizeRecord(32L, 3L, true, false)));
		assertTrue(sizes.contains(getSizeRecord(33L, 3L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(41L, 4L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(42L, 4L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(43L, 4L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(44L, 4L, true, true)));
		assertTrue(sizes.contains(getSizeRecord(51L, 5L, true, false)));
		assertTrue(sizes.contains(getSizeRecord(52L, 5L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(53L, 5L, false, false)));
		assertTrue(sizes.contains(getSizeRecord(54L, 5L, true, true)));
	}
	
	@Test
	void testLoadStockRecordCSVFile() throws IOException {
		// GIVEN a Resource to stock.csv file
		Resource stockResource = new ClassPathResource("csv/stock.csv");
		
		// WHEN trying to get the list of stock records
		CSVDataLoader dataLoaderFromCSV = new CSVDataLoaderImpl(null, null, stockResource);
		List<StockRecord> stocks = dataLoaderFromCSV.loadStocks();
		
		// THEN the stock record list contains the expected ones
		assertEquals(15, stocks.size());
		assertTrue(stocks.contains(getStockRecord(11L,0)));
		assertTrue(stocks.contains(getStockRecord(12L,0)));
		assertTrue(stocks.contains(getStockRecord(13L,0)));
		assertTrue(stocks.contains(getStockRecord(22L,0)));
		assertTrue(stocks.contains(getStockRecord(31L,10)));
		assertTrue(stocks.contains(getStockRecord(32L,10)));
		assertTrue(stocks.contains(getStockRecord(33L,10)));
		assertTrue(stocks.contains(getStockRecord(41L,0)));
		assertTrue(stocks.contains(getStockRecord(42L,0)));
		assertTrue(stocks.contains(getStockRecord(43L,0)));
		assertTrue(stocks.contains(getStockRecord(44L,10)));
		assertTrue(stocks.contains(getStockRecord(51L,10)));
		assertTrue(stocks.contains(getStockRecord(52L,10)));
		assertTrue(stocks.contains(getStockRecord(53L,10)));
		assertTrue(stocks.contains(getStockRecord(54L,10)));
	}
	
	private StockRecord getStockRecord(Long sizeId, Integer quantity) {
		return new StockRecord(sizeId, quantity);
	}
	
	private ProductRecord getProductRecord(Long id, Integer sequence) {
		return new ProductRecord(id,sequence);
	}
	
	private SizeRecord getSizeRecord(Long id, Long productId, boolean backSoon, boolean special) {
		return new SizeRecord(id, productId, backSoon, special);
	}
	
}
