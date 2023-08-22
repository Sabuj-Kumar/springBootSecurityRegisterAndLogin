package com.loginregistration.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.loginregistration.security.entity.User;
import com.loginregistration.security.repository.UserRepository;
import com.loginregistration.security.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/register")
	public String registration() {
		
		return "registration";
	}
	
	@ModelAttribute
	public void commonUser(Principal p,Model m) {
		
		if(p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user",user);
		}
	}
	@GetMapping("/signin")
	public String login() {
		
		return "login";
	}
	
	@GetMapping("/user/profile")
	public String profile(Principal p,Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		System.out.println(email);
		m.addAttribute("user",user);
		return "profile";
	}
	
	@GetMapping("/user/home")
	public String home() {
		
		return "home";
	}
	
	@PostMapping("/createUser")
	public String saveUser(@ModelAttribute User user,HttpSession session) {
		
		//System.out.println(user);
		User u = userService.saveUser(user);
		
		if(u != null) {
			session.setAttribute("msg", "succesfully added");
		}else {
			session.setAttribute("msg", "not added");
		}
		return "redirect:/register";
	}
	
}
