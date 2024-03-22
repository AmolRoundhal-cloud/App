package com.ats.emami.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.ats.emami.entity.ViewInfeedMissionRuntimeDetailsEntity;
import com.ats.emami.entity.ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity;
import com.ats.emami.repository.IMasterProductDetailsRepository;
import com.ats.emami.repository.ViewOutfeedMissionRuntimeAndMissionRouteDetailsRepository;
import com.ats.emami.service.ViewOutfeedMissionRuntimeAndMissionRouteDetailsService;


@Service
public class ViewOutfeedMissionRuntimeAndMissionRouteDetailsServiceImpl implements ViewOutfeedMissionRuntimeAndMissionRouteDetailsService{

	@Autowired
	private ViewOutfeedMissionRuntimeAndMissionRouteDetailsRepository viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository;
	
	@Autowired
	private IMasterProductDetailsRepository masterProductDetailsRepository;
	
	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> findAllViewOutfeedMissionRuntimeAndMissionRouteDetails() {
		return viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.findAll();
	}
	
	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> getAllViewOutfeedMissionRuntimeAndMissionRouteDetails(
			ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity detailsEntity) {
		
		List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> list=viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.findAll();
		
		return list;
	}
	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> findByMissionRuntimeOutfeedStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
//------------------------------------------------------Current Mission---------------------------------------------------------------------	
	
	
	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> getAllOutfeedMissionRuntimeDetails() 
	{
	List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> allOutfeedMissionRuntimeDetails = viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository
			.getAllOutfeedMissionRuntimeDetails();
		System.out.println("allOutfeedMissionRuntimeDetails"+allOutfeedMissionRuntimeDetails);
		return allOutfeedMissionRuntimeDetails;
		
	}

	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> searchViewOutfeedMissionRuntimeAndMissionRouteDetails(
			String missionRuntimeOutfeedStartDate, String missionRuntimeOutfeedEndDate,
			String missionRuntimeOutfeedStartTime, String missionRuntimeOutfeedEndTime, int productId,
			String missionRuntimeOutfeedStatus, String skuCode, String batchNo, String palletCode, int floorId,
			int areaId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> fetchAllByCurrentDate() {
//		System.out.println("in 1 date-");
//		Date dNow = new Date();
//		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MMM-dd");
//		String startdateString = ft.format(dNow);
//		String endDateString = ft.format(dNow);  
//		String	starttimeString	="06:00:00";        
//		String	startdateTimeString	= startdateString +	" "	+ starttimeString;    
//		String	endTimeString	="05:59:59";        
//		String	enddateTimeString	= endDateString +	" "	+ endTimeString;
//		System.out.println("in  date-"+enddateTimeString);
//		System.out.println("in 1 date-");
//		List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> byMissionRuntimeOutfeedStartDateTimeBetween = viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.fetchAllByCurrentDate(startdateTimeString, enddateTimeString);
//		
//		System.out.println( );
//		System.out.println("byMissionRuntimeInfeedStartDateTimeBetween"+byMissionRuntimeOutfeedStartDateTimeBetween.size());
//		return byMissionRuntimeOutfeedStartDateTimeBetween;
//	}

	
//	@Override
//	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> fetchAllByCurrentDate() throws ParseException {
//		// Get current date
//		LocalDate currentDate = LocalDate.now();
//
//		// Define time range
//		LocalTime startTime = LocalTime.of(6, 0, 0);
//		LocalTime endTime = LocalTime.of(5, 59, 59);
//
//		// Combine date and time to create start and end timestamps
//		LocalDateTime startDateTime = LocalDateTime.of(currentDate, startTime);
//		LocalDateTime endDateTime = LocalDateTime.of(currentDate.plusDays(1), endTime);
//
//		// Filter entities within the specified time range
//		List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> filteredEntities = viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.findAll().stream().filter(entity -> {
//					// Parse LogCDate and LogCTime into LocalDateTime
//					LocalDateTime logDateTime = LocalDateTime.of(
//							LocalDate.parse(entity.getMissionRuntimeOutfeedStartDate(), DateTimeFormatter.ofPattern("dd MMM yyyy")),
//							LocalTime.parse(entity.getMissionRuntimeOutfeedStartTime(), DateTimeFormatter.ofPattern("HH:mm")));
//					// Check if logDateTime is within the specified time range
//					return (logDateTime.isAfter(startDateTime) || logDateTime.equals(startDateTime))
//							&& logDateTime.isBefore(endDateTime);
//				}).collect(Collectors.toList());
//
//		return filteredEntities;
//	}



	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> searchOutfeedMissionRuntimeDetailsFilter(
			String missionRuntimeOutfeedStartDate, String missionRuntimeOutfeedStartTime, String missionRuntimeOutfeedEndDate,
			String missionRuntimeOutfeedEndTime, String productName, String missionRuntimeOutfeedStatus, String skuCode,
			String batchNo, String palletCode, String floorName, String areaName) {
		List<String> filterList = new ArrayList<String>();
		
		List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> list = new ArrayList<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity>();
		
		
		if (!missionRuntimeOutfeedStartDate.equals("NA")) {
			filterList.add("missionRuntimeInfeedStartDate");
		}
		if (!missionRuntimeOutfeedStartTime.equals("NA")) {
			filterList.add("missionRuntimeInfeedStartTime");
		}
		if (!missionRuntimeOutfeedEndDate.equals("NA")) {
			filterList.add("missionRuntimeInfeedEndDate");
		}

		if (!missionRuntimeOutfeedEndTime.equals("NA")) {
			filterList.add("missionRuntimeInfeedEndTime");
		}
		
		if (!productName.equals("NA")) {
			filterList.add("productName");
		}
		if (!missionRuntimeOutfeedStatus.equals("NA")) {
			filterList.add("missionRuntimeInfeedStatus");
		}
		if (!skuCode.equals("NA")) {
			filterList.add("skuCode");
		}

		if (!batchNo.equals("NA")) {
			filterList.add("batchNo");
		}
		if (!palletCode.equals("NA")) {
			filterList.add("palletCode");
		}
		if (!floorName.equals("NA")) {
			filterList.add("floorName");
		}

		if (!areaName.equals("NA")) {
			filterList.add("areaName");
		}

//		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> productNamePred = data -> data.getProductName().equals(productName);
		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> missionRuntimeOutfeedStatusPred = data -> data.getMissionRuntimeOutfeedStatus().equals(missionRuntimeOutfeedStatus);
		
		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> skuCodePred = data -> data.getSkuCode().equals(skuCode);
		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> batchNoPred = data -> data.getBatchNo().equals(batchNo);
		
		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> palletCodePred = data -> data.getPalletCode().equals(palletCode);
		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> floorNamePred = data -> data.getFloorName().equals(floorName);
		Predicate<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> areaNamePred = data -> data.getAreaName().equals(areaName);
		System.out.println("1 missionRuntimeInfeedStartDate-"+missionRuntimeOutfeedStartDate);
		
		if (!(missionRuntimeOutfeedStartDate.equals("NA"))&& !(missionRuntimeOutfeedStartTime.equals("NA"))&& !(missionRuntimeOutfeedEndDate.equals("NA"))&& !(missionRuntimeOutfeedEndTime.equals("NA")))
			{
			// Removing "T" from datatime format
//			String startDateTime = cDateTimeStart.toString().replace("T", " ");
//			String endDateTime = cDateTimeEnd.toString().replace("T", " ");
			String startdateString	=missionRuntimeOutfeedStartDate;      
			String	starttimeString	=missionRuntimeOutfeedStartTime;        
			String	startdateTimeString	= startdateString +	" "	+ starttimeString;
			
			String endDateString	=missionRuntimeOutfeedEndDate;      
			String	endTimeString	=missionRuntimeOutfeedEndTime;        
			String	enddateTimeString	= endDateString +	" "	+ endTimeString;
			System.out.println("startdateTimeString-"+startdateTimeString);
			System.out.println("enddateTimeString-"+enddateTimeString);
			list = viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.fetchAllOutfeedMissionDetailsByCurrentDate(startdateTimeString, enddateTimeString);
		}
		else if (!(missionRuntimeOutfeedStartDate.equals("NA"))&&  !(missionRuntimeOutfeedEndDate.equals("NA")))
			
		{
			
			System.out.println("in else missionRuntimeOutfeedStartDate-"+missionRuntimeOutfeedStartDate);
			String startdateString	=missionRuntimeOutfeedStartDate;       
			String	starttimeString	="00:00:00";        
			String	startdateTimeString	= startdateString +	" "	+ starttimeString;
			
			String endDateString	=missionRuntimeOutfeedEndDate;      
			String	endTimeString	="23:59:59";        
			String	enddateTimeString	= endDateString +	" "	+ endTimeString;
			list = viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.fetchAllOutfeedMissionDetailsByCurrentDate(startdateTimeString, enddateTimeString);
		}
	else 
		{
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MMM-dd");
			String startdateString = ft.format(dNow);
			String endDateString = ft.format(dNow);
//			System.out.println("in else missionRuntimeOutfeedStartDate-"+missionRuntimeOutfeedStartDate);
    
			String	starttimeString	="00:00:00";        
			String	startdateTimeString	= startdateString +	" "	+ starttimeString;
			
    
			String	endTimeString	="23:59:59";        
			String	enddateTimeString	= endDateString +	" "	+ endTimeString;
			list = viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.fetchAllOutfeedMissionDetailsByCurrentDate(startdateTimeString, enddateTimeString);
		}

		if (filterList.size() != 0) {

			for (int i = 0; i < filterList.size(); i++) {

//				 if (filterList.get(i).equals("productName")) {
//					list = list.stream().filter(productNamePred).collect((Collectors.toList()));
//				}
				  if (filterList.get(i).equals("missionRuntimeOutfeedStatus")) {
					list = list.stream().filter(missionRuntimeOutfeedStatusPred).collect((Collectors.toList()));
				}
				 else if (filterList.get(i).equals("skuCode")) {
						list = list.stream().filter(skuCodePred).collect((Collectors.toList()));
					}
				
				 else if (filterList.get(i).equals("batchNo")) {
					list = list.stream().filter(batchNoPred).collect((Collectors.toList()));
				}
				 
				 
				 else if (filterList.get(i).equals("palletCode")) {
						list = list.stream().filter(palletCodePred).collect((Collectors.toList()));
					}
					 else if (filterList.get(i).equals("floorName")) {
							list = list.stream().filter(floorNamePred).collect((Collectors.toList()));
						}
					
					 else if (filterList.get(i).equals("areaName")) {
						list = list.stream().filter(areaNamePred).collect((Collectors.toList()));
					}
			}
		}


		
		if (filterList.size() == 0 && list.size() == 0) {
			list = null;
		}
		return list;
	}

	

	

	@Override
	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> fetchAllOutfeedMissionDetailsByCurrentDate() 
	{
//		LocalDate today = LocalDate.now();
//		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.US));
		
		LocalDateTime date = LocalDate.now().atTime(6,0,0);
		LocalDateTime nextDate = date.plusDays(1);
		String currentDateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
		String nextDateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(nextDate);
		System.out.println(currentDateStr);
		System.out.println(nextDateStr);
//		System.out.println(formattedDate);
		return viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.fetchAllOutfeedMissionDetailsByCurrentDate(currentDateStr, nextDateStr);
	}

//	search/filter
	
	
//
//	@Override
//	public List<ViewOutfeedMissionRuntimeAndMissionRouteDetailsEntity> searchViewOutfeedMissionRuntimeAndMissionRouteDetails(
//			String missionRuntimeOutfeedStartDate, String missionRuntimeOutfeedEndDate,
//			String missionRuntimeOutfeedStartTime, String missionRuntimeOutfeedEndTime, int productId,
//			String missionRuntimeOutfeedStatus, String skuCode, String batchNo,String palletCode, int floorId, int areaId) 
//	{
//		
//		if(missionRuntimeOutfeedStartDate.isEmpty()) {
//			missionRuntimeOutfeedStartDate=null;
//		}
//		if(missionRuntimeOutfeedEndDate.isEmpty()) {
//			missionRuntimeOutfeedEndDate=null;
//		}
//		if(missionRuntimeOutfeedStartTime.isEmpty()) {
//			missionRuntimeOutfeedStartTime=null;
//		}
//		if(missionRuntimeOutfeedEndTime.isEmpty()) {
//			missionRuntimeOutfeedEndTime=null;
//		}
////		if(productName.isEmpty()) {
////			productName=null;
////		}
//		if(missionRuntimeOutfeedStatus.isEmpty()) {
//			missionRuntimeOutfeedStatus=null;
//		}
//		if(skuCode.isEmpty()) {
//			skuCode=null;
//		}
//		if(batchNo.isEmpty()) {
//			batchNo=null;
//		}
//		if(palletCode.isEmpty()) {
//			palletCode=null;
//		}
//		return viewOutfeedMissionRuntimeAndMissionRouteDetailsRepository.searchViewOutfeedMissionRuntimeAndMissionRouteDetails(missionRuntimeOutfeedStartDate,missionRuntimeOutfeedEndDate,
//				missionRuntimeOutfeedStartTime,missionRuntimeOutfeedEndTime,productId,missionRuntimeOutfeedStatus,skuCode,batchNo,palletCode,floorId,areaId);
//		
//	}
//

	

}
