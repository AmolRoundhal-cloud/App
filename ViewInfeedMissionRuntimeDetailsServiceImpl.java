package com.ats.emami.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.emami.entity.ViewInfeedMissionRuntimeDetailsEntity;
import com.ats.emami.repository.ViewInfeedMissionRuntimeDetailsRepository;
import com.ats.emami.service.ViewInfeedMissionRuntimeDetailsService;


@Service
public class ViewInfeedMissionRuntimeDetailsServiceImpl implements ViewInfeedMissionRuntimeDetailsService{

	@Autowired
	private ViewInfeedMissionRuntimeDetailsRepository viewInfeedMissionRuntimeDetailsRepository;
	
	
	
	         /*                   Current Mission Page                    */
	@Override
	public List<ViewInfeedMissionRuntimeDetailsEntity> gelAllinfeedMissionRuntimeDetailsEntities(
			ViewInfeedMissionRuntimeDetailsEntity viewInfeedMissionRuntimeDetailsEntity) {
		
		
		List<ViewInfeedMissionRuntimeDetailsEntity> list=viewInfeedMissionRuntimeDetailsRepository.findAll();
		
		return list;
	}

				/*                   Infeed Report Page                    */

				/*
				 * @Override public List<ViewInfeedMissionRuntimeDetailsEntity>
				 * searchViewInfeedMissionRuntimeDetails(String query) {
				 * 
				 * List<ViewInfeedMissionRuntimeDetailsEntity>
				 * list=viewInfeedMissionRuntimeDetailsRepository.
				 * searchViewInfeedMissionRuntimeDetails(query); return list; }
				 */
	
	@Override
	public List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(String palletCodeQuery,
			String skuCodeQuery, String skuNameQuery, String productNameQuery, String prodOrderNoQuery,
			String batchNoQuery, String quantityQuery, String floorNameQuery, String areaNameQuery,
			String rackNameQuery, String positionNameQuery, String missionRuntimeInfeedStatusQuery,
			String missionRuntimeInfeedStartDateQuery, String missionRuntimeInfeedStartTimeQuery,
			String missionRuntimeInfeedEndDateQuery, String missionRuntimeInfeedEndTimeQuery) {
		 
		/*List<ViewInfeedMissionRuntimeDetailsEntity> list=viewInfeedMissionRuntimeDetailsRepository.searchViewInfeedMissionRuntimeDetails(palletCodeQuery,
				skuCodeQuery, skuNameQuery, productNameQuery, prodOrderNoQuery, batchNoQuery, quantityQuery, floorNameQuery, areaNameQuery, rackNameQuery,
				positionNameQuery, missionRuntimeInfeedStatusQuery, missionRuntimeInfeedStartDateQuery, missionRuntimeInfeedStartTimeQuery,
				missionRuntimeInfeedEndDateQuery, missionRuntimeInfeedEndTimeQuery);*/
		return null;
	}

	@Override
	public List<ViewInfeedMissionRuntimeDetailsEntity> searchViewInfeedMissionRuntimeDetails(
			String missionRuntimeInfeedStartDate, String missionRuntimeInfeedStartTime,
			String missionRuntimeInfeedEndDate, String missionRuntimeInfeedEndTime, String productName,
			String missionRuntimeInfeedStatus, String skuCode, String batchNo, String palletCode, String floorName,
			String areaName) {
		if(missionRuntimeInfeedStartDate.isEmpty() || "undefined".equals(missionRuntimeInfeedStartDate) || "NA".equals(missionRuntimeInfeedStartDate) || "null".equals(missionRuntimeInfeedStartDate)) {
			missionRuntimeInfeedStartDate=null;
		}
		if(missionRuntimeInfeedStartTime.isEmpty() || "undefined".equals(missionRuntimeInfeedStartTime) || "NA".equals(missionRuntimeInfeedStartTime) || "null".equals(missionRuntimeInfeedStartTime)) {
			missionRuntimeInfeedStartTime=null;
		}
		if(missionRuntimeInfeedEndDate.isEmpty() || "undefined".equals(missionRuntimeInfeedEndDate) || "NA".equals(missionRuntimeInfeedEndDate) || "null".equals(missionRuntimeInfeedEndDate)) {
			missionRuntimeInfeedEndDate=null;
		}
		if(missionRuntimeInfeedEndTime.isEmpty() || "undefined".equals(missionRuntimeInfeedEndTime) || "NA".equals(missionRuntimeInfeedEndTime) || "null".equals(missionRuntimeInfeedEndTime)) {
			missionRuntimeInfeedEndTime=null;
		}
		if(productName.isEmpty() || "undefined".equals(productName) || "NA".equals(productName)) {
			productName=null;
		}
		if(missionRuntimeInfeedStatus.isEmpty() || "undefined".equals(missionRuntimeInfeedStatus) || "NA".equals(missionRuntimeInfeedStatus)) {
			missionRuntimeInfeedStatus=null;
		}
		if(skuCode.isEmpty() || "undefined".equals(skuCode) || "NA".equals(skuCode)) {
			skuCode=null;
		}
		if(batchNo.isEmpty() || "undefined".equals(batchNo) || "NA".equals(batchNo)) {
			batchNo=null;
		}
		if(palletCode.isEmpty() || "undefined".equals(palletCode) || "NA".equals(palletCode)) {
			palletCode=null;
		}
		if(floorName.isEmpty() || "undefined".equals(floorName) || "NA".equals(floorName)) {
			floorName=null;
		}
		if(areaName.isEmpty() || "undefined".equals(areaName) || "NA".equals(areaName)) {
			areaName=null;
		}

		return viewInfeedMissionRuntimeDetailsRepository.searchViewInfeedMissionRuntimeDetails(
				missionRuntimeInfeedStartDate, missionRuntimeInfeedEndDate, productName, missionRuntimeInfeedStartTime, missionRuntimeInfeedEndTime,
				missionRuntimeInfeedStatus,skuCode, batchNo, palletCode, floorName,areaName);
	}

	@Override
	public List<ViewInfeedMissionRuntimeDetailsEntity> fetchAllByCurrentDate() 
	{
		LocalDate today = LocalDate.now();
		//String formattedDate = today.format(DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.US));
		String formattedDate ="21 Jan 2023";
		System.out.println(formattedDate);
		return viewInfeedMissionRuntimeDetailsRepository.fetchAllByCurrentDate(formattedDate);
	}

}
