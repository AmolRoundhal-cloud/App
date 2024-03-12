package com.ats.emami.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.emami.entity.InfeedMissionRuntimeDetailsEntity;

//import com.ats.emami.entity.MasterPositionDetailsEntity;
import com.ats.emami.entity.OutfeedMissionRuntimeDetailsEntity;
import com.ats.emami.entity.ViewInfeedMissionRuntimeDetailsEntity;
import com.ats.emami.repository.InfeedMissionRuntimeDetailsRepository;
import com.ats.emami.service.InfeedMissionRuntimeDetailsService;
import com.ats.emami.service.ViewInfeedMissionRuntimeDetailsService;
import com.ats.emami.serviceImpl.InfeedMissionRuntimeDetailsServiceImpl;
//import com.ats.emami.repository.MasterPositionDetailsRepository;



@RestController
@CrossOrigin
@RequestMapping("/infeedmissionruntimedetails")
@EnableCaching
public class InfeedMissionRuntimeDetailsController {



	@Autowired
	public InfeedMissionRuntimeDetailsService infeedMissionRuntimeDetailsService;

	@Autowired
	public InfeedMissionRuntimeDetailsRepository infeedMissionRuntimeDetailsRepository;
	
	@Autowired
	private ViewInfeedMissionRuntimeDetailsService viewInfeedMissionRuntimeDetailsService;

	@GetMapping("/fetchAllInfeedMissionRuntimeDetails")
	public List<InfeedMissionRuntimeDetailsEntity> fetchAllInfeedMissionRuntimeDetails()
	{
		return infeedMissionRuntimeDetailsService.fetchAllInfeedMissionRuntimeDetails();
	}
	
	@GetMapping("fetchAllByCurrentDate")
	public List<ViewInfeedMissionRuntimeDetailsEntity> fetchAllByCurrentDate(){
		return viewInfeedMissionRuntimeDetailsService.fetchAllByCurrentDate();
	}
	
	@GetMapping("/fetchAllInfeedMissionRuntimeDetailsbyId")
	public List<InfeedMissionRuntimeDetailsEntity> fetchAllInfeedMissionRuntimeDetailsbyId()
	{
		return infeedMissionRuntimeDetailsService.fetchAllInfeedMissionRuntimeDetailsbyId();
	}
	
	@GetMapping("/searchInfeedMissionRuntimeDetails")
	public ResponseEntity<List<ViewInfeedMissionRuntimeDetailsEntity>> searchViewInfeedMissionRuntimeDetails(
			@RequestParam("missionRuntimeInfeedStartDate") String missionRuntimeInfeedStartDate,
			@RequestParam("missionRuntimeInfeedStartTime") String missionRuntimeInfeedStartTime,
			@RequestParam("missionRuntimeInfeedEndDate") String missionRuntimeInfeedEndDate,
			@RequestParam("missionRuntimeInfeedEndTime") String missionRuntimeInfeedEndTime,
			@RequestParam("productName") String productName,
			@RequestParam("missionRuntimeInfeedStatus") String missionRuntimeInfeedStatus,
			@RequestParam("skuCode") String skuCode,
			@RequestParam("batchNo") String batchNo,
			@RequestParam("palletCode") String palletCode,
			@RequestParam("floorName") String floorName,
			@RequestParam("areaName") String areaName) {

		List<ViewInfeedMissionRuntimeDetailsEntity> list = viewInfeedMissionRuntimeDetailsService
				.searchViewInfeedMissionRuntimeDetails(missionRuntimeInfeedStartDate,missionRuntimeInfeedStartTime,missionRuntimeInfeedEndDate,
						missionRuntimeInfeedEndTime,productName,missionRuntimeInfeedStatus,skuCode,batchNo,palletCode,floorName,areaName);
		return new ResponseEntity<List<ViewInfeedMissionRuntimeDetailsEntity>>(list, HttpStatus.OK);

		
	}
	
	@GetMapping("/test")
	public String Test(@RequestParam("missionRuntimeInfeedStartDate") String missionRuntimeInfeedStartDate,@RequestParam("foorId") int foorId) {
		System.out.println(missionRuntimeInfeedStartDate);
		System.out.println(foorId);
		return "Amol";
		
	}
}
// @GetMapping("/fetchAllInfeedMissionRuntimeDetails")
// public List<InfeedMissionRuntimeDetailsEntity> fetchAllInfeedMissionRuntimeDetails() {
//    return infeedMissionRuntimeDetailsRepository.findAll();
// }


// ----------------------------------------------------search--------------------------------------------------------
// @GetMapping("/searchViewInfeedMissionRuntimeDetails")
// public ResponseEntity<List<ViewInfeedMissionRuntimeDetailsEntity>> searchViewInfeedMissionRuntimeDetails(
// @RequestParam("palletCodeQuery") String palletCodeQuery,
// @RequestParam("materialCodeQuery") String skuCodeQuery,
// @RequestParam("materialNameQuery") String skuNameQuery,
// @RequestParam("areaQuery") String productNameQuery,
// @RequestParam("floorQuery") String prodOrderNoQuery,
// @RequestParam("loadDateTimeQuery") String batchNoQuery,
// @RequestParam("expiryDateQuery") String quantityQuery,
// @RequestParam("LocationQuery") String floorNameQuery,
// @RequestParam("StatusQuery") String areaNameQuery)
//// @RequestParam("rackNameQuery") String rackNameQuery,
//// @RequestParam("positionNameQuery") String positionNameQuery,
//// @RequestParam("missionRuntimeInfeedStatusQuery") String missionRuntimeInfeedStatusQuery,
//// @RequestParam("") String missionRuntimeInfeedStartDateQuery,
//// @RequestParam("") String missionRuntimeInfeedStartTimeQuery,
//// @RequestParam("missionRuntimeInfeedEndDateQuery") String missionRuntimeInfeedEndDateQuery,
//// @RequestParam("missionRuntimeInfeedEndTimeQuery") String missionRuntimeInfeedEndTimeQuery)
// {
//
// List<ViewInfeedMissionRuntimeDetailsEntity> list = infeedMissionRuntimeDetailsService
// .searchInfeedMissionRuntimeDetails(palletCodeQuery, materialCodeQuery, materialNameQuery, areaQuery,
// floorQuery, loadDateTimeQuery, expiryDateQuery, LocationQuery, StatusQuery);
// return new ResponseEntity<List<ViewInfeedMissionRuntimeDetailsEntity>>(list, HttpStatus.OK);
//
//
// }
//
// -----------------------------------update-----------------------------------------------------------------


// @PutMapping("/updateInfeedMissionRuntimeDetailsDetails")
// public void updateInfeedMissionRuntimeDetails(
// @RequestBody InfeedMissionRuntimeDetailsEntity infeedMissionRuntimeDetailsEntity) {
//
// infeedMissionRuntimeDetailsService.updateInfeedMissionRuntimeDetails(infeedMissionRuntimeDetailsEntity);
//
// }


//
//
//@GetMapping("/fetchInfeedMissionRuntimeDetailsByAllFilters/{status}/{skuCode}/{batchNo}/{palletCode}/{floor}/{area}")
//public List<InfeedMissionRuntimeDetailsEntity> fetchInfeedMissionRuntimeDetailsByAllFilters(
// @PathVariable String status, @PathVariable String skuCode, @PathVariable String batchNo,
// @PathVariable String palletCode, @PathVariable String floor,  @PathVariable String area) {
//
// List<InfeedMissionRuntimeDetailsEntity> fetchInfeedMissionRuntimeDetailsByAllFilters = infeedMissionRuntimeDetailsService
// .findByAllFilters(status, skuCode, batchNo ,palletCode, floor,area);
// return fetchInfeedMissionRuntimeDetailsByAllFilters;
//
//}


// @Override
// public int findTotalOutfeedCount() {
// int dailyOutfeedCount1 = 0;
// LocalDateTime currentDate = LocalDateTime.now();
// LocalDate currentMonthStartDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
// LocalDate nextMonthStartDate = currentMonthStartDate.plusMonths(1);
// LocalDateTime currentMonthDateTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 1, 6, 0,
// 0);
// String currentMonthDateTime1 = currentMonthDateTime.toString();
// String currentMonthDateTime2 = currentMonthDateTime1.replace("T", " ");
// LocalDateTime nextMonthDateTime = LocalDateTime.of(nextMonthStartDate, LocalTime.of(05, 59, 59));
// String nextMonthDateTime1 = nextMonthDateTime.toString();
// String nextMonthDateTime2 = nextMonthDateTime1.replace("T", " ");
// List<InfeedMissionRuntimeDetailsEntity> findBycDateTimeBetween = infeedMissionRuntimeDetailsRepository.findBycDateTimeBetween(currentMonthDateTime2, nextMonthDateTime2);
//
//
// return dailyOutfeedCount1;
//;
// }
//
//}


// @GetMapping("/fetchAllInfeedMissionRuntimeDetails")
// public List<InfeedMissionRuntimeDetailsEntity> findAllEquipmentSensorDetails(){
// try {
// return infeedMissionRuntimeDetailsServiceImpl.findAll();
// }
// catch (Exception e) {
// logger.error("Exception in findAllEqupmentSensorDetails:{}" ,e.getMessage());
// }
// return Collections.emptyList();
// }


// @GetMapping("/fetchAllInfeedMissionRuntimeDetails")
// @GetMapping("/findAllInfeedMissionRuntimetDetails")
// public ResponseEntity<List<InfeedMissionRuntimeDetailsEntity>> fetchAllMasterEquipmentDetails(){
//
// List<InfeedMissionRuntimeDetailsEntity> findAll=InfeedMissionRuntimeDetailsServiceImpl.findAllInfeedMissionRuntimeDetails();
// System.out.println(findAll);
// return new ResponseEntity<>(findAll, HttpStatus.OK);
// @GetMapping("/findAllInfeedMission")
// public List<InfeedMissionRuntimeDetailsEntity> findAllInfeedMissionRuntimeDetails() {
//
// System.out.println("in findAllMasterSKUDetails");
// return infeedMissionRuntimeDetailsServiceImpl.findAllInfeedMissionRuntimeDetails();
//
// }public List<InfeedMissionRuntimeDetailsEntity> fetchAllInfeedMissionRuntimeDetails(){





// @GetMapping("/fetchAllInfeedMissionRuntimeDetails")
// public List<InfeedMissionRuntimeDetailsEntity> fetchAllInfeedMissionRuntimeDetails()
// {
// {
// Date date = new Date();
// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
// String currentDateTime = dateFormat.format(date);
// return infeedMissionRuntimeDetailsRepositoryInstance.findByInfeedMissionCDateTimeBetweenAndInfeedMissionIsDeleted(currentDateTime + " " + "00:00:00",
// currentDateTime + " " + "23:59:59", 0);
//}
