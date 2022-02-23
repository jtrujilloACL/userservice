package io.getarrays.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
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
			
			Profile user1 = userService.saveProfile( new Profile(null, "name") );
			Profile user2 = userService.saveProfile( new Profile(null, "name2") );
			Profile user3 = userService.saveProfile( new Profile(null, "name3") );
			Profile user4 = userService.saveProfile( new Profile(null, "name4") );
			
			//TODO: remove name and change null of Profile
			userService.saveUser( new User( null,"name" ,"username" ,"pass",new ArrayList<>(),user1 ));
			userService.saveUser( new User( null,"name2","username2","pass",new ArrayList<>(),user2 ));
			userService.saveUser( new User( null,"name3","username3","pass",new ArrayList<>(),user3 ));
			userService.saveUser( new User( null,"name4","username4","pass",new ArrayList<>(),user4 ));
			
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
