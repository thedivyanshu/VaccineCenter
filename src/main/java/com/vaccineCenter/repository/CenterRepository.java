package com.vaccineCenter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.vaccineCenter.pojo.Center;

public interface CenterRepository extends CrudRepository<Center, Integer> {

	List<Center> findByCenterName(String centerName);

	List<Center> findAll();
}
