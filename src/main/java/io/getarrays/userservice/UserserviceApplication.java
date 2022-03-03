package io.getarrays.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.getarrays.userservice.repository.entity.Role;
import io.getarrays.userservice.repository.entity.User;
import io.getarrays.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean 
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role( null,"ROLE_USER"));
			userService.saveRole(new Role( null,"ROLE_MANAGER"));
			userService.saveRole(new Role( null,"ROLE_ADMIN"));
			userService.saveRole(new Role( null,"ROLE_SUPER_ADMIN"));
						
			userService.saveUser( new User( null,"name" ,"username" ,"pass",new ArrayList<>() ));
			userService.saveUser( new User( null,"name2","username2","pass",new ArrayList<>() ));
			userService.saveUser( new User( null,"name3","username3","pass",new ArrayList<>() ));
			userService.saveUser( new User( null,"name4","username4","pass",new ArrayList<>() ));
			
			userService.addRoleToUser("username", "ROLE_MANAGER");
			userService.addRoleToUser("username", "ROLE_USER");
			userService.addRoleToUser("username2", "ROLE_MANAGER");
			userService.addRoleToUser("username3", "ROLE_ADMIN");
			userService.addRoleToUser("username4", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("username4", "ROLE_ADMIN");
			userService.addRoleToUser("username4", "ROLE_USER");
		};
	}
}
