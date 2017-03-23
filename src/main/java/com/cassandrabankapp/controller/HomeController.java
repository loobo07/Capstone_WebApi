package com.cassandrabankapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.cassandrabankapp.domain.Member;
//import com.cassandrabankapp.dto.UserDetailsImpl;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping
	public String showHomePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		logger.info(currentUserName);
		
		
		model.addAttribute(currentUserName);
		return "home";
	}
}
