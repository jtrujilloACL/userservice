package io.getarrays.userservice.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.getarrays.userservice.dto.UserDTO;
import io.getarrays.userservice.repository.UserRepository;
import io.getarrays.userservice.repository.entity.User;
import io.getarrays.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.1
 * @since 16/10/2021 / 23/02/2022
 */
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEnconde;
	
	/**
	 * @see SecurityConfig
	 * override method loadUserByUsername extend userDEtailService for authentication Springboot.Security
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			log.error("User not found in database: {}",username);
			throw new UsernameNotFoundException("User not found in database");
		}
		else {
			log.info("User found in database: {}",username);
			
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach( role -> {
			authorities.add( new SimpleGrantedAuthority(role.getName()) ); 
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities );
	}
	
	@Override
	public User saveUser(UserDTO userDTO) {
		log.info("Saving new user {} to the database",userDTO.getUsername() );
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEnconde.encode(userDTO.getPassword() ));
		user.setRoles( new ArrayList<>() );
		return userRepository.save(user);
	}
	
	@Override
	public User getUser(String username) {
		log.info("Fetching user {}",username);
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepository.findAll();
	}
	
}
