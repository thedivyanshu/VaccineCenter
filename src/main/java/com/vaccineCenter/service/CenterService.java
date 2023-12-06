package com.vaccineCenter.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccineCenter.pojo.Center;
import com.vaccineCenter.repository.CenterRepository;

@Service
public class CenterService {

	@Autowired
	CenterRepository repository;


	public void AddCenter(Center center){
		repository.save(center);
	}
	public Center getCenterByName(String centerName) {
		List<Center> centers = repository.findByCenterName(centerName);       
		if (!centers.isEmpty()) {
			return centers.get(0); // Returning the first vaccination center from the list

		}
		return null; // no vaccination center found.
	}

	public List<Center> getAllCenters() {
		return repository.findAll(); // Retrieve all centers
	}


	public Center getbyid(int id) {		

		Optional<Center> optionalCenter = repository.findById(id);
		return optionalCenter.orElse(null);

	}
	public void deletebyid(int id) {

		repository.deleteById(id);
	}
	public void updateCenter(Center center) {
		repository.save(center);
	}
}