package com.ats.emami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.emami.entity.ViewInfeedMissionRuntimeDetailsEntity;

public interface ViewInfeedMissionRuntimeDetailsRepository extends JpaRepository<ViewInfeedMissionRuntimeDetailsEntity, Integer>{

	/*
	 * @Query("SELECT m FROM ViewInfeedMissionRuntimeDetailsEntity m WHERE " +
	 * "m.palletCode LIKE CONCAT('%', :query, '%') " +
	 * "OR m.skuCode LIKE CONCAT('%', :query, '%') " +
	 * "OR m.skuName LIKE CONCAT('%', :query, '%') "+
	 * "OR m.productName LIKE CONCAT('%', :query, '%') " +
	 * "OR m.prodOrderNo LIKE CONCAT('%', :query, '%') "+
	 * "OR m.BatchNo LIKE CONCAT('%', :query, '%') " +
	 * "OR m.quantity LIKE CONCAT('%', :query, '%') "+
	 * "OR m.floorName LIKE CONCAT('%', :query, '%') " +
	 * "OR m.areaName LIKE CONCAT('%', :query, '%') "+
	 * "OR m.rackName LIKE CONCAT('%', :query, '%') " +
	 * "OR m.positionName LIKE CONCAT('%', :query, '%') "+
	 * "OR m.missionRuntimeInfeedStatus LIKE CONCAT('%', :query, '%') " +
	 * "OR m.missionRuntimeInfeedStartDate LIKE CONCAT('%', :query, '%') "+
	 * "OR m.missionRuntimeInfeedStartTime LIKE CONCAT('%', :query, '%') " +
	 * "OR m.missionRuntimeInfeedEndDate LIKE CONCAT('%', :query, '%') "+
	 * "OR m.missionRuntimeInfeedEndTime LIKE CONCAT('%', :query, '%') " )
	 * 
	 * List<ViewInfeedMissionRuntimeDetailsEntity>
	 * searchViewInfeedMissionRuntimeDetails(String query);
	 */
	
	
	@Query("SELECT m FROM ViewInfeedMissionRuntimeDetailsEntity m WHERE " +
		    "m.palletCode LIKE CONCAT('%', :palletCodeQuery, '%') " +
		    "OR m.skuCode LIKE CONCAT('%', :skuCodeQuery, '%') " +
		    "OR m.skuName LIKE CONCAT('%', :skuNameQuery, '%') "+
		    "OR m.productName LIKE CONCAT('%', :productNameQuery, '%') " +
	       "OR m.prodOrderNo LIKE CONCAT('%', :prodOrderNoQuery, '%') "+
	       "OR m.batchNo LIKE CONCAT('%', :batchNoQuery, '%') " +
	       "OR m.quantity LIKE CONCAT('%', :quantityQuery, '%') "+
	       "OR m.floorName LIKE CONCAT('%', :floorNameQuery, '%') " +
	       "OR m.areaName LIKE CONCAT('%', :areaNameQuery, '%') "+
	       "OR m.rackName LIKE CONCAT('%', :rackNameQuery, '%') " +
	       "OR m.positionName LIKE CONCAT('%', :positionNameQuery, '%') "+
	       "OR m.missionRuntimeInfeedStatus LIKE CONCAT('%', :missionRuntimeInfeedStatusQuery, '%') " +
	       "OR m.missionRuntimeInfeedStartDate LIKE CONCAT('%', :missionRuntimeInfeedStartDateQuery, '%') "+
	       "OR m.missionRuntimeInfeedStartTime LIKE CONCAT('%', :missionRuntimeInfeedStartTimeQuery, '%') " +
	       "OR m.missionRuntimeInfeedEndDate LIKE CONCAT('%', :missionRuntimeInfeedEndDateQuery, '%') "+ 
		   "OR m.missionRuntimeInfeedEndTime LIKE CONCAT('%', :missionRuntimeInfeedEndTimeQuery, '%') " )

	List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(String palletCodeQuery,
			String skuCodeQuery, String skuNameQuery, String productNameQuery, String prodOrderNoQuery,
			String batchNoQuery, String quantityQuery, String floorNameQuery, String areaNameQuery,
			String rackNameQuery, String positionNameQuery, String missionRuntimeInfeedStatusQuery,
			String missionRuntimeInfeedStartDateQuery, String missionRuntimeInfeedStartTimeQuery,
			String missionRuntimeInfeedEndDateQuery, String missionRuntimeInfeedEndTimeQuery);

	@Query("SELECT m FROM ViewInfeedMissionRuntimeDetailsEntity m WHERE " +
		    "( :missionRuntimeInfeedStartDate is null or :missionRuntimeInfeedEndDate is null "+
			"or (CONVERT(date,m.missionRuntimeInfeedStartDate) >= CONVERT(date,:missionRuntimeInfeedStartDate) "+
		    "AND CONVERT(date,m.missionRuntimeInfeedEndDate) <= CONVERT(date,:missionRuntimeInfeedEndDate))) "+
		    "AND ( :productName is null or m.productName LIKE CONCAT('%', :productName, '%')) "+
		    "AND ( :missionRuntimeInfeedStartTime is null or :missionRuntimeInfeedEndTime is null "+
		    "or (CONVERT(time,m.missionRuntimeInfeedStartTime) >= CONVERT(time,:missionRuntimeInfeedStartTime) "+
		    "AND CONVERT(time,m.missionRuntimeInfeedEndTime) <= CONVERT(time,:missionRuntimeInfeedEndTime))) "+
		    "AND ( :palletCode is null or m.palletCode LIKE CONCAT('%', :palletCode, '%')) "+
		    "AND ( :missionRuntimeInfeedStatus is null or m.missionRuntimeInfeedStatus LIKE CONCAT('%', :missionRuntimeInfeedStatus, '%')) " +
		    "AND ( :skuCode is null or m.skuCode LIKE CONCAT('%', :skuCode, '%')) " +
		    "AND ( :batchNo is null or m.batchNo LIKE CONCAT('%', :batchNo, '%')) " +
		    "AND ( :floorName is null or m.floorName LIKE CONCAT('%', :floorName, '%')) "+
		    "AND ( :areaName is null or m.areaName LIKE CONCAT('%', :areaName, '%')) ")
	List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(String missionRuntimeInfeedStartDate,
			String missionRuntimeInfeedEndDate, String productName, String missionRuntimeInfeedStartTime, String missionRuntimeInfeedEndTime,
			String missionRuntimeInfeedStatus, String skuCode, String batchNo, String palletCode, String floorName, String areaName);


	@Query("SELECT m FROM ViewInfeedMissionRuntimeDetailsEntity m WHERE m.missionRuntimeInfeedStartDate = :currentDate ")
	List<ViewInfeedMissionRuntimeDetailsEntity> fetchAllByCurrentDate(String currentDate);
	
}
