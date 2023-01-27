package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private RoleRepository roleRepository;
	
    @Autowired
    private UserService userService;

//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
//    	Role role = roleRepository.findById("User").get();
//    	System.out.println("role is : " + role);
//    	Set<Role> roles = new HashSet<Role>();
//    	roles.add(role);
//    	user.setUserPassword(userService.getEcod(user.getUserPassword()));
//    	user.setRole(roles);
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
	
}
