package com.desarrolloweb.redsocial.Tools;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class Encoding {
    String miString = "";
    StringBuilder nuevaP = new StringBuilder();
    UUID sessionId;

    public String crypt(String texto) {
        miString = texto;
        int valor = texto.length();
        //encripta
        for (char caracter : miString.toCharArray()) {
            int vAsciiM = (int) caracter + valor;
            char caracterM = (char) vAsciiM;
            nuevaP.append(caracterM);
        }
        return nuevaP.toString();
    }
        public static String calcularMD5(String input) {
            return DigestUtils.md5Hex(input);
        }


    public UUID SessionManager(){
        return sessionId = UUID.randomUUID();
    }

}

