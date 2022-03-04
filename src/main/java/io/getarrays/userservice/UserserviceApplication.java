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
						
			userService.saveUser( new UserDTO( "username" ,"pass" ));
			userService.saveUser( new UserDTO( "username2","pass" ));
			userService.saveUser( new UserDTO( "username3","pass" ));
			userService.saveUser( new UserDTO( "username4","pass" ));
			
			roleService.addRoleToUser("username", "ROLE_MANAGER");
			roleService.addRoleToUser("username", "ROLE_USER");
			roleService.addRoleToUser("username2", "ROLE_MANAGER");
			roleService.addRoleToUser("username3", "ROLE_ADMIN");
			roleService.addRoleToUser("username4", "ROLE_SUPER_ADMIN");
			roleService.addRoleToUser("username4", "ROLE_ADMIN");
			roleService.addRoleToUser("username4", "ROLE_USER");
		};
	}
}
