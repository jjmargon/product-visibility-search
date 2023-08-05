package com.inditex.product.visibility.search.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ProductTest {
	
	@Test
	void testProductIsNotSearchableIfAllSizesDoesNotHaveStock() {
		
		// GIVEN a Product and sizes with 0 stock
		Product productWithSizesWithZeroStock = new Product();
		
		Size size1 = new Size.Builder(productWithSizesWithZeroStock).build();
		Size size2 = new Size.Builder(productWithSizesWithZeroStock).build();
		
		// WHEN adding the sizes to the product
		
		productWithSizesWithZeroStock.addSize(size1);
		productWithSizesWithZeroStock.addSize(size2);
		
		// THEN the product doesn't have stock
		assertFalse(productWithSizesWithZeroStock.hasStock());
	}

}
