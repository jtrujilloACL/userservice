package io.getarrays.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.getarrays.userservice.dto.UserDTO;
import io.getarrays.userservice.repository.entity.Role;
import io.getarrays.userservice.service.RoleService;
import io.getarrays.userservice.service.UserService;
import io.getarrays.userservice.utils.Constants;

@SpringBootApplication
public class UserserviceApplication {
	
	private static final String USER_USERNAME = "username";
	private static final String USER_USERNAME2 = "username2";
	private static final String USER_USERNAME3 = "username3";
	private static final String USER_USERNAME4 = "username4";
	

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean 
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
			roleService.save(new Role( null, Constants.SPRING_ROLE_USER));
			roleService.save(new Role( null, Constants.SPRING_ROLE_MANAGER));
			roleService.save(new Role( null, Constants.SPRING_ROLE_ADMIN));
			roleService.save(new Role( null, Constants.SPRING_ROLE_SUPER_ADMIN));
						
			userService.saveUser( new UserDTO( USER_USERNAME ,"pass" ));
			userService.saveUser( new UserDTO( USER_USERNAME2,"pass" ));
			userService.saveUser( new UserDTO( USER_USERNAME3,"pass" ));
			userService.saveUser( new UserDTO( USER_USERNAME4,"pass" ));
			
			roleService.addRoleToUser(USER_USERNAME,  Constants.SPRING_ROLE_MANAGER);
			roleService.addRoleToUser(USER_USERNAME,  Constants.SPRING_ROLE_USER);
			roleService.addRoleToUser(USER_USERNAME2, Constants.SPRING_ROLE_MANAGER);
			roleService.addRoleToUser(USER_USERNAME3, Constants.SPRING_ROLE_ADMIN);
			roleService.addRoleToUser(USER_USERNAME4, Constants.SPRING_ROLE_SUPER_ADMIN);
			roleService.addRoleToUser(USER_USERNAME4, Constants.SPRING_ROLE_ADMIN);
			roleService.addRoleToUser(USER_USERNAME4, Constants.SPRING_ROLE_USER);
		};
	}
}
