package com.rmbartolome.agence;

import com.rmbartolome.agence.models.security.ERole;
import com.rmbartolome.agence.models.security.Role;
import com.rmbartolome.agence.payload.request.SignupRequest;
import com.rmbartolome.agence.repository.security.RoleRepository;
import com.rmbartolome.agence.security.services.security.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class SpringBootSecurityPostgresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityPostgresqlApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, AuthService authService) {

		return args -> {

			Optional<Role> roleDB = roleRepository.findByName(ERole.ROLE_ADMIN);
			if (roleDB.orElse(null) == null) {
				Role newAdminRole = new Role();
				newAdminRole.setName(ERole.ROLE_ADMIN);
				roleRepository.save(newAdminRole);
				SignupRequest signupRequest = new SignupRequest();
				HashSet<String> roles = new HashSet<>();
				roles.add("admin");
				signupRequest.setRoles(roles);
				signupRequest.setEmail("admin@agence.com");
				signupRequest.setUsername("admin");
				signupRequest.setPassword("fleetmg@!");
				authService.signUp(signupRequest);
			}

			roleDB = roleRepository.findByName(ERole.ROLE_MODERATOR);
			if (roleDB.orElse(null) == null) {
				Role newAdminRole = new Role();
				newAdminRole.setName(ERole.ROLE_MODERATOR);
				roleRepository.save(newAdminRole);
			}

			roleDB = roleRepository.findByName(ERole.ROLE_USER);
			if (roleDB.orElse(null) == null) {
				Role newUserRole = new Role();
				newUserRole.setName(ERole.ROLE_USER);
				roleRepository.save(newUserRole);
			}
		};
	}
}