package com.genzon.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genzon.App.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

	List<ProductModel> findAllByProductContainingIgnoreCase(String product);
}
