package com.school.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;
import com.school.entity.SchoolEntity;
import com.school.mapper.SchoolMapper;
import com.school.repository.SchoolRepository;

@Component
@Profile("local")
public class SchoolServiceImplLocal implements SchoolServiceFacade {

	private static final Logger LOG = LoggerFactory.getLogger(SchoolServiceImplLocal.class);

	@Autowired
	SchoolRepository repository;

	@Autowired
	SchoolMapper mapper;

	@Override
	public List<GetSchoolDetailsByNameResponse> getSchoolDetails() {
		LOG.info("SchoolServiceImplLocal :: getSchoolDetails :: STARTS");

		List<SchoolEntity> schoolEntityList = repository.findAll();

		List<GetSchoolDetailsByNameResponse> response = mapper
				.mapSchoolEntityToSchoolDetailsByNameResponse(schoolEntityList);

		LOG.info("SchoolServiceImplLocal :: getSchoolDetails :: ENDS");
		return response;
	}

	@Override
	public List<GetSchoolDetailsByNameResponse> getSchoolDetailsByName(String name) {
		LOG.info("SchoolServiceImplLocal :: getSchoolDetailsByName :: STARTS");

		List<SchoolEntity> schoolEntityList = repository.findByName(name);

		List<GetSchoolDetailsByNameResponse> response = mapper
				.mapSchoolEntityToSchoolDetailsByNameResponse(schoolEntityList);

		LOG.info("SchoolServiceImplLocal :: getSchoolDetailsByName :: ENDS");
		return response;
	}

	@Override
	public void pushSchoolDetails(List<PushSchoolDetailsRequest> pushSchoolDetailsRequest) {
		LOG.info("SchoolServiceImplLocal :: pushSchoolDetails :: STARTS");

		List<SchoolEntity> schoolEntityList = mapper
				.mapPushSchoolDetailsRequestToSchoolEntity(pushSchoolDetailsRequest);

		repository.saveAll(schoolEntityList);

		LOG.info("SchoolServiceImplLocal :: pushSchoolDetails :: ENDS");
	}

}