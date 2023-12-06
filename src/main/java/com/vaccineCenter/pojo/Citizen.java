package com.vaccineCenter.pojo;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String city;
    private int doses;
    private String vaccinationStatus;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="Citizen_vaccination",
	//joinColumns relation is going to specify the present table identity 
	      joinColumns = {@JoinColumn(name="cid",referencedColumnName ="id")},
	//inverseJoinColumns establish the relation with the aggregate class - foreign key      
	      inverseJoinColumns = {@JoinColumn(name="vid",referencedColumnName ="id")}
			)    
    private Center center;
    @Override
    public String toString() {
        return "Citizen [name=" + name + ", city=" + city + "]";
    }
}

