package br.com.amil.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public class CryptUtils {

	public static String encrypt(String text) {
		String hash = null;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			BigInteger valorHash = null;
			
			if (StringUtils.isEmpty(text)) {
				valorHash = new BigInteger(1, messageDigest.digest(text.getBytes()));
			} else {
				valorHash = new BigInteger(1, messageDigest.digest(text.getBytes("UTF-8")));
			}

			hash = valorHash.toString(16);
			hash = StringUtils.leftPad(hash, 32, "0");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		return hash;
	}
	
}
