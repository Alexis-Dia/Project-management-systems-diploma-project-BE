package com.projectManagement.util;

import com.projectManagement.exceptions.NonInstanceException;

import org.apache.commons.codec.digest.DigestUtils;

public final class EncryptionUtils {

	private EncryptionUtils() {

		throw new NonInstanceException(EncryptionUtils.class);
	}

	public static String sha256Encrypt(String raw) {

		return DigestUtils.sha256Hex(raw);
	}
}
