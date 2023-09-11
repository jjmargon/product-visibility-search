package com.inditex.product.visibility.search.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.service.ProductFilterStrategy;

@SpringBootTest
class WebFilterProductServiceStrategyIntegrationTest {
	
	@Autowired
	private ProductFilterStrategy productFilterStrategy;

	@Test
	void testProductSearchWithDataSavedInDB() {
		
		// GIVEN the product service as in the application running

		// WHEN invoking the product filter method with the defauly strategy
		List<Product> productsFiltered = productFilterStrategy.productsWebFilter();

		// THEN the List of Ids is the expected one
		List<Long> expectedIds = List.of(5L, 1L, 3L);

		assertEquals(expectedIds, productsFiltered.stream().map(p -> p.getId()).toList());
	}

}
