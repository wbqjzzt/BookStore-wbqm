package com.wbqm.util;

import java.util.Random;

public class StringUtil {
    private static final int LENGTH = 6;

    public static String generated(String email) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < LENGTH; i ++) {
            stringBuilder.append(String.valueOf(64 + random.nextInt(122 - 65)));
        }
        return stringBuilder.append("-").append(email).toString();
    }
}
