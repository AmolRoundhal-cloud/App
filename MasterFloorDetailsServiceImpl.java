package com.ats.emami.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.emami.entity.MasterFloorDetailsEntity;
import com.ats.emami.repository.IMasterFloorDetailsRepository;
import com.ats.emami.service.MasterFloorDetailsService;

@Service
public class MasterFloorDetailsServiceImpl implements MasterFloorDetailsService {

	@Autowired
	private IMasterFloorDetailsRepository masterFloorDetailsRepository;
	
	@Override
	public List<MasterFloorDetailsEntity> fetchAllMasterFloorDetails() {
		return masterFloorDetailsRepository.findAll();
	}

}
