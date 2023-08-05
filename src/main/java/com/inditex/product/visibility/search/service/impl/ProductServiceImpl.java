package com.inditex.product.visibility.search.service.impl;

import static java.util.Comparator.comparing;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Long> productSearch(List<Product> products) {
		
		return products.stream().filter(product -> product.isSearchable()).
				sorted(comparing(Product::getSequence)).map(p -> p.getId()).toList();
	}

}
