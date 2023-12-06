package com.vaccineCenter.pojo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Center {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String centerName;
	private String city;
	//check status 
	private String status;
		
	@OneToOne(mappedBy = "center")
	private Citizen citizen;

	@Override
	public String toString() {
	    return "VaccinationCenter [centerName=" + centerName + "]";
	}
	
}
