package com.ats.emami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.emami.entity.MasterProductDetailsEntity;
import com.ats.emami.serviceImpl.MasterProductDetailsServiceImpl;

@RestController
@RequestMapping("/masterProductDetails")
@CrossOrigin
public class MasterProductDetailsController {

	@Autowired
	private MasterProductDetailsServiceImpl masterProductDetailsServiceImpl;

	// http://localhost:8989/ats/getAllMasterProductDetails
	@GetMapping("/getAllMasterProductDetails")
	public ResponseEntity<List<MasterProductDetailsEntity>> getAllMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {

		List<MasterProductDetailsEntity> allMasterProductDetails = masterProductDetailsServiceImpl
				.getAllMasterProductDetails(masterProductDetailsEntity);
		return new ResponseEntity<>(allMasterProductDetails, HttpStatus.OK);

	}

	// http://localhost:8080/ats/findAllMasterProductDetails			
	@GetMapping("/findAllMasterProductDetails")
	public ResponseEntity<List<MasterProductDetailsEntity>> findAllMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {

		List<MasterProductDetailsEntity> allMasterProductDetails = masterProductDetailsServiceImpl
				.findAllMasterProductDetails(masterProductDetailsEntity);
		return new ResponseEntity<>(allMasterProductDetails, HttpStatus.OK);

	}



	// http://localhost:8989/ats/getByIdMasterProductDetails/6
	@GetMapping("/getByIdMasterProductDetails/{id}")
	public ResponseEntity<MasterProductDetailsEntity> getByIdMasterProductDetails(@PathVariable int id) {
		MasterProductDetailsEntity masterProductDetails = masterProductDetailsServiceImpl
				.findMasterProductDetailsById(id);
		return new ResponseEntity<>(masterProductDetails, HttpStatus.OK);
	}

	// http://localhost:8989/ats/createMasterProductDetails
	@PostMapping("/createMasterProductDetails")
	public ResponseEntity<MasterProductDetailsEntity> createMasterProductDetails(
			@Validated @RequestBody MasterProductDetailsEntity masterProductDetailsEntity) {
		MasterProductDetailsEntity masterProductDetailsEntity2 = masterProductDetailsServiceImpl
				.createMasterProductDetails(masterProductDetailsEntity);

		return new ResponseEntity<>(masterProductDetailsEntity2, HttpStatus.OK);

	}

	//update

	// http://localhost:8989/ats/updateMasterProductDetails/1002
	@PutMapping("/updateMasterProductDetails/{id}")
	public ResponseEntity<MasterProductDetailsEntity> updateMasterProductDetails(
			@RequestBody MasterProductDetailsEntity entity, @PathVariable int id) {

		MasterProductDetailsEntity masterProductDetailsEntity = masterProductDetailsServiceImpl
				.updateMasterProductDetails(entity, id);
		System.out.println("updateMasterProductDetails id" + id);
		return new ResponseEntity<>(masterProductDetailsEntity, HttpStatus.OK);

	}

	@PutMapping("/updateMasterProductDetails")
	public ResponseEntity<MasterProductDetailsEntity> updateMasterProductDetailsNew(
			@RequestBody MasterProductDetailsEntity entity) {
		MasterProductDetailsEntity masterProductDetailsEntity = masterProductDetailsServiceImpl
				.updateMasterProductDetails(entity);
		return new ResponseEntity<>(masterProductDetailsEntity, HttpStatus.OK);
	}

	@PostMapping("/addMasterProductDetails")
	public ResponseEntity<Object> addMasterProductDetails(
			@RequestBody MasterProductDetailsEntity entity) {
		try {
			ResponseEntity<Object> responseEntity = masterProductDetailsServiceImpl.addMasterProductDetails(entity);
			return responseEntity;
		}
		catch (Exception e) {
		}
		return null;
	}



	//http://localhost:8989/ats/isDeleteMasterProductDetails/1
	@PostMapping("/isDeleteMasterProductDetails/{id}")
	public ResponseEntity<MasterProductDetailsEntity> isDeleteMasterProductDetails(@PathVariable int id){
		masterProductDetailsServiceImpl.isdeleteMasterProductDetails(id);
		return new ResponseEntity<MasterProductDetailsEntity>(HttpStatus.OK);
	}





	// http://localhost:8989/ats/searchMasterProductDetails?query=Pouch    ----------searchBOX based on productName/productDescription/productCdate/productCtime
	@GetMapping("/searchMasterProductDetails")
	public ResponseEntity<List<MasterProductDetailsEntity>> searchMasterProductDetails(
			@RequestParam("query") String query) {

		return ResponseEntity.ok(masterProductDetailsServiceImpl.searchMasterProductDetails(query));

	}

	// http://localhost:8989/ats/findPagenated?pageNo=1&pageSize=5 -----1 to 5   record display
	@GetMapping("/findPagenated")
	public ResponseEntity<List<MasterProductDetailsEntity>> findPagenated(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "productId") String sortBy) {
		List<MasterProductDetailsEntity> list = masterProductDetailsServiceImpl.findPagenated1(pageNo, pageSize,
				sortBy);
		System.out.println(list.size());
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	// http://localhost:8989/ats/getMasterProductDetailsWithSorttings/productId    ----sorted by id/desc/date/time
	@GetMapping("/getMasterProductDetailsWithSorttings/{field}")
	public ResponseEntity<List<MasterProductDetailsEntity>> getMasterProductDetailsWithSorttings(
			@PathVariable String field) {
		List<MasterProductDetailsEntity> list = masterProductDetailsServiceImpl
				.findMasterProductDetailsWithSortting(field);
		System.out.println(list.size());
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	// http://localhost:8989/ats/findNextPriviousMasterProductDetail/0/5 -----1 to 5  record display
	// http://localhost:8989/ats/findNextPriviousMasterProductDetail/1/5 -----6 to10  record display.

	@GetMapping("findNextPriviousMasterProductDetail/{offset}/{pageSize}")
	public ResponseEntity<Page<MasterProductDetailsEntity>> findNextPriviousMasterProductDetail(
			@PathVariable int offset, @PathVariable int pageSize) {

		Page<MasterProductDetailsEntity> page = masterProductDetailsServiceImpl.nextPriviousMasterProductDetail(offset,
				pageSize);

		return new ResponseEntity<>(page, new HttpHeaders(), HttpStatus.OK);

	}

}
