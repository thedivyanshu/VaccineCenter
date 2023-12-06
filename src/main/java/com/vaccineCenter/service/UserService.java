package com.vaccineCenter.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import com.vaccineCenter.pojo.User;
import com.vaccineCenter.repository.UserRepository;


@Service
public class UserService {

	
	@Autowired
	UserRepository userRepo ;
	
	public void addUser(User user){
		userRepo.save(user);
    }
	
	public boolean checkLogin(@RequestParam("email") String email , @RequestParam("password") String password) {
		return userRepo.checkLoginRepo(email, password);
    }
		
	public User getuserByEmail(String email) {
        List<User> users = userRepo.findByEmail(email);        
        if (!users.isEmpty()) {
            return users.get(0); // Returning the first user from the list
            
        }
        return null; // No user found with the given email
    }

	public User findByEmailAndPassword(String email,String password) {
		User authenticatedUser = userRepo.findByEmailAndPassword(email,password);
       
		return authenticatedUser;
	}
}
