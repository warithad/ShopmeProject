package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encodedPassword = encoder.encode("password");
		System.out.println(encodedPassword);
		boolean matches = encoder.matches("password", encodedPassword);
		
		assertThat(matches).isTrue();
	}
}
