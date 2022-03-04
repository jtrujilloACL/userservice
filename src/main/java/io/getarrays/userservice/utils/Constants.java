package io.getarrays.userservice.utils;

/**
 * @author JeanTrujillo
 * @version 1.1
 * @since 03/03/2022 04/03/2022
 */
public final class Constants {
	/*Role*/
	public static final String SPRING_ROLE_USER = "ROLE_USER";
	public static final String SPRING_ROLE_MANAGER= "ROLE_MANAGER";
	public static final String SPRING_ROLE_ADMIN = "ROLE_ADMIN";
	public static final String SPRING_ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
	
	/*Token*/
	public static final String SPRING_SECRET_KEY = "secret";

	private Constants() {
	}
}