package io.getarrays.userservice.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log.info("Username is : {}", username);log.info("Password is : {}", password);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authenticationToken);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		User user = (User)authentication.getPrincipal();
		// TODO: "secret" hidden and encrypt in another location
		// WithExpiresAt 10min
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		String acces_token = JWT.create()
				.withSubject( user.getUsername())
				.withExpiresAt( new Date( System.currentTimeMillis() + 10 * 60 * 1000 ))
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect( Collectors.toList() ))
				.sign(algorithm);
		String refesh_token = JWT.create()
				.withSubject( user.getUsername())
				.withExpiresAt( new Date( System.currentTimeMillis() + 30 * 60 * 1000 ))
				.sign(algorithm);
		/*response.setHeader("acces_token", acces_token);
		response.setHeader("refesh_token", refesh_token);*/
		Map<String, String> tokens = new  HashMap<>();
		tokens.put("acces_token", acces_token);
		tokens.put("refesh_token", refesh_token);
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
	
}
