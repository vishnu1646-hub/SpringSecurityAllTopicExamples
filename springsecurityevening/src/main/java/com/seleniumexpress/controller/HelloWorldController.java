package com.seleniumexpress.controller;

import java.security.Principal;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seleniumexpress.dto.ChangePasswordDTO;

@Controller
public class HelloWorldController {
	

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// principal means username
	@GetMapping("/home") // dont secure it
	public String helloWorld(Principal principal, Authentication auth, Model model) {
		// fetches username
		String username = principal.getName();
		System.out.println("logged in user is:" + username);
		// fetches the roles of a user
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		System.out.println(authorities);
		model.addAttribute("username", username);
		model.addAttribute("roles", authorities);
		return "home-page";
	}

	// or
	/*
	 * @GetMapping("/")//dont secure it public String helloWorld(Authentication
	 * auth){ String username = auth.getPrincipal().toString();
	 * System.out.println("logged in user is:"+username); return "home-page"; }
	 */
	@ResponseBody
	@GetMapping("/hello") // secure it
	public String hello() {
		return "hello from selenium express";
	}

	@ResponseBody
	@GetMapping("/bye") // secure it
	public String bye() {
		return "bye bye guys";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username) {
		jdbcUserDetailsManager.deleteUser(username);
		System.out.println("user deleted :"+username);
		return "redirect:/myCustomLogin";
	}

	@GetMapping("/changePassword")
	public String changePassword(Model model) {
		model.addAttribute("password-chng", new ChangePasswordDTO());
		return "change-password";
	}
	
	@PostMapping("/save-password")
	public String savePassword(Principal principal,ChangePasswordDTO changePasswordDTO) {
		//check whether the old password is correct
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(principal.getName());
		boolean matches = passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());
		//checks if new and confirm password matches are not
		if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())){
			return "redirect:/changePassword?notMatched";
		}
		if(matches){
			//logic to save the new password into the database
		String encodedPassword = passwordEncoder.encode(changePasswordDTO.getConfirmPassword());
		jdbcUserDetailsManager.changePassword(changePasswordDTO.getOldPassword(), encodedPassword);
		System.out.println("password changed....");
		return "redirect:/home";
		}
		return "redirect:/changePassword?invalidPassword";
	}
	

	
	@GetMapping("/trainer")
	public String showTrainerDashBoard() {
		return "trainer-dashboard";
	}

	@GetMapping("/coder")
	public String showCoderDashBoard() {
		return "coder-dashboard";
	}

	@GetMapping("/accessDenied")
	public String error() {
		return "accessdenied";
	}
}
