package com.inditex.product.visibility.search.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.inditex.product.visibility.search.domain.Product;
import com.inditex.product.visibility.search.domain.Size;
import com.inditex.product.visibility.search.domain.Stock;

public class ProductServiceImplTest {
	
	@Test
	void testProductsSearchable() {
		
		// GIVEN a List of Products with data as in the CSV files
		List<Product> products = getProducts();
		
		// WHEN invoking the search filter service
		ProductServiceImpl productService = new ProductServiceImpl(null);
		List<Long> productIds = productService.productSearch(products);
		
		// THEN the List of Ids is the expected one
		List<Long> expectedIds = List.of(5L, 1L, 3L);
		
		assertEquals(expectedIds, productIds);
		
	}

	private List<Product> getProducts() {
		Product product1 = new Product(1L,10);
		Size size11 = new Size.Builder(product1).id(11L).backSoon(true).special(false).stock(new Stock(0)).build();
		Size size12 = new Size.Builder(product1).id(12L).backSoon(false).special(false).stock(new Stock(0)).build();
		Size size13 = new Size.Builder(product1).id(13L).backSoon(true).special(false).stock(new Stock(0)).build();
		product1.addSize(size11);
		product1.addSize(size12);
		product1.addSize(size13);
		
		Product product2 = new Product(2L,7);
		Size size21 = new Size.Builder(product2).id(21L).backSoon(false).special(false).build();
		Size size22 = new Size.Builder(product2).id(22L).backSoon(false).special(false).stock(new Stock(0)).build();
		Size size23 = new Size.Builder(product2).id(23L).backSoon(true).special(true).build();
		product2.addSize(size21);
		product2.addSize(size22);
		product2.addSize(size23);
		
		Product product3 = new Product(3L,15);
		Size size31 = new Size.Builder(product3).id(31L).backSoon(true).special(false).stock(new Stock(10)).build();
		Size size32 = new Size.Builder(product3).id(32L).backSoon(true).special(false).stock(new Stock(10)).build();
		Size size33 = new Size.Builder(product3).id(33L).backSoon(false).special(true).stock(new Stock(10)).build();
		product3.addSize(size31);
		product3.addSize(size32);
		product3.addSize(size33);
		
		Product product4 = new Product(4L,13);
		Size size41 = new Size.Builder(product4).id(41L).backSoon(false).special(false).stock(new Stock(0)).build();
		Size size42 = new Size.Builder(product4).id(42L).backSoon(false).special(false).stock(new Stock(0)).build();
		Size size43 = new Size.Builder(product4).id(43L).backSoon(false).special(false).stock(new Stock(0)).build();
		Size size44 = new Size.Builder(product4).id(44L).backSoon(true).special(true).stock(new Stock(10)).build();
		product4.addSize(size41);
		product4.addSize(size42);
		product4.addSize(size43);
		product4.addSize(size44);
		
		Product product5 = new Product(5L,6);
		Size size51 = new Size.Builder(product5).id(51L).backSoon(true).special(false).stock(new Stock(10)).build();
		Size size52 = new Size.Builder(product5).id(52L).backSoon(false).special(false).stock(new Stock(10)).build();
		Size size53 = new Size.Builder(product5).id(53L).backSoon(false).special(false).stock(new Stock(10)).build();
		Size size54 = new Size.Builder(product5).id(54L).backSoon(true).special(true).stock(new Stock(10)).build();
		product5.addSize(size51);
		product5.addSize(size52);
		product5.addSize(size53);
		product5.addSize(size54);
		
		return List.of(product1, product2, product3, product4, product5);
	} 

}
