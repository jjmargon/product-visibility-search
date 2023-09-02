package com.inditex.product.visibility.search.adapter.http;

import org.springframework.http.MediaType;
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
	
	@GetMapping(value="/visibleProducts", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getVisibleProducts() {
		
		return ProductFormatter.format(productService.productsWebFilter());
		
	}

}
