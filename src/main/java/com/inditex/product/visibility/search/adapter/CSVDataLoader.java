package com.inditex.product.visibility.search.adapter;

import java.util.List;

import com.inditex.product.visibility.search.adapter.impl.ProductRecord;
import com.inditex.product.visibility.search.adapter.impl.SizeRecord;
import com.inditex.product.visibility.search.adapter.impl.StockRecord;

public interface CSVDataLoader {
	
	public List<ProductRecord> loadProducts();
	
	public List<SizeRecord> loadSizes();
	
	public List<StockRecord> loadStocks();

}
