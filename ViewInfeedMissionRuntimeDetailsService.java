package com.ats.emami.service;

import java.util.List;

import com.ats.emami.entity.ViewInfeedMissionRuntimeDetailsEntity;

public interface ViewInfeedMissionRuntimeDetailsService {
     
	
	//  Current Mission  
	List<ViewInfeedMissionRuntimeDetailsEntity > gelAllinfeedMissionRuntimeDetailsEntities(ViewInfeedMissionRuntimeDetailsEntity viewInfeedMissionRuntimeDetailsEntity);
	
	
	
	//infeed report page
	
	// List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(String query);
	
	List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(String palletCodeQuery,
			String skuCodeQuery, String skuNameQuery, String productNameQuery, String prodOrderNoQuery,
			String batchNoQuery, String quantityQuery, String floorNameQuery, String areaNameQuery,
			String rackNameQuery, String positionNameQuery, String missionRuntimeInfeedStatusQuery,
			String missionRuntimeInfeedStartDateQuery, String missionRuntimeInfeedStartTimeQuery,
			String missionRuntimeInfeedEndDateQuery, String missionRuntimeInfeedEndTimeQuery);



	List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(
			String missionRuntimeInfeedStartDate, String missionRuntimeInfeedStartTime,
			String missionRuntimeInfeedEndDate, String missionRuntimeInfeedEndTime, String productName,
			String missionRuntimeInfeedStatus, String skuCode, String batchNo, String palletCode, String floorName,
			String areaName);



	List<ViewInfeedMissionRuntimeDetailsEntity> fetchAllByCurrentDate();
	
	
	
	
}
