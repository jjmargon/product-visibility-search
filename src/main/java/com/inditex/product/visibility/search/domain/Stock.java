package com.inditex.product.visibility.search.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Stock {
	
	@NotNull
	@Column(nullable=false)
	private int quantity;
	
	public Stock() {
		super();
	}

	public Stock(int quantity) {
		super();
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}
}
