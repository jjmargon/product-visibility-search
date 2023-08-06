package com.inditex.product.visibility.search.adapter.csv;

import java.util.List;

import com.inditex.product.visibility.search.adapter.csv.impl.ProductRecord;
import com.inditex.product.visibility.search.adapter.csv.impl.SizeRecord;
import com.inditex.product.visibility.search.adapter.csv.impl.StockRecord;

public interface CSVDataLoader {
	
	public List<ProductRecord> loadProducts();
	
	public List<SizeRecord> loadSizes();
	
	public List<StockRecord> loadStocks();

}
