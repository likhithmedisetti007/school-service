package com.school.dto;

import java.util.List;

import com.school.dto.downstream.StudentInfo;

import lombok.Data;

@Data
public class PushSchoolDetailsRequest {

	private String name;
	private String location;
	private List<StudentInfo> studentInfo;

}