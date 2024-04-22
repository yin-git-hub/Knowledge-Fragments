package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: yin7331
 * Date: 2023/10/20 23:53
 * Describe:
 */
public class UserList {
    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();

        userList.add(new User(1,"张三",18,"上海"));
        userList.add(new User(2,"王五",16,"上海"));
        userList.add(new User(3,"李四",20,"上海"));
        userList.add(new User(4,"张雷",22,"北京"));
        userList.add(new User(5,"张超",15,"深圳"));
        userList.add(new User(6,"李雷",24,"北京"));
        userList.add(new User(7,"王爷",21,"上海"));
        userList.add(new User(8,"张三丰",18,"广州"));
        userList.add(new User(9,"赵六",16,"广州"));
        userList.add(new User(10,"赵无极",26,"深圳"));

        return userList;
    }
}
