package com.school.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;
import com.school.dto.downstream.PushStudentInfoRequest;
import com.school.dto.downstream.StudentInfo;
import com.school.entity.SchoolEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchoolMapper {

	List<GetSchoolDetailsByNameResponse> mapSchoolEntityToSchoolDetailsByNameResponse(
			List<SchoolEntity> schoolEntityList);

	GetSchoolDetailsByNameResponse mapSchoolEntityToSchoolDetailsByNameResponse(SchoolEntity schoolEntityList);

	List<SchoolEntity> mapPushSchoolDetailsRequestToSchoolEntity(
			List<PushSchoolDetailsRequest> pushSchoolDetailsRequestList);

	List<PushStudentInfoRequest> mapStudentInfoToPushStudentInfoRequest(List<StudentInfo> studentInfo);

}