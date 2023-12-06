package com.vaccineCenter.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaccineCenter.pojo.Center;
import com.vaccineCenter.pojo.User;
import com.vaccineCenter.service.CenterService;
import com.vaccineCenter.service.UserService;
@Controller
@RequestMapping("/user")
public class LoginController {
   
	@Autowired
	UserService service;
	
	@Autowired
	CenterService vaccineService;
	
	@RequestMapping("/login")
	public ModelAndView basepage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView Logout(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mv = new ModelAndView();
	    HttpSession session = request.getSession(false); // Retrieve the existing session (if any)
	    
	    if (session != null) {
	        session.invalidate(); // Invalidate the session if it exists
	    }

	    mv.addObject("message", "Your session has been logged out.");
	    mv.setViewName("login");
	    
	    return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		 if (name.isEmpty() || name == null ||  email == null || password == null || email.isEmpty() || password.isEmpty()) {
			mv.setViewName("register");			
			mv.addObject("message", "Invalid name or password");
			
		} else {							
			 try {
		            user.setName(name);
		            user.setEmail(email);
		            user.setPassword(password);
		            service.addUser(user);
		            mv.setViewName("redirect:/");
		            mv.addObject(user);
		        } catch (DataIntegrityViolationException e) {
		            mv.setViewName("register");
		            mv.addObject("message", "Email already exists");
		        } catch (Exception e) {
		            mv.setViewName("register");
		            mv.addObject("message", "Failed to save user");
		        }
		}
		
		return mv;
	}
	
	
	@RequestMapping("/dashboard")
	public ModelAndView DashboardLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
 	
		if (email == null || email.isEmpty() && password.isEmpty()) {
			mv.setViewName("redirect:/"); //homecontroller login
			String message = "invalid";
		    mv.addObject("message", message);
		}else {
             //check login by email and password
			 User authenticatedUser = service.findByEmailAndPassword(email, password);
		        if (authenticatedUser != null) {
		            User user = service.getuserByEmail(email);
    				mv.setViewName("dashboard");    				
    				HttpSession httpsession = request.getSession();
    				httpsession.setAttribute("userid", user.getName());
					mv.addObject("user",user);
		        } else {
		        	 mv.setViewName("login");
		        	 String message = "Invalid email or password";
		                mv.addObject("message", message);
		}
		        }
		return mv;
		
		}
	

	@RequestMapping("viewCenter")
	public ModelAndView ViewCenter(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mv = new ModelAndView();	    
	    List<Center> centers = vaccineService.getAllCenters(); // Retrieve all centers from the service	    
	    mv.addObject("centers", centers); // Pass the list of centers to the view
	    mv.setViewName("viewCenter");
	    return mv;
	   }	
	}

