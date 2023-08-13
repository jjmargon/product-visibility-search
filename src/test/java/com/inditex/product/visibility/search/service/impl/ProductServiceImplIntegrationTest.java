package com.inditex.product.visibility.search.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.product.visibility.search.service.ProductService;

@SpringBootTest
class ProductServiceImplIntegrationTest {
	
	@Autowired
	private ProductService productService;

	@Test
	void testProductSearchWithDataSavedInDB() {
		
		// GIVEN the product service as in the application running with Spring

		// WHEN invoking the product search method
		List<Long> productIds = productService.productsWebFilter();

		// THEN the List of Ids is the expected one
		List<Long> expectedIds = List.of(5L, 1L, 3L);

		assertEquals(expectedIds, productIds);
	}

}
