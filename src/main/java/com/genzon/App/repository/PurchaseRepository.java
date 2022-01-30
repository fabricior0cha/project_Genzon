package com.genzon.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genzon.App.model.PurchaseModel;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long>{
	
	

}
