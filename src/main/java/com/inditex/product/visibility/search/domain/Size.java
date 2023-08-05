package com.inditex.product.visibility.search.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Size {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@NotNull
	private boolean backSoon;
	
	@NotNull
	private boolean special;
	
	
	private Stock stock;
	
	
	/**
	 * Empty default constructor required by JPA
	 */
	public Size() {
		super();
	}

	public Size(Long id, Product product, boolean backSoon, boolean special) {
		super();
		this.id = id;
		this.product = product;
		this.backSoon = backSoon;
		this.special = special;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	

}
