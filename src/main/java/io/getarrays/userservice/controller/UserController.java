package io.getarrays.userservice.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.IOException;
import java.lang.RuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.getarrays.userservice.dto.UserDTO;
import io.getarrays.userservice.repository.entity.Role;
import io.getarrays.userservice.repository.entity.User;
import io.getarrays.userservice.service.implementation.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.2
 * @since 19/02/2022 03/03/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {
	@Autowired
	private final UserServiceImplementation userService;
	
	@GetMapping("/users")
	public List<User> getUser(){
		return StreamSupport.stream(userService.getUsers().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO){
		log.info("User Save from POSTMAN: {}",userDTO.getUsername());
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString() );
		return ResponseEntity.created(uri).body( userService.saveUser(userDTO) );
	}
	
	
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		log.info("UserResource Header : {}",authorizationHeader);
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length() );
				log.info("Secret get bytes: {}","secret".getBytes());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodeJWT = verifier.verify(refreshToken);
				String username = decodeJWT.getSubject();
				User user = userService.getUser(username);
				String accesToken = JWT.create()
						.withSubject( user.getUsername())
						.withExpiresAt( new Date( System.currentTimeMillis() + 10 * 60 * 1000 ))
						.withClaim("roles", user.getRoles().stream().map(Role::getName).collect( Collectors.toList() ))
						.sign(algorithm);
				Map<String, String> tokens = new  HashMap<>();
				tokens.put("acces_token", accesToken);
				tokens.put("refesh_token", refreshToken);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				
			} catch (Exception e) {
				log.error("Error logging in: {}", e.getMessage());
				response.setHeader("error", e.getMessage());
				response.setStatus(FORBIDDEN.value());
				Map<String, String> error = new  HashMap<>();
				error.put("error", e.getMessage() );
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), error);

			}
		}else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
}
