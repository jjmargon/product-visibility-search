package com.inditex.product.visibility.search.adapter.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.product.visibility.search.adapter.format.ProductFormatter;
import com.inditex.product.visibility.search.service.ProductFilterContext;

@RestController
public class ProductController {
	
	private ProductFilterContext productFilterContext;
	
	public ProductController(ProductFilterContext productFilterStrategy) {
		this.productFilterContext = productFilterStrategy;
	}
	
	@GetMapping(value="/visibleProducts", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getVisibleProducts() {
		
		return ProductFormatter.format(productFilterContext.productsWebFilter());
		
	}

}
