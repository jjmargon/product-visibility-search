package com.inditex.product.visibility.search.adapter.format;

import java.util.List;
import java.util.stream.Collectors;

import com.inditex.product.visibility.search.domain.Product;

public class ProductFormatter {
	
	private static String COMMA_SEPARATOR_CHARACTER = ",";

	public static String format(List<Product> products) {
		return products.stream().map(Product::getId).map(String::valueOf).collect(
				Collectors.joining(COMMA_SEPARATOR_CHARACTER));
	}

}
