package com.yswuyu.backend.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 手机号格式校验
 */
public class PhoneFormatUtils {

    private static final Pattern PATTERN =  Pattern.compile("^1\\d{10}$");

    public static Boolean checkPhoneFormat(String phone){
        if (StringUtils.isEmpty(phone)){
            return false;
        }

        if (PATTERN.matcher(phone).matches()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String phone = "1234567890";
        Boolean aBoolean = PhoneFormatUtils.checkPhoneFormat(phone);
        System.out.println(aBoolean);
    }
}
