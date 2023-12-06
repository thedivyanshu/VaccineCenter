package com.vaccineCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccineCenter.pojo.Citizen;

public interface CitizensRepository extends JpaRepository<Citizen, Integer> {

	List<Citizen> findByCenterCenterName(String centerName);

}
