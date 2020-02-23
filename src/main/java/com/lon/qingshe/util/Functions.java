package com.lon.qingshe.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Functions {

    public static String md5(String raw, String salt) {
        return DigestUtils.md5DigestAsHex(
                (raw + salt).getBytes(StandardCharsets.UTF_8)
        );
    }

    public static String getVCode(){
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 6; i++){
            result += random.nextInt(10);
        }
        return result;
    }

    public static void invalidReturn(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-Type", "application/json");
        response.getWriter().println(
                JSON.toJSONString(JsonR.createFail("Invalid"))
        );
        response.getWriter().close();
    }

}
