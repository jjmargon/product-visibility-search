package com.inditex.product.visibility.search.adapter.bbdd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inditex.product.visibility.search.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
