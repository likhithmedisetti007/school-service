package com.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;

@Service
public interface SchoolServiceFacade {

	public List<GetSchoolDetailsByNameResponse> getSchoolDetails();

	public GetSchoolDetailsByNameResponse getSchoolDetailsByName(String name);

	public List<String> pushSchoolDetails(List<PushSchoolDetailsRequest> pushSchoolDetailsRequest);

}