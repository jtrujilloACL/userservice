package io.getarrays.userservice.service;

import java.util.List;

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 16/02/2022
 */
public interface UserService {
	 Role saveRole(Role role); //TODO: MOVE to ROLE SERVICE
	 Profile saveProfile(Profile profile); //TODO: MOVE to PROFILE SERVICE
	 User saveUser(User user);
	 
	 void addRoleToUser(String username, String roleName);
	 
	 User getUser(String username);
	 List<User> getUsers();
}
