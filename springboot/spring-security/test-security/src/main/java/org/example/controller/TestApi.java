package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApi {
 
    @GetMapping("/test1")
    public String test1(){
        return "111111";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/userLogin")
    public String userLogin( ) {
        return "index";
    }

    @GetMapping("/admin/toAddUser")
    public String toAddUser() {
        return "admin/addUser";
    }

    @GetMapping("/admin/toListUser")
    public String toListUser() {
        return "admin/listUser";
    }

    @GetMapping("/admin/toResetPwd")
    public String toResetPwd() {
        return "admin/resetPwd";
    }

    @GetMapping("/admin/toUpdateUser")
    public String toUpdateUser() {
        return "admin/updateUser";
    }

    @GetMapping("/user/toUpdatePwd")
    public String toUpdatePwd() {
        return "user/updatePwd";
    }

    @GetMapping("/noAccess")
    public String noAccess() {
        return "accessDenied";
    }
 

}
