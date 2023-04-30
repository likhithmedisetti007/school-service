package com.school.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;

@Component
@Profile("lower")
public class SchoolServiceImplLower implements SchoolServiceFacade {

	private static final Logger LOG = LoggerFactory.getLogger(SchoolServiceImplLower.class);

	@Override
	public List<GetSchoolDetailsByNameResponse> getSchoolDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GetSchoolDetailsByNameResponse> getSchoolDetailsByName(String name) {
		LOG.info("SchoolServiceImplLower :: getSchoolDetailsByName :: STARTS");
		List<GetSchoolDetailsByNameResponse> response = null;

		LOG.info("SchoolServiceImplLower :: getSchoolDetailsByName :: ENDS");
		return response;
	}

	@Override
	public void pushSchoolDetails(List<PushSchoolDetailsRequest> pushSchoolDetailsRequest) {
		// TODO Auto-generated method stub

	}

}