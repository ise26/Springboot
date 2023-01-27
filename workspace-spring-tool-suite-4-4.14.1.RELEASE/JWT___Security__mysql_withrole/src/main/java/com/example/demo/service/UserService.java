package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private RoleRepository roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEcod("admin@pass"));
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        Set<Role> adminRoles = new HashSet();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserName("raj123");
        user.setUserPassword(getEcod("raj@123"));
        user.setFirstName("raj");
        user.setLastName("sharma");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }
	
	 public User registerNewUser(User user) {
		 Role role1 = new Role();
		 System.out.println("success 1");
	        Role role = roleDao.findById("User").get();
	        Set<Role> userRoles = new HashSet<>();
	        for(Role r : userRoles)
	        {
	        	System.out.println("roles are : "+r.getRoleName());
	        }
	        userRoles.add(role);
	        user.setRole(userRoles);
	        user.setUserPassword(getEcod(user.getUserPassword()));
	        System.out.println("password is : hii "  );
	        return userDao.save(user);
	    }
	 
	 public String getEcod(String password)
	 {
		 return passwordEncoder.encode(password);
	 }
}
