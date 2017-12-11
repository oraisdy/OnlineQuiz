package com.example.qs.Util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by phoebegl on 2017/12/9.
 */
public class Encrypt {

    public static String aes(String str) {
        Cipher cipher = null;
        String result = "";
        try {
            cipher = Cipher.getInstance("AES");
            SecretKey key = KeyGenerator.getInstance("AES").generateKey();
            cipher.init(Cipher.ENCRYPT_MODE,key);
            result =cipher.doFinal(str.getBytes()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
