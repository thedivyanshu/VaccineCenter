package com.vaccineCenter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vaccineCenter.pojo.Center;
import com.vaccineCenter.pojo.Citizen;
import com.vaccineCenter.service.CenterService;
import com.vaccineCenter.service.CitizensService;
@Controller
@RequestMapping("/user/citizen")
public class CitizenController {
	@Autowired
	CitizensService citizenService;
	
	@Autowired
	CenterService vaccineService;
	
	@RequestMapping("/addCitizen")
	public ModelAndView AddNewCitizen(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		List<Center> vaccineCenters = vaccineService.getAllCenters();
		List<Center> inactiveCenters = new ArrayList<>();
	    for (Center center : vaccineCenters) {
	        String status = center.getStatus();
	        if (status == null) {
	            inactiveCenters.add(center);
	        } 
	    }		
		mv.addObject("centers", inactiveCenters);
		mv.setViewName("addCitizen");
		return mv;
	}
	
	@RequestMapping("/viewCitizen")
	public ModelAndView ViewCitizens(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();		
        List<Citizen> citizens = citizenService.getAllCitizens(); // Retrieve all centers from the service
	    int citizenCount = citizens.size();
	    mv.addObject("citizens", citizens);
	    mv.addObject("citizenCount", citizenCount > 0 ? citizenCount : 0);
		
		mv.setViewName("viewCitizen");
		return mv;
	}
	
	
	   @RequestMapping("/editCitizen")
	    public ModelAndView editCitizen(@RequestParam("id") int id ,HttpServletRequest request, HttpServletResponse response) {
		   ModelAndView mv = new ModelAndView();
	        Citizen citizen = citizenService.getCitizenById(id);	        
			List<Center> vaccineCenters = vaccineService.getAllCenters();
			List<Center> inactiveCenters = new ArrayList<>();
		    for (Center center : vaccineCenters) {
		        String status = center.getStatus();
		        if (status == null) {
		            inactiveCenters.add(center);
		        } 
		    }			
			mv.addObject("centers", inactiveCenters);
	        
	        mv.addObject("citizen", citizen);
	        mv.setViewName("editCitizen");
	        return mv;
	    }
	   
  
	   @RequestMapping("/updateCitizen")
	   public ModelAndView updateCitizen(
	       @RequestParam("id") int id,
	       @RequestParam("name") String name,
	       @RequestParam("centerName") String centerName,
	       @RequestParam("city") String city,
	       @RequestParam("doses") int doses,
	       HttpServletRequest request,
	       HttpServletResponse response,
	       RedirectAttributes redirectAttributes
	   ) {
	       ModelAndView mv = new ModelAndView();
	       Citizen citizen = citizenService.getCitizenById(id);
	       if (citizen != null) {
	           citizen.setName(name);
	           citizen.setCity(city);
	           
	           // Prevent changing the centerName	           
	           if (!centerName.equals(citizen.getCenter().getCenterName())) {
	               mv.setViewName("redirect:/user/citizen/editCitizen?id=" + id);
	        	   
	               String message = "You cannot change the clinic.";
	               redirectAttributes.addFlashAttribute("message", message);
	               return mv;
	           }

	           citizen.setDoses(doses);

	           // Update vaccination status based on doses
	           if (doses == 0) {
	               citizen.setVaccinationStatus("Not Vaccinated");
	           } else if (doses == 1) {
	               citizen.setVaccinationStatus("Partially Vaccinated");
	           } else if (doses == 2) {
	               citizen.setVaccinationStatus("Fully Vaccinated");
	           }
	           
	           citizenService.updateCitizen(citizen);
	           mv.setViewName("redirect:/user/citizen/viewCitizen");
	           String message = name+" IS UPDATED";
	           redirectAttributes.addFlashAttribute("message", message);
	       } else {	           
	           mv.setViewName("redirect:/user/vaccination/editCenter?id=" + id);
	           redirectAttributes.addFlashAttribute("message", "Failed to update citizen.");
	       }

	       return mv;
	   }

	   @RequestMapping("/deleteCitizen")
	    public ModelAndView deleteCitizen(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
	       ;
	        citizenService.deleteCitizenById(id);
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("redirect:/user/citizen/viewCitizen");
	        redirectAttributes.addFlashAttribute("message","CITIZEN DELETED");
	        return mv;
	        
	    }
	   
	@RequestMapping("/insertCitizen")
	public ModelAndView insertCitizen(HttpServletRequest request, HttpServletResponse response, Model model) {
	    ModelAndView mv = new ModelAndView();
	    Citizen citizen = new Citizen();
	    String name = request.getParameter("name");
	    String city = request.getParameter("city");
	    String centerName = request.getParameter("centerName");
	    String doses = request.getParameter("doses");
	    int numberOfDoses = Integer.parseInt(doses);
	    if (name == null || name.isEmpty() || city == null || city.isEmpty() || centerName == null || centerName.isEmpty()) {
	        mv.setViewName("addCitizen");	        
	        mv.addObject("message", "Invalid or fill every block ");
	    } else {
	        try {
	            citizen.setName(name);
	            citizen.setCity(city);
	            String vaccinationStatus = "";
	            switch (numberOfDoses) {
                case 0:
                    vaccinationStatus = "Not Vaccinated";
                    break;
                case 1:
                    vaccinationStatus = "Partially Vaccinated";
                    break;
                case 2:
                    vaccinationStatus = "Fully Vaccinated";
                    break;
                default:
                	 mv.setViewName("addCitizen");
                     mv.addObject("message", "Invalid number of doses");
                     return mv;
             }
	            citizen.setDoses(numberOfDoses);
	            citizen.setVaccinationStatus(vaccinationStatus);
	            Center center = new Center();
	            center.setCenterName(centerName);
	            String status = "active";
	            center.setStatus(status);
	            center.setCity(city);
	            citizen.setCenter(center);
	            citizenService.saveCitizen(citizen);
	            mv.setViewName("redirect:/user/citizen/viewCitizen");
	            mv.addObject("citizen", citizen);
	        } catch (Exception e) {	            
	            mv.setViewName("addCitizen");
	            mv.addObject("message", "Failed to save Citizen");
	        }
	    }

	    return mv;
	}	
	
	@RequestMapping("/viewCitizenById")
	public ModelAndView viewCitizen(@RequestParam("id") int id) {
	    ModelAndView mv = new ModelAndView();	    
	    Citizen citizen = citizenService.getCitizenById(id);        
	    if (citizen != null) {
	        mv.addObject("citizen", citizen);
	        mv.setViewName("viewCitizenById");
	    } else {
	        mv.setViewName("error");
	        mv.addObject("message", "Citizen not found");
	    }

	    return mv;
	}

}
