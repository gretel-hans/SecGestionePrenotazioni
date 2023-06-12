package com.hans.runner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hans.model.ERole;
import com.hans.model.Role;
import com.hans.repository.RoleRepository;
import com.hans.repository.UtenteRepository;
import com.hans.service.AuthService;



@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UtenteRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// Metodo da lanciare solo la prima volta
		// Serve per salvare i ruoli nel DB
		setRoleDefault();
		
	}
	
	private void setRoleDefault() {
		if(roleRepository.findAll().size()==0) {
			Role admin = new Role();
			admin.setRoleName(ERole.ROLE_ADMIN);
			roleRepository.save(admin);
			
			Role user = new Role();
			user.setRoleName(ERole.ROLE_USER);
			roleRepository.save(user);
			
			Role moderator = new Role();
			moderator.setRoleName(ERole.ROLE_MODERATOR);
			roleRepository.save(moderator);
		}
		
//		adminRole = new HashSet<Role>();
//		adminRole.add(admin);
//		adminRole.add(moderator);
//		adminRole.add(user);
//		
//		moderatorRole = new HashSet<Role>();
//		moderatorRole.add(moderator);
//		moderatorRole.add(user);
//		
//		userRole = new HashSet<Role>();
//		userRole.add(user);
	}
	
	

}