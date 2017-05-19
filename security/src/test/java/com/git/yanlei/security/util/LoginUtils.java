package com.git.yanlei.security.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.git.yanlei.security.AuthenticatorTests;

public class LoginUtils {

    public static Subject loginAs_ZhangSan(String iniResourcePath) {
        String username = "zhangsan";
        String password = "123";
        return login(iniResourcePath, username, password);
    }
    
    public static Subject loginAs_LiSi(String iniResourcePath) {
        String username = "lisi";
        String password = "123";
        return login(iniResourcePath, username, password);
    }

    private static Subject login(String iniResourcePath, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniResourcePath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            AuthenticatorTests.log.info("{}登陆失败", username, e);
            return subject;
        }
        AuthenticatorTests.log.info("{}登陆成功", username);
        return subject;
    }

}
