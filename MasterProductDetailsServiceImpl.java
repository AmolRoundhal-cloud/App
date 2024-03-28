package com.ats.emami.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ats.emami.entity.MasterProductDetailsEntity;
import com.ats.emami.entity.MasterSKUDetailsEntity;
import com.ats.emami.exeption.ResourceNotFoundException;
import com.ats.emami.repository.IMasterProductDetailsRepository;
import com.ats.emami.responce.ResponseHandler;
import com.ats.emami.service.MasterProductDetailsService;

@Service
public class MasterProductDetailsServiceImpl implements MasterProductDetailsService {

	@Autowired
	private IMasterProductDetailsRepository masterProductDetailsRepository;

	@Override
	public MasterProductDetailsEntity createMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {
		MasterProductDetailsEntity detailsEntity = masterProductDetailsRepository.save(masterProductDetailsEntity);
		return detailsEntity;
	}

	@Override
	public List<MasterProductDetailsEntity> getAllMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {

		List<MasterProductDetailsEntity> list = masterProductDetailsRepository.findAll();
		return list;
	}
	
	@Override
	public List<MasterProductDetailsEntity> findAllMasterProductDetails(
			MasterProductDetailsEntity masterProductDetailsEntity) {
		List<MasterProductDetailsEntity> list = masterProductDetailsRepository.findAllByStatus();
		return list;
	}


	@Override
	public MasterProductDetailsEntity findMasterProductDetailsById(Integer id) {
		MasterProductDetailsEntity masterProductDetailsEntity = masterProductDetailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MasterProductDetailsEntityId" + id, null, id));
		return masterProductDetailsEntity;
	}

	@Override
	public MasterProductDetailsEntity updateMasterProductDetails(MasterProductDetailsEntity masterProductDetailsEntity,
			Integer id) {

		MasterProductDetailsEntity productDetailsEntity = masterProductDetailsRepository.findById(id).get();

		if (Objects.nonNull(masterProductDetailsEntity.getProductName())
				&& !"".equalsIgnoreCase(masterProductDetailsEntity.getProductName())) {
			productDetailsEntity.setProductName(masterProductDetailsEntity.getProductName());
		}
		if (Objects.nonNull(masterProductDetailsEntity.getProductDescription())
				&& !"".equalsIgnoreCase(masterProductDetailsEntity.getProductDescription())) {
			productDetailsEntity.setProductDescription(masterProductDetailsEntity.getProductDescription());
		}
		if (Objects.nonNull(masterProductDetailsEntity.getProductCdate())
				&& !"".equalsIgnoreCase(masterProductDetailsEntity.getProductCdate())) {
			productDetailsEntity.setProductCdate(masterProductDetailsEntity.getProductCdate());
		}
		if (Objects.nonNull(masterProductDetailsEntity.getProductCtime())
				&& !"".equalsIgnoreCase(masterProductDetailsEntity.getProductCtime())) {
			productDetailsEntity.setProductCtime(masterProductDetailsEntity.getProductCtime());
		}
		if (Objects.nonNull(masterProductDetailsEntity.getProductMdate())
				&& !"".equalsIgnoreCase(masterProductDetailsEntity.getProductMdate())) {
			productDetailsEntity.setProductMdate(masterProductDetailsEntity.getProductMdate());
		}
		if (Objects.nonNull(masterProductDetailsEntity.getProductMtime())
				&& !"".equalsIgnoreCase(masterProductDetailsEntity.getProductMtime())) {
			productDetailsEntity.setProductMtime(masterProductDetailsEntity.getProductMtime());
		}

		if (Objects.nonNull(masterProductDetailsEntity.getProductIsDeleted()) && masterProductDetailsEntity.getProductIsDeleted() != 0) {
			productDetailsEntity.setProductIsDeleted(masterProductDetailsEntity.getProductIsDeleted());
		}

		if (Objects.nonNull(masterProductDetailsEntity.getDispatchSequence()) && masterProductDetailsEntity.getDispatchSequence() != 0) {
			productDetailsEntity.setDispatchSequence(masterProductDetailsEntity.getDispatchSequence());
		}

		return masterProductDetailsRepository.save(productDetailsEntity);
	}

	@Override
	public void isdeleteMasterProductDetails(int id) {

		MasterProductDetailsEntity productDetail=masterProductDetailsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Master Product Details"+id, null, id));
		productDetail.setProductIsDeleted(1);
		masterProductDetailsRepository.save(productDetail);
	}



	@Override
	public List<MasterProductDetailsEntity> searchMasterProductDetails(String query) {
		List<MasterProductDetailsEntity> detailsEntities = masterProductDetailsRepository
				.searchMasterProductDetails(query);

		return detailsEntities;
	}

	@Override
	public Page<MasterProductDetailsEntity> findPagenated(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.masterProductDetailsRepository.findAll(pageable);
	}

	@Override
	public List<MasterProductDetailsEntity> findPagenated1(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<MasterProductDetailsEntity> pagedResult = masterProductDetailsRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<MasterProductDetailsEntity> findMasterProductDetailsWithSortting(String field) {
		List<MasterProductDetailsEntity> detailsEntities = masterProductDetailsRepository
				.findAll(Sort.by(Direction.ASC, field));
		return detailsEntities;
	}

	@Override
	public Page<MasterProductDetailsEntity> nextPriviousMasterProductDetail(int offset, int pageSize) {

		Page<MasterProductDetailsEntity> page=masterProductDetailsRepository.findAll(PageRequest.of(offset, pageSize));
		return page;
	}

	public List<MasterProductDetailsEntity> findAllMasterProductDetails() {
		
		// TODO Auto-generated method stub
		return masterProductDetailsRepository.findAll();
	}

	public MasterProductDetailsEntity updateMasterProductDetails(MasterProductDetailsEntity entity) {
		return masterProductDetailsRepository.save(entity);
	}

	public ResponseEntity<Object> addMasterProductDetails(MasterProductDetailsEntity entity) {
		try {
			List<MasterProductDetailsEntity> list = masterProductDetailsRepository.findByProductName(entity.getProductName());
			if(list.size() == 0) {
				LocalDate currentDate = LocalDate.now();
		        LocalTime currentTime = LocalTime.now();
		        
		        String date = currentDate.toString();
		        String time = currentTime.toString();
		        
		        entity.setProductCdate(date);
		        entity.setProductCtime(time);
		        entity.setProductMdate(date);
		        entity.setProductMtime(time);
		        masterProductDetailsRepository.save(entity);
		        return ResponseHandler.generateResponse("Product Details added sucessfully", HttpStatus.OK, null);				
			}else {
				return ResponseHandler.generateResponse("Product already exists", HttpStatus.ALREADY_REPORTED, null);
			}
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Error occurred at server side", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

}
