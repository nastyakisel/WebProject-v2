package com.finalproject.onlineapteka.service.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CipherUtils {
	public static String md5Cipher(String st) {
	    return DigestUtils.md5Hex(st);
	}
}
