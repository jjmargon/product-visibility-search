package com.inditex.product.visibility.search.adapter.format;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.domain.ProductTestUtils;

public class ProductFormatterTest {
	
	@Test
	void testProductFormatter() {
		
		// GIVEN a List of Products
		List<Product> products = List.of(
				ProductTestUtils.product5(), 
				ProductTestUtils.product1(), 
				ProductTestUtils.product3());
		
		// WHEN applying the formatter
		String productsFormatted = ProductFormatter.format(products);
		
		// THEN the result is the Product ids separated by commas
		assertEquals("5,1,3", productsFormatted);
	}
	
	@Test
	void testProductFormatterWithEmptyListOfProducts() {
		
		// GIVEN an empty List of Products
		List<Product> products = Collections.emptyList();
		
		// WHEN applying the formatter
		String productsFormatted = ProductFormatter.format(products);
		
		// THEN the result is an empty String
		assertEquals("", productsFormatted);
	}
	
	@Test
	void testProductFormatterWithListOfSingleProduct() {
		
		// GIVEN an  List of Products with a single element
		List<Product> products = List.of(ProductTestUtils.product5());
		
		// WHEN applying the formatter
		String productsFormatted = ProductFormatter.format(products);
		
		// THEN the result is the id of the Product
		assertEquals("5", productsFormatted);
	}

}
