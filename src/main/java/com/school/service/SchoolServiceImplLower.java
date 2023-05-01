package com.school.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;

@Component
@Profile("lower")
public class SchoolServiceImplLower implements SchoolServiceFacade {

	@Override
	public List<GetSchoolDetailsByNameResponse> getSchoolDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetSchoolDetailsByNameResponse getSchoolDetailsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> pushSchoolDetails(List<PushSchoolDetailsRequest> pushSchoolDetailsRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}