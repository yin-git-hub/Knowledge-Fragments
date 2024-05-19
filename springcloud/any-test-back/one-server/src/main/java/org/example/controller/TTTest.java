package org.example.controller;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")

public class TTTest {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/test1")
    public List<User>  test1(){
        List<User> users = userMapper.selectList(null);
        return users ;
    }
}
