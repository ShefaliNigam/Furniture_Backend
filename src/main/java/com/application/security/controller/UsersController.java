package com.application.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.security.entity.Users;
import com.application.security.repository.UsersRepository;
import com.application.security.service.impl.UsersServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UsersController {

	@Autowired
	private UsersServiceImpl usersServiceImpl;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/add")
	public ResponseEntity<String> createUser(@Valid @RequestBody Users user){
		
		if(usersRepository.findByUserEmail(user.getUserEmail()).isPresent()) {
			return ResponseEntity.ok("User Already Exists....");
		}
		user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
		
		 usersServiceImpl.addUser(user).get();
		return ResponseEntity.ok("User Added Successfully");
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") String id){
		Users u = usersServiceImpl.getUserById(id).get();
		return ResponseEntity.ok(u);
	}
	
	@GetMapping("/name/")
	public ResponseEntity<Users> getUserByName(@RequestParam("name") String name){
		Users u = usersServiceImpl.getUserByName(name).get();
		return ResponseEntity.ok(u);
	}
	
	@GetMapping("/email/")
	public ResponseEntity<Users> getUserByEmail(@RequestParam("email") String email){
		Users u = usersServiceImpl.getUserByEmail(email).get();
		return ResponseEntity.ok(u);
	}
	@GetMapping("/role")
	public ResponseEntity<List<Users>>  getUsersByRole(@RequestParam("role") String role){
		List<Users> users = usersServiceImpl.getUserByRole(role).get();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/mobile/")
	public ResponseEntity<Users> getUserByNumber(@RequestParam("mobile") Long number){
		Users u = usersServiceImpl.getUserByNumber(number).get();
		return ResponseEntity.ok(u);
	}

	
	@GetMapping("/home")
	public String home() {
		return "This is Home Page";
	}
}
