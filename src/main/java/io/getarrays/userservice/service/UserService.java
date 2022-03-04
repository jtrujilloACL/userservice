package io.getarrays.userservice.service;

import java.util.List;

import io.getarrays.userservice.dto.UserDTO;
import io.getarrays.userservice.repository.entity.User;

/**
 * @author JeanTrujillo
 * @version 1.1
 * @since 16/02/2022 03/03/2022
 */
public interface UserService {
	 User saveUser(UserDTO userDTO);
	 	 
	 User getUser(String username);
	 
	 List<User> getUsers();
}
