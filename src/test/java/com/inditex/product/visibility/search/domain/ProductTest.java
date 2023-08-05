package com.inditex.product.visibility.search.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductTest {
	
	@Test
	void testProductIsNotSearchableIfAllSizesDoesNotHaveStock() {
		
		// GIVEN a Product and a size with 0 stock
		Product product = new Product();
		
		Size sizeWithStock = new Size.Builder(product).stock(new Stock(1)).build();
		Size sizeWithoutStock = new Size.Builder(product).build();
		
		// WHEN adding the sizes to the product
		
		product.addSize(sizeWithStock);
		product.addSize(sizeWithoutStock);
		
		// THEN the product doesn't have stock
		assertFalse(product.hasStock());
	}
	
	@Test
	void testProductIsNotSearchableIfDoesNotHaveSizes() {
		
		// GIVEN a Product without sizes
		Product product = new Product();
		
		// THEN the product doesn't have stock
		assertFalse(product.hasStock());
	}
	
	@Test
	void testProductIsSearchableIfAllSizesHaveStock() {
		
		// GIVEN a Product
		Product product = new Product();
		
		Size sizeWithStock = new Size.Builder(product).stock(new Stock(1)).build();
		Size sizeWithStock2 = new Size.Builder(product).stock(new Stock(1)).build();
		
		// WHEN adding the sizes with stock to the product
		
		product.addSize(sizeWithStock);
		product.addSize(sizeWithStock2);
		
		// THEN the product has stock
		assertTrue(product.hasStock());
	}

}
