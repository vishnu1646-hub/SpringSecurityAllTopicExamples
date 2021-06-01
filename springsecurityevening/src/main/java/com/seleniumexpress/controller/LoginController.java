package com.seleniumexpress.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.seleniumexpress.dao.SignupDAO;
import com.seleniumexpress.dto.SignupDTO;

@Controller
public class LoginController {
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;


	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@GetMapping("/myCustomLogin")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupdto") SignupDTO signupDTO) {
		return "signup";
	}

	@PostMapping("/process-signup")
	public String processSignup(SignupDTO signupDTO) {
		// before encoding
		System.out.println(signupDTO);

		String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
		signupDTO.setPassword(encodedPassword);

		// after encoding
		System.out.println(signupDTO);
		
		
		UserDetails user = User.withUsername(signupDTO.getUsername()).password(signupDTO.getPassword()).authorities("Coder").build();
		jdbcUserDetailsManager.createUser(user);

		// save the dto to db call
		//signupDAO.saveUser(signupDTO);
		return "redirect:/myCustomLogin";
	}
}
