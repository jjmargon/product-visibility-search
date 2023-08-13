package com.inditex.product.visibility.search.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.domain.ProductTestUtils;

public class ProductServiceImplTest {
	
	@Test
	void testProductsSearchable() {
		
		// GIVEN a List of Products with data as in the CSV files
		List<Product> products = getProducts();
		
		// WHEN invoking the search filter service
		ProductServiceImpl productService = new ProductServiceImpl(null);
		List<Product> productsFiltered = productService.productsWebFilter(products);
		
		// THEN the List of Ids is the expected one
		List<Product> expectedIds = List.of(ProductTestUtils.product5(),
				ProductTestUtils.product1(), 
				ProductTestUtils.product3());
		
		assertEquals(expectedIds, productsFiltered);
		
	}

	private List<Product> getProducts() {
		Product product1 = ProductTestUtils.product1();

		Product product2 = ProductTestUtils.product2();

		Product product3 = ProductTestUtils.product3();

		Product product4 = ProductTestUtils.product4();

		Product product5 = ProductTestUtils.product5();

		return List.of(product1, product2, product3, product4, product5);
	}

	 

}
