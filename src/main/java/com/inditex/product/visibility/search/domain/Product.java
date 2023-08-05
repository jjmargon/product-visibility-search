package com.inditex.product.visibility.search.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	private Long id;
	
	@NotNull
	private Integer sequence;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Size> sizes = new ArrayList<>();
	
	@Transient
	private Map<Boolean,List<Size>> sizesByType = new HashMap<>();
	
	@Transient
	private List<Size> specialSizes = new ArrayList<>();
	
	@Transient
	private List<Size> ordinarySizes = new ArrayList<>();
	
	/**
	 * Empty default constructor required by JPA
	 */
	public Product() {
		super();
		sizesByType.put(Boolean.TRUE, specialSizes);
		sizesByType.put(Boolean.FALSE, ordinarySizes);
	}

	public Product(Long id, Integer sequence) {
		this();
		this.id = id;
		this.sequence = sequence;
	}
	
	public void addSize(Size size) {
		Assert.notNull(size,"Size cannot be null");
		sizes.add(size);
		size.setProduct(this);
	}
	
	public void removeSize(Size size) {
		Assert.notNull(size,"Size cannot be null");
		sizes.remove(size);
		size.setProduct(null);
	}
	
	public boolean isSearchable() {
		return !sizes.isEmpty() && sizes.stream().anyMatch(size -> size.isSearchable());
	}

}
