package com.inditex.product.visibility.search.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	private Long id;
	
	@NotNull
	private Integer sequence;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Size> sizes = new ArrayList<>();
	
	/**
	 * Empty default constructor required by JPA
	 */
	public Product() {
		super();
	}

	public Product(Long id, Integer sequence) {
		super();
		this.id = id;
		this.sequence = sequence;
	}
	
	public void addSize(Size size) {
		sizes.add(size);
		size.setProduct(this);
	}
	
	public void removeSize(Size size) {
		sizes.remove(size);
		size.setProduct(null);
	}
	
	

}
