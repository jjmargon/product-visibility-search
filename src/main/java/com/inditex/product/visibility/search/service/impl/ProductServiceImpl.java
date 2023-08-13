package com.inditex.product.visibility.search.service.impl;

import static java.util.Comparator.comparing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.product.visibility.search.adapter.bbdd.ProductRepository;
import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	public ProductServiceImpl(@Autowired ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> productsWebFilter(List<Product> products) {
		
		return products.stream().filter(product -> product.isSearchable()).
				sorted(comparing(Product::getSequence)).toList();
	}

	@Override
	@Transactional
	public List<Product> productsWebFilter() {
		return productsWebFilter(productRepository.findAll());
	}

}
