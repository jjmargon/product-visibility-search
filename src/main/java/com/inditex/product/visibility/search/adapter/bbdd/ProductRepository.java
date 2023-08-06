package com.inditex.product.visibility.search.adapter.bbdd;

import org.springframework.data.repository.CrudRepository;

import com.inditex.product.visibility.search.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
