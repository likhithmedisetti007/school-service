package com.school.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;
import com.school.service.SchoolServiceFacade;

@RestController
@RequestMapping("/school")
public class SchoolController {

	private static final Logger LOG = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	SchoolServiceFacade schoolService;

	@GetMapping("/getSchoolDetails")
	public List<GetSchoolDetailsByNameResponse> getSchoolDetails() {
		LOG.info("SchoolController :: getSchoolDetails :: STARTS");

		List<GetSchoolDetailsByNameResponse> response = schoolService.getSchoolDetails();

		LOG.info("SchoolController :: getSchoolDetails :: ENDS");
		return response;
	}

	@GetMapping("/getSchoolDetailsByName/{name}")
	public List<GetSchoolDetailsByNameResponse> getSchoolDetailsByName(@PathVariable String name) {
		LOG.info("SchoolController :: getSchoolDetailsByName :: STARTS");
		LOG.info("School Name: " + name);

		List<GetSchoolDetailsByNameResponse> response = schoolService.getSchoolDetailsByName(name);

		LOG.info("SchoolController :: getSchoolDetailsByName :: ENDS");
		return response;
	}

	@PostMapping("/pushSchoolDetails")
	public String pushSchoolDetails(@RequestBody List<PushSchoolDetailsRequest> requestBody) {
		LOG.info("SchoolController :: pushSchoolDetails :: STARTS");

		try {
			schoolService.pushSchoolDetails(requestBody);
		} catch (Exception e) {
			e.printStackTrace();
			return "Unexpected Issue occured while pushing the record.";
		}

		LOG.info("SchoolController :: pushSchoolDetails :: ENDS");
		return requestBody.size() + " record(s) pushed.";
	}

}