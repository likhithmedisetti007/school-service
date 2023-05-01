package com.school.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.school.client.StudentInfoServiceClient;
import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;
import com.school.dto.downstream.PushStudentInfoRequest;
import com.school.dto.downstream.StudentInfo;
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

	@Autowired
	StudentInfoServiceClient studentInfoClient;

	@Override
	public List<GetSchoolDetailsByNameResponse> getSchoolDetails() {
		LOG.info("SchoolServiceImplLocal :: getSchoolDetails :: STARTS");

		List<SchoolEntity> schoolEntityList = repository.findAll();

		List<String> schoolNames = schoolEntityList.stream().filter(s -> null != s.getName()).map(s -> s.getName())
				.collect(Collectors.collectingAndThen(Collectors.toCollection(HashSet::new), ArrayList::new));

		List<StudentInfo> studentInfoList = studentInfoClient.getStudentsInfoBySchoolName(schoolNames);

		List<GetSchoolDetailsByNameResponse> response = mapper
				.mapSchoolEntityToSchoolDetailsByNameResponse(schoolEntityList);

		if (!CollectionUtils.isEmpty(studentInfoList)) {
			for (GetSchoolDetailsByNameResponse object : response) {
				List<StudentInfo> studentInfoSubList = studentInfoList.stream()
						.filter(s -> null != s.getSchoolName() && s.getSchoolName().equalsIgnoreCase(object.getName()))
						.collect(Collectors.toCollection(ArrayList::new));
				if (!CollectionUtils.isEmpty(studentInfoSubList)) {
					object.setStudentInfo(studentInfoSubList);
				}
			}
		}

		LOG.info("SchoolServiceImplLocal :: getSchoolDetails :: ENDS");
		return response;
	}

	@Override
	public GetSchoolDetailsByNameResponse getSchoolDetailsByName(String name) {
		LOG.info("SchoolServiceImplLocal :: getSchoolDetailsByName :: STARTS");

		SchoolEntity schoolEntity = repository.findByName(name);

		GetSchoolDetailsByNameResponse response = mapper.mapSchoolEntityToSchoolDetailsByNameResponse(schoolEntity);

		List<StudentInfo> studentInfoList = studentInfoClient.getStudentsInfoBySchoolName(Arrays.asList(name));
		response.setStudentInfo(studentInfoList);

		LOG.info("SchoolServiceImplLocal :: getSchoolDetailsByName :: ENDS");
		return response;
	}

	@Override
	public List<String> pushSchoolDetails(List<PushSchoolDetailsRequest> pushSchoolDetailsRequest) {
		LOG.info("SchoolServiceImplLocal :: pushSchoolDetails :: STARTS");

		List<SchoolEntity> schoolEntityList = null;
		List<String> responseMessages = new ArrayList<>();

		if (!CollectionUtils.isEmpty(pushSchoolDetailsRequest)) {
			schoolEntityList = mapper.mapPushSchoolDetailsRequestToSchoolEntity(pushSchoolDetailsRequest);
		}

		if (!CollectionUtils.isEmpty(schoolEntityList)) {
			repository.saveAll(schoolEntityList);

			List<StudentInfo> studentInfo = pushSchoolDetailsRequest.stream()
					.filter(s -> !CollectionUtils.isEmpty(s.getStudentInfo())).flatMap(s -> s.getStudentInfo().stream())
					.collect(Collectors.toCollection(ArrayList::new));

			if (!CollectionUtils.isEmpty(studentInfo)) {
				List<PushStudentInfoRequest> pushStudentInfoRequest = mapper
						.mapStudentInfoToPushStudentInfoRequest(studentInfo);
				String message = studentInfoClient.pushStudentInfo(pushStudentInfoRequest);
				responseMessages.add(message);
			}

			responseMessages.add(schoolEntityList.size() + " record(s) pushed in SCHOOL_DETAILS.");

			LOG.info("SchoolServiceImplLocal :: pushSchoolDetails :: ENDS");
			return responseMessages;
		}

		responseMessages.add("0 record(s) pushed in SCHOOL_DETAILS.");

		LOG.info("SchoolServiceImplLocal :: pushSchoolDetails :: ENDS");
		return responseMessages;
	}

}