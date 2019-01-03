package com.tiny.util;

import java.util.UUID;

public class EmailUtil {
    public static String create() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }

    public static String getUID(String text) {
        if (null == text || text.lastIndexOf("-") == -1) return null;
        return text.substring(text.lastIndexOf("-") + 1);
    }

    public static String getUUID(String text) {
        if (null == text || text.lastIndexOf("-") == -1) return null;
        return text.substring(0, text.lastIndexOf("-"));
    }
}
