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
	
	@NotNull
	private Stock stock;
	
	public static class Builder {
		
		// Required value for builder
		private final Product product;
		
		// Default values for builder
		private boolean backSoon = false;
		private boolean special = false;
		private Stock stock = new Stock(0);
		
		
		public Builder(Product product) {
			this.product = product;
		}
		
		public Builder backSoon(boolean backSoon) {
			this.backSoon = backSoon;
			return this;
		}
		
		public Builder special(boolean special) {
			this.special = special;
			return this;
		}
		
		public Builder stock(Stock stock) {
			this.stock = stock;
			return this;
		}
		
		public Size build() {
			return new Size(this);
		}
	}
	
	/**
	 * Required by JPA
	 */
	protected Size() {
		super();
	}
	
	private Size(Builder builder) {
		product = builder.product;
		backSoon = builder.backSoon;
		special = builder.special;
		stock = builder.stock;
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

}
