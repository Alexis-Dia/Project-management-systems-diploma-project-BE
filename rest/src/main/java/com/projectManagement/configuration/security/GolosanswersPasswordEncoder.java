package com.projectManagement.configuration.security;

import com.projectManagement.util.EncryptionUtils;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GolosanswersPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {

		return rawPassword.toString();
		//return EncryptionUtils.sha256Encrypt(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		return rawPassword.toString().equals(encodedPassword);
		//String encoded = EncryptionUtils.sha256Encrypt(rawPassword.toString());
		//return encoded.equals(encodedPassword);
	}
}
