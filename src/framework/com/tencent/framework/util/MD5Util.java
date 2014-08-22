package com.tencent.framework.util;

/*
 *  $Id: MD5Util.java 458 2009-04-27 07:45:30Z guoxp $
 */

/**
 *
 * @author Administrator
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


    protected static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};



    public static String md5(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        FileInputStream in = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(in);
        byte byteBuffer[] = new byte[2048];
        int numRead = 0;
        while ((numRead=bis.read(byteBuffer)) > 0) {
            messagedigest.update(byteBuffer, 0, numRead );
        }
        return bufferToHex(messagedigest.digest());
    }

    public static String md5(String s) throws NoSuchAlgorithmException {
        return getMD5String(s.getBytes());
    }

    private static String getMD5String(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}  