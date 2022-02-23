package io.getarrays.userservice.service;

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

import io.getarrays.userservice.domain.Profile;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.repository.ProfileRepository;
import io.getarrays.userservice.repository.RoleRepository;
import io.getarrays.userservice.repository.UserRepository;
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
	
	private final RoleRepository roleRepository;
	
	private final ProfileRepository profileRepository; // ProfileService
	
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
	public User saveUser(User user) {
		log.info("Saving new user {} to the database",user.getName() );
		user.setPassword(passwordEnconde.encode(user.getPassword() ));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database",role.getName() );	
		return roleRepository.save(role);
	}

	@Override
	public Profile saveProfile(Profile profile) {
		log.info("Saving new profile {} to the database", profile.getId() );	
		return profileRepository.save(profile);
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

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Add role {} to the user{}",roleName, username );
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
	}
	
}
