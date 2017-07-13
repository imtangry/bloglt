package win.bloglt.common.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import win.bloglt.common.utils.MD5encode;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tryu on 2017/7/13.
 */
public class MyCredentialsMatcher extends SimpleCredentialsMatcher{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            String password = String.valueOf(usernamePasswordToken.getPassword());
            Object tokenCredentials= MD5encode.encrypt(password);
            Object databaseCredentials = getCredentials(info);
            return equals(tokenCredentials,databaseCredentials);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
