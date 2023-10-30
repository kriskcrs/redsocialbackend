package com.desarrolloweb.redsocial.Tools;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class Encoding {
    String miString = "";
    StringBuilder nuevaP = new StringBuilder();
    UUID sessionId;

    public static String MD5(String input) {
        return DigestUtils.md5Hex(input);
    }


    public UUID SessionManager() {
        return sessionId = UUID.randomUUID();
    }

}

