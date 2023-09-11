package com.inditex.product.visibility.search.service;

import java.util.Comparator;
import java.util.List;

import com.inditex.product.visibility.search.domain.Product;

public interface ProductFilterStrategy {

	List<Product> productsWebFilter(List<Product> products, Comparator<? super Product> productComparator);

	List<Product> productsWebFilter();

}
