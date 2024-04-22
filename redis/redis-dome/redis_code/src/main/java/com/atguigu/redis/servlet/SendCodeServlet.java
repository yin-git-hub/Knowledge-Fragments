package com.atguigu.redis.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

//处理发送验证码的Servlet
@WebServlet("/SendCodeServlet")
public class SendCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    // 随机生成验证码的方法
    private String getCode(int len) {
        String code = "";
        for (int i = 0; i < len; i++) {
            int rand = new Random().nextInt(10);
            code += rand;
        }
        return code;
    }

    //获取当天剩余秒数的方法
    private long getTheLeftSeconds(){
        //获取现在的时间
        LocalTime now = LocalTime.now();
        //获取当日23点59分59秒的时间
        LocalTime end = LocalTime.of(23, 59, 59);
        //获取end与now相差的秒数
        long millis = Duration.between(now, end).toMillis()/1000;
        return millis;
    }
}
