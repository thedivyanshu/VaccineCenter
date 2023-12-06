package com.vaccineCenter.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.vaccineCenter.pojo.Center;
import com.vaccineCenter.pojo.Citizen;
import com.vaccineCenter.service.CenterService;
import com.vaccineCenter.service.CitizensService;

@Controller
@RequestMapping("/user/vaccination")
public class VaccinationCenterController {

	@Autowired
	CenterService vaccineService;
	@Autowired
	CitizensService citizenService;
	
	@RequestMapping("/addCenter")
	public ModelAndView AddNewCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();	
		mv.setViewName("addCenter");
		return mv;
	}

	@RequestMapping("/viewCenterList")
	public ModelAndView viewCenter() {
	    ModelAndView mv = new ModelAndView();

	    List<Center> vaccineCenters = vaccineService.getAllCenters();

	    // Separate vaccination centers into different categories
	    List<Center> activeCenters = new ArrayList<>();
	    List<Center> inactiveCenters = new ArrayList<>();

	    for (Center center : vaccineCenters) {
	        String status = center.getStatus();
	        if (status == null) {
            inactiveCenters.add(center);
	        } 
	    }
	    mv.setViewName("viewCenter");
	    mv.addObject("centers", inactiveCenters);
	    int centerCount =  inactiveCenters.size();
        mv.addObject("centerCount", centerCount > 0 ? centerCount : 0);
	    return mv;
	}

	
	 @RequestMapping("/viewCenterById")
	   public ModelAndView viewCenterById(@RequestParam("id") int id, @RequestParam("centerName") String centerName, RedirectAttributes redirectAttributes) {
	       ModelAndView mv = new ModelAndView();
	       Center center = vaccineService.getbyid(id);
	       
	       if (center != null) {
	           List<Citizen> citizens = citizenService.getAllCitizens();
	           mv.addObject("center", center);
	           mv.addObject("citizens", citizens);
	           mv.setViewName("viewCenterById");
	       } else {
	    	   redirectAttributes.addFlashAttribute("message","ID IS NOT AVAIABLE");
	           mv.setViewName("redirect:/user/vaccination/viewCenter");
	       }
	       
	       return mv;
	   }
	 
	@RequestMapping("/insertCenter")
	public ModelAndView insertCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Center center = new Center();
		String centerName = request.getParameter("centerName");
		String city = request.getParameter("city");
		
		
		
		
		if (centerName == null || centerName.isEmpty() && city.isEmpty()) {
			mv.setViewName("addCenter");
			mv.addObject("message", "Failed to save Center");
			
		} else {
			

			
			 try {
		            center.setCenterName(centerName);
		            center.setCity(city);
		            vaccineService.AddCenter(center);
		            mv.setViewName("redirect:/user/vaccination/viewCenterList");
		            Center vaccinationCenter = vaccineService.getCenterByName(centerName);		            
		            mv.addObject(vaccinationCenter);
		        } catch (Exception e) {
		            mv.setViewName("addCenter");
		            mv.addObject("message", "Failed to save Center");
		        }
		}
		
		return mv;
	}
	
	
	   @RequestMapping("/editCenter")
	    public ModelAndView editCenter(@RequestParam("id") int id ,HttpServletRequest request, HttpServletResponse response) {
		   ModelAndView mv = new ModelAndView();
	        Center center = vaccineService.getbyid(id);	       
	        mv.addObject("center", center);
	        mv.setViewName("editCenter");
	        return mv;
	    }

	    @RequestMapping("/deleteCenter")
	    public ModelAndView deleteCenter(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
	        vaccineService.deletebyid(id);
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("redirect:/user/vaccination/viewCenterList");
	        String message = " IS DELETED";
	        redirectAttributes.addFlashAttribute(id + message);
	        return mv;
	    }
	    
	    @RequestMapping("/updateCenter")
	    public ModelAndView updateCenter(@RequestParam("id") int id ,@RequestParam("centerName") String centerName ,@RequestParam("city")String city  ,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		   ModelAndView mv = new ModelAndView();
		   Center center = vaccineService.getbyid(id);		  
           if(center!=null ) {
        	   center.setCenterName(centerName);
        	   center.setCity(city);
        	   vaccineService.updateCenter(center);
        	   mv.setViewName("redirect:/user/vaccination/viewCenterList");
        	   String message = centerName+" IS UPDATED";
        	   redirectAttributes.addFlashAttribute("message"+ message);
        	   }else {        	   
        	   mv.setViewName("redirect:/user/vaccination/editCenter?id=" + id); // Redirect to the editCenter request mapping with the id parameter
	        mv.addObject("message", "Failed to Update Center");
           }
	        return mv;
	    }
}
