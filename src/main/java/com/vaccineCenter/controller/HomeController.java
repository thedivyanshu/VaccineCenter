package com.vaccineCenter.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	Logger log = Logger.getAnonymousLogger();
	
	@RequestMapping("/")
	public ModelAndView basepage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		log.info("login page");
	
		mv.setViewName("redirect:/user/login");
		return mv;
	}
}
