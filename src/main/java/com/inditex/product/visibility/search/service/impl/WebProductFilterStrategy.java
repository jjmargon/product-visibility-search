package com.inditex.product.visibility.search.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.product.visibility.search.adapter.bbdd.ProductRepository;
import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.service.ProductFilterStrategy;

@Service
public class WebProductFilterStrategy implements ProductFilterStrategy {

private ProductRepository productRepository;
	
	public WebProductFilterStrategy(@Autowired ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> productsWebFilter(List<Product> products, Comparator<? super Product> productComparator) {
		
		return products.stream().filter(product -> product.isSearchable()).
				sorted(productComparator).toList();
	}

	@Override
	@Transactional
	public List<Product> productsWebFilter() {
		return productsWebFilter(productRepository.findAll(), (product1, product2) -> product1.getSequence().compareTo(product2.getSequence()));
	}

}
