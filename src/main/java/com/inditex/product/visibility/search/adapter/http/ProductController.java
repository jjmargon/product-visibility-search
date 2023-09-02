package com.inditex.product.visibility.search.adapter.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.product.visibility.search.adapter.format.ProductFormatter;
import com.inditex.product.visibility.search.service.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value="/products")
	public String getVisibleProducts() {
		
		return ProductFormatter.format(productService.productsWebFilter());
		
	}

}