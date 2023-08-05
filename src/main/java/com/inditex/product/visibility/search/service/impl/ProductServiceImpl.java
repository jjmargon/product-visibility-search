package com.inditex.product.visibility.search.service.impl;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Long> productSearch(List<Product> products) {
		throw new NotImplementedException();
	}

}
