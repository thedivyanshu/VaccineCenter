package com.vaccineCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccineCenter.pojo.Citizen;
import com.vaccineCenter.repository.CitizensRepository;

@Service
public class CitizensService {

	@Autowired
	CitizensRepository repository;

   	   
   public Citizen saveCitizen(Citizen citizen) {
        return repository.save(citizen);
    }

    
    public Citizen updateCitizen(Citizen citizen) {
        return repository.save(citizen);
    }

    
    public List<Citizen> getAllCitizens() {
        return repository.findAll();
    }

    public void addCitizen(Citizen citizen) {
        repository.save(citizen);
    }
    public Citizen getCitizenById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid citizen Id:" + id));
    }

    
    public void deleteCitizenById(int id) {
        Citizen citizen = getCitizenById(id);
        repository.delete(citizen);
    }		
    
    public Citizen getCitizensCenterByCenterName(String centerName) {
		List<Citizen> citizen = repository.findByCenterCenterName(centerName);	       
        if (!citizen.isEmpty()) {
            return citizen.get(0); 
            
        }
        return null; 
	}
}