package com.inditex.product.visibility.search.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ResourcesConfiguration {
	
	@Bean(name = "productCSVResource")
	public Resource getProductCSVResource() {
		return new ClassPathResource("csv/product.csv");
	}
	
	@Bean(name = "sizeCSVResource")
	public Resource getSizeCSVResource() {
		return new ClassPathResource("csv/size.csv");
	}
	
	@Bean(name = "stockCSVResource")
	public Resource getStockCSVResource() {
		return new ClassPathResource("csv/stock.csv");
	}

}
