package com.inditex.product.visibility.search.service;

import java.util.List;

import com.inditex.product.visibility.search.domain.Product;

public interface ProductService {
	
	List<Product> productsWebFilter(List<Product> products);
	
	List<Product> productsWebFilter();

}
