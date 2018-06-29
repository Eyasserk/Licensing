package es.etsit.silcam.util;

import org.springframework.util.DigestUtils;

public class HashUtils {
	
	public static String getHash(String key) {
		return DigestUtils.md5DigestAsHex(key.getBytes());
	}
}
