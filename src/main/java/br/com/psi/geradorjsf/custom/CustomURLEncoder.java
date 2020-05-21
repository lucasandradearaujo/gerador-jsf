package br.com.psi.geradorjsf.custom;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Hiago
 */
public class CustomURLEncoder {
    public static String encodeUTF8(String value){
        try {
            return value == null ? null : URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported by this JVM");
        }
    }
}