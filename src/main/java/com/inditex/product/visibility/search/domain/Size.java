package com.inditex.product.visibility.search.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="size")
public class Size {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@NotNull
	@Column(name="back_soon")
	private boolean backSoon;
	
	@NotNull
	private boolean special;
	
	private Stock stock;
	
	public static class Builder {
		
		// Required value for builder
		private final Product product;
		
		// Default values for builder
		private Long id;
		private boolean backSoon = false;
		private boolean special = false;
		private Stock stock = null;
		
		
		public Builder(Product product) {
			this.product = product;
		}
		
		public Builder id(Long id) {
			this.id = id;
			return this;
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
		id = builder.id;
	}

	public Product getProduct() {
		return product;
	}
	
	protected void setProduct(Product product) {
		this.product = product;
	}

	public Stock getStock() {
		return stock;
	}
	
	public boolean isBackSoon() {
		return backSoon;
	}
	
	public boolean isSpecial() {
		return special;
	}

	public boolean hasStock() {
		boolean hasStock = false;
		if(stock != null) {
			hasStock = stock.getQuantity() > 0;
		}
		return hasStock;
	}
	
	public boolean isSearchable() {
		return backSoon || hasStock();
	}

}
