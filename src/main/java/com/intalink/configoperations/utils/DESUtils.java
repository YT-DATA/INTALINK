package com.intalink.configoperations.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * yqi 2018-5-22
 * TripleDES 加密及解密
 */
public class DESUtils {

    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final String DEFAULT_INCODING = "utf-8";
    private static final String SECRET_KEY= "xayt-sjzl";

    public final static String encrypt(String code, String key) {
        try {
            return Base64.encodeBase64String(encrypt(code.getBytes(DEFAULT_INCODING), key.getBytes(DEFAULT_INCODING)));
        } catch (Exception e) {
        }
        return null;
    }

    public final static String encrypt(String code) {
        return encrypt(code,SECRET_KEY);
    }

    //加密
    public static byte[] encrypt(byte[] code, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return cipher.doFinal(code);
    }

    //解密
    public final static String decrypt(String code, String key) {
        try {
            return new String(decrypt(Base64.decodeBase64(code), key.getBytes(DEFAULT_INCODING)), DEFAULT_INCODING);
        } catch (Exception e) {
        }
        return null;
    }

    public final static String decrypt(String code) {
        return decrypt(code,SECRET_KEY);
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey sectetKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, sectetKey, sr);
        return cipher.doFinal(src);
    }

}
