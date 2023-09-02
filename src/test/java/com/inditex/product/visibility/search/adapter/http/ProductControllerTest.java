package com.inditex.product.visibility.search.adapter.http;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.inditex.product.visibility.search.domain.ProductTestUtils;
import com.inditex.product.visibility.search.service.ProductService;

@SpringBootTest
public class ProductControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ProductService productService;
	
	@BeforeEach
	void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		when(productService.productsWebFilter()).thenReturn(ProductTestUtils.getVisibleProducts());
	}
	
	@Test
	void testProductController() throws Exception {
		
		// GIVEN the /visibleProducts URL 
		// WHEN invoking the GET method
		// THEN the String "5,1,3" should be returned
		
		mockMvc.perform(get("/visibleProducts") 
		           .accept(MediaType.TEXT_PLAIN_VALUE))
		           .andExpect(status().isOk())
		           .andExpect(content().string("5,1,3"));
		
	}

}
