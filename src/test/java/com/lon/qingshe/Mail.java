package com.lon.qingshe;

import com.lon.qingshe.util.SendmailUtil;
import org.junit.Test;

public class Mail {


    @Test
    public static void main(String[] args) {
        try {
            SendmailUtil.sendEmail("820725915@qq.com","验证码","156745");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
