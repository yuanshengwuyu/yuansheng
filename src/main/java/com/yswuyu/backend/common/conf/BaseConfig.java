package com.yswuyu.backend.common.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author Hankin
 */
@Component
@ConfigurationProperties(prefix = "base")
public class BaseConfig {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 版权年份
     */
    private String copyrightYear;
    /**
     * 上传路径
     */
    private static String profile;
    /**
     * 是否开启 上传static
     **/
    private static String isstatic;
    /**
     * 开启存放静态文件夹后目录
     **/
    private static String isroot_dir;
    /**
     * 邮箱发送smtp
     */
    private static String email_smtp;
    /**
     * 发送邮箱端口
     */
    private static String email_port;
    /**
     * 发送邮箱登录账号
     */
    private static String email_account;
    /**
     * 发送邮箱登录密码
     */
    private static String email_password;
    /**
     * 演示模式
     **/
    private static String demoEnabled;
    /**
     * 滚动验证码
     **/
    private static Boolean rollVerification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        BaseConfig.profile = profile;
    }

    public static String getIsstatic() {
        return isstatic;
    }

    public static void setIsstatic(String isstatic) {
        BaseConfig.isstatic = isstatic;
    }

    public static String getIsroot_dir() {
        return isroot_dir;
    }

    public static void setIsroot_dir(String isroot_dir) {
        BaseConfig.isroot_dir = isroot_dir;
    }

    public static String getEmail_smtp() {
        return email_smtp;
    }

    public static void setEmail_smtp(String email_smtp) {
        BaseConfig.email_smtp = email_smtp;
    }

    public static String getEmail_port() {
        return email_port;
    }

    public static void setEmail_port(String email_port) {
        BaseConfig.email_port = email_port;
    }

    public static String getEmail_account() {
        return email_account;
    }

    public static void setEmail_account(String email_account) {
        BaseConfig.email_account = email_account;
    }

    public static String getEmail_password() {
        return email_password;
    }

    public static void setEmail_password(String email_password) {
        BaseConfig.email_password = email_password;
    }

    public static String getDemoEnabled() {
        return demoEnabled;
    }

    public static void setDemoEnabled(String demoEnabled) {
        BaseConfig.demoEnabled = demoEnabled;
    }

    public static Boolean getRollVerification() {
        return rollVerification;
    }

    public void setRollVerification(Boolean rollVerification) {
        BaseConfig.rollVerification = rollVerification;
    }


}
