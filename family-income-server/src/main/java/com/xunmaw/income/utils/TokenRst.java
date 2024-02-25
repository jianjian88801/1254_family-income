package com.xunmaw.income.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class TokenRst {


    public static void Rst(HttpServletResponse response, String msg) {
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=UTF8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(msg);
        writer.close();
    }
}
