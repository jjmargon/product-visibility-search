package com.inditex.product.visibility.search.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Stock {
	
	@NotNull
	@Column(nullable=false)
	private Integer quantity;
	
}
