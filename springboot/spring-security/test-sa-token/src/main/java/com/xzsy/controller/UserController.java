package com.xzsy.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
//    @GetMapping("doLogin")
//    public String doLogin(String username, String password) {
//        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
//        if("zhang".equals(username) && "123456".equals(password)) {
//            StpUtil.login(10001);
//            return "登录成功";
//        }
//        return "登录失败";
//    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 登录接口
    @RequestMapping("doLogin")
    public SaResult doLogin() {
        // 第1步，先登录上
        StpUtil.login(10001, new SaLoginModel()
                .setDevice("PC")
        );
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 第3步，返回给前端
        return SaResult.data(tokenInfo);
    }
}
