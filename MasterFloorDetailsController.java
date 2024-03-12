package com.ats.emami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.emami.entity.MasterFloorDetailsEntity;
import com.ats.emami.entity.MasterProductDetailsEntity;
import com.ats.emami.service.MasterFloorDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/mappingFloorDetails")
public class MasterFloorDetailsController {

	@Autowired
	private MasterFloorDetailsService masterFloorDetailsService;
	
	@GetMapping("/fetchAllMasterFloorDetails")
	public ResponseEntity<List<MasterFloorDetailsEntity>> findAllMasterFloorDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {

		List<MasterFloorDetailsEntity> allMasterFloorDetails = masterFloorDetailsService
				.fetchAllMasterFloorDetails();
		return new ResponseEntity<>(allMasterFloorDetails, HttpStatus.OK);

	}
}
