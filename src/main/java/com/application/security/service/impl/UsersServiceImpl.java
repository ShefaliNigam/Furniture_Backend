package com.application.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.security.entity.Users;
import com.application.security.repository.UsersRepository;

@Service
public class UsersServiceImpl {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Optional<Users> addUser(Users users) {

		Users user = usersRepository.save(users);
		return Optional.of(user);
	}
	
	public Optional<Users> getUserById(String id){
		Optional<Users> user = usersRepository.findById(id);
		return user;
	}
	
	public Optional<Users> getUserByName(String name){
		Optional<Users> user =usersRepository.findByUserName(name);
		return user;
	}
	
	public Optional<Users> getUserByEmail(String email){
		Optional<Users> user = usersRepository.findByUserEmail(email);
		return user;
	}
	
	public Optional<Users> getUserByNumber(Long number){
		Optional<Users> user = usersRepository.findByUserMobile(number);
		return user;
	}
	
	public Optional<List<Users>> getUserByRole(String role){
		Optional<List<Users>> users = usersRepository.findByUserRole(role);
		return users;
	}
	


}
