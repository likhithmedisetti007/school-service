package com.school.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.dto.downstream.PushStudentInfoRequest;
import com.school.dto.downstream.StudentInfo;

@FeignClient(name = "student-info-service", path = "/student-info-service")
public interface StudentInfoServiceClient {

	@GetMapping("/getStudentsInfoBySchoolName/{schoolName}")
	public List<StudentInfo> getStudentsInfoBySchoolName(@PathVariable List<String> schoolName);

	@PostMapping("/pushStudentInfo")
	public String pushStudentInfo(@RequestBody List<PushStudentInfoRequest> request);

}