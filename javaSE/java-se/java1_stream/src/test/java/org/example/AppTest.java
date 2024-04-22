package org.example;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {

    @Test
    public void test1(){
        // 1、filter：输出ID大于6的user对象
        List<User> userList = UserList.getUserList();
        List<Integer> idList = userList.stream()
                .map(User::getId)
                .collect(Collectors.toList()) ;
        Map<Integer,String> userMap = userList.stream()
                .collect(Collectors.toMap(User::getId,User::getName));
        Set<String> citySet = userList.stream()
                .map(User::getCity)
                .collect(Collectors.toSet());
        long count = userList.stream()
                .filter(user -> user.getId()>1)
                .collect(Collectors.counting());
        Integer sumInt = userList.stream()
                .filter(user -> user.getId()>2)
                .collect(Collectors.summingInt(User::getId)) ;
        User maxId = userList.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(User::getId)))
                .get() ;
        String joinCity = userList.stream()
                .map(User::getCity)
                .collect(Collectors.joining("||"));
        Map<String,List<User>> groupCity = userList.stream()
                .collect(Collectors.groupingBy(User::getCity));
    }
}
