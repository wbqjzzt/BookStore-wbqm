package com.tiny.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MD5Util {
    public static String encode(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] data = messageDigest.digest(text.getBytes());
            Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
