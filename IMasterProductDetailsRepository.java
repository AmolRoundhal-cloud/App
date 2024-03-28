package com.ats.emami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.emami.entity.MasterProductDetailsEntity;


public interface IMasterProductDetailsRepository extends JpaRepository<MasterProductDetailsEntity, Integer>{


	@Query("SELECT m FROM MasterProductDetailsEntity m WHERE " +
		       "m.productName LIKE CONCAT('%', :query, '%') " +
		       "OR m.productDescription LIKE CONCAT('%', :query, '%') " +
		       "OR m.productCdate LIKE CONCAT('%', :query, '%') "+
		       "OR m.productCtime LIKE CONCAT('%', :query, '%') " )
	List<MasterProductDetailsEntity> searchMasterProductDetails(String query);

	@Query("SELECT m FROM MasterProductDetailsEntity m WHERE " +
		       "m.productName = :productName" )
	List<MasterProductDetailsEntity> findByProductName(String productName);

	@Query("SELECT m FROM MasterProductDetailsEntity m WHERE " +
		       "m.productIsDeleted = 0 " )
	List<MasterProductDetailsEntity> findAllByStatus();
}
