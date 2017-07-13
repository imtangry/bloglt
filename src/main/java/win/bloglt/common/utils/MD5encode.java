package win.bloglt.common.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tryu on 2017/7/13.
 * 将字符串（用户密码）进行加密的工具类
 */
public class MD5encode {
    public static String encrypt(String code) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String cipherText;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //防止返回乱码
        cipherText = base64Encoder.encode(md5.digest(code.getBytes("UTF-8")));
        return cipherText;
    }
}
