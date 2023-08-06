package com.inditex.product.visibility.search.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	private Long id;
	
	@NotNull
	private Integer sequence;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Size> sizes = new ArrayList<>();
	
	@Transient
	private Set<Size> specialSizes = new HashSet<>();
	
	@Transient
	private Set<Size> ordinarySizes = new HashSet<>();
	
	/**
	 * Empty default constructor required by JPA
	 */
	public Product() {
		super();
	}

	public Product(Long id, Integer sequence) {
		this();
		this.id = id;
		this.sequence = sequence;
	}
	
	public void addSize(Size size) {
		Assert.notNull(size,"Size cannot be null");
		if(size.isSpecial()) {
			specialSizes.add(size);
		} else {
			ordinarySizes.add(size);
		}
		sizes.add(size);
		size.setProduct(this);
	}
	
	public void removeSize(Size size) {
		Assert.notNull(size,"Size cannot be null");
		if(size.isSpecial()) {
			specialSizes.remove(size);
		} else {
			ordinarySizes.remove(size);
		}
		sizes.remove(size);
		size.setProduct(null);
	}
	
	public boolean isSearchable() {
		return !sizes.isEmpty() && 
				(CollectionUtils.isEmpty(specialSizes) || specialSizes.stream().anyMatch(size -> size.isSearchable())) &&
				ordinarySizes.stream().anyMatch(size -> size.isSearchable());
	}

	public Long getId() {
		return id;
	}

	public Integer getSequence() {
		return sequence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sequence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id) && Objects.equals(sequence, other.sequence);
	}
	
}
