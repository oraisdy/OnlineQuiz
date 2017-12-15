package com.example.qs.Util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by phoebegl on 2017/12/9.
 */
public class Encrypt {

    public static String sKey = "1234567890123456";

    public static String encode(String str) {

        try {
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("utf-8"));

            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decode(String str) throws Exception{
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = new Base64().decode(str);//先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original,"utf-8");
        return originalString;
    }

    public static void main(String[] args) {
        String origin = null;
        try {
            origin = Encrypt.decode("+bRriCwgAbKgosHhRFQ8dg==");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] temp = origin.split(" ");
        int examid = Integer.parseInt(temp[0]);
        int userid = Integer.parseInt(temp[1]);

        System.out.println(examid);
        System.out.println(userid);
    }

}
