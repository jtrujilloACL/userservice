package io.getarrays.userservice.api;

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

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
//import io.getarrays.userservice.service.UserService;
import io.getarrays.userservice.service.UserServiceImplementation;
import io.getarrays.userservice.utils.RoleToUserForm;
import io.getarrays.userservice.utils.UtilToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 19/02/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserResource {
	private final UserServiceImplementation userService;
	
	@GetMapping("/users")
	public List<User> getUser(){
		List<User> user = StreamSupport.stream(userService.getUsers().spliterator(), false)
				.collect(Collectors.toList());
		return user;
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		log.info("User Save from POSTMAN: {}",user.getUsername());
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString() );
		return ResponseEntity.created(uri).body( userService.saveUser(user) );
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toString() );
		return ResponseEntity.created(uri).body( userService.saveRole(role) );
	}

	@PostMapping("/role/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		userService.addRoleToUser( form.getUsername(),form.getRoleName() );
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		UtilToken.generateToken();
		
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		log.info("UserResource Header : {}",authorizationHeader);
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length() );
				log.info("Secret get bytes: {}","secret".getBytes());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodeJWT = verifier.verify(refresh_token);
				String username = decodeJWT.getSubject();
				User user = userService.getUser(username);
				String acces_token = JWT.create()
						.withSubject( user.getUsername())
						.withExpiresAt( new Date( System.currentTimeMillis() + 10 * 60 * 1000 ))
						.withClaim("roles", user.getRoles().stream().map(Role::getName).collect( Collectors.toList() ))
						.sign(algorithm);
				Map<String, String> tokens = new  HashMap<>();
				tokens.put("acces_token", acces_token);
				tokens.put("refesh_token", refresh_token);
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
