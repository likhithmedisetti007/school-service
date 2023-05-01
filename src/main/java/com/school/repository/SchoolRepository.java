package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.entity.SchoolEntity;

@Repository
@Transactional
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {

	SchoolEntity findByName(String name);

}