package io.getarrays.userservice.utils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JeanTrujillo
 * @version 1.0
 * @since 22/02/2022
 */
@Slf4j
public final class UtilToken {

	@Autowired
	public static String getUsernameToToken(HttpServletRequest request){
		try {
			String authorizationHeader = request.getHeader(AUTHORIZATION);
			String token = authorizationHeader.substring("Bearer ".length() );
			Algorithm algorithm = Algorithm.HMAC256(Constants.SPRING_SECRET_KEY.getBytes());
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodeJWT = verifier.verify(token);
			return decodeJWT.getSubject();
		} catch (Exception e) {
			//TODO: Add exception
			log.info("Error getUsernameToToken: {}",e.getMessage());
		}
		return null;
		
	}
}
