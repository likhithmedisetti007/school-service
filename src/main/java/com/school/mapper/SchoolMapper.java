package com.school.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.school.dto.GetSchoolDetailsByNameResponse;
import com.school.dto.PushSchoolDetailsRequest;
import com.school.entity.SchoolEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchoolMapper {

	List<GetSchoolDetailsByNameResponse> mapSchoolEntityToSchoolDetailsByNameResponse(
			List<SchoolEntity> schoolEntityList);

	List<SchoolEntity> mapPushSchoolDetailsRequestToSchoolEntity(
			List<PushSchoolDetailsRequest> pushSchoolDetailsRequestList);

}