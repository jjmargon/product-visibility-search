package com.inditex.product.visibility.search.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inditex.product.visibility.search.domain.Product;

@Component
public class ProductFilterContext {
	
	private final ProductFilterStrategy strategy;

    public ProductFilterContext(@Autowired ProductFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Product> productsWebFilter(List<Product> products, Comparator<? super Product> productComparator) {
        return strategy.productsWebFilter(products, productComparator);
    }
    
    public List<Product> productsWebFilter() {
    	return strategy.productsWebFilter();
    }

}
