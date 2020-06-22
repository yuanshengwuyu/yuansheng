package com.yswuyu.backend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Hankin
 * @date 2019/12/31 4:22 下午
 * @Version 1.0
 * @Description:
 */
public class ValidateUtil {

    /**
     * 手机号正则校验
     * @param phoneNum
     * @return
     */
    public static boolean checkMobilePhoneNum(String phoneNum) {
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNum);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }
}
