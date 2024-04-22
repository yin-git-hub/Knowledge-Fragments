package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 1、filter：输出ID大于6的user对象
        List<User> userList = UserList.getUserList();
        List<User> filetrUserList = userList
                .stream()
                .filter(user -> user.getId() > 6)
                .collect(Collectors.toList());


        Map<Integer, String> collect = userList.stream().collect(Collectors
                .toMap(User::getId, User::getName));
        Set<Map.Entry<Integer, String>> entries = collect.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry);
        }
        // filetrUserList.forEach(System.out::println);

        //2、map
        // 接受一个函数作为参数。这个函数会被应用到每个元素上，
        // 并将其映射成一个新的元素（使用映射一词，是因为它和转换类似，
        // 但其中的细微差别在于它是“创建一个新版本”而不是去“修改”）

        List<String> mapUserList = userList.stream()
                .map(user -> user.getName() + "用户")
                .collect(Collectors.toList());

        mapUserList.forEach(System.out::println);

        //3、distinct：去重

        List<String> distinctUsers = userList
                .stream()
                .map(User::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctUsers  = " + distinctUsers);
        distinctUsers.forEach(System.out::println);

        //4、sorted：排序，根据名字倒序

        userList.stream()
                .sorted(Comparator.comparing(User::getName).reversed())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //5、limit：取前5条数据

        userList.stream()
                .limit(5)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //6、skip：跳过第几条取后几条

        userList.stream()
                .skip(7)
                .collect(Collectors
                        .toList())
                .forEach(System.out::println);

        //7、flatMap：数据拆分一对多映射

        userList.stream()
                .flatMap(user -> Arrays.stream(user.getCity().split(",")))
                .forEach(System.out::println);

        //8、peek：对元素进行遍历处理，每个用户ID加1输出

        userList.stream()
                .peek(user -> user.setId(user.getId()+1))
                .forEach(System.out::println);

        // Collectors
        List<Integer> idList = userList.stream().map(User::getId).collect(Collectors.toList()) ;
        Map<Integer,String> userMap = userList.stream()
                .collect(Collectors.toMap(User::getId,User::getName));
        Set<String> citySet = userList.stream().map(User::getCity).collect(Collectors.toSet());
        long count = userList.stream().filter(user -> user.getId()>1).collect(Collectors.counting());
        Integer sumInt = userList.stream().filter(user -> user.getId()>2).collect(Collectors.summingInt(User::getId)) ;
        User maxId = userList.stream().collect(Collectors.minBy(Comparator.comparingInt(User::getId))).get() ;
        String joinCity = userList.stream().map(User::getCity).collect(Collectors.joining("||"));
        Map<String,List<User>> groupCity = userList.stream().collect(Collectors.groupingBy(User::getCity));


        System.out.println("============================="+maxId);
        List<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");
        //以"张"开头的控制台输出
        list.stream().filter(s->s.startsWith("张")).forEach(System.out::println);
        //长度为3的在控制台输出
        list.stream().filter(s->s.length() == 3).forEach(System.out::println);
        //以"张"开头的并长度为3的在控制台输出
        list.stream().filter(s->s.startsWith("张")).filter(s->s.length() == 3).forEach(System.out::println);

        //取前三个数据在控制台输出
        list.stream().limit(3).forEach(System.out::println);
        //跳过三个元素把剩余的元素的控制台输出
        list.stream().skip(3).forEach(System.out::println);
        //跳过两个元素，把剩下的元素中前两个元素在控制台输出
        list.stream().skip(2).limit(2).forEach(System.out::println);

        //取前4个组成一个流
        Stream<String> limit = list.stream().limit(4);
        //跳过两个数据组成一个流
        Stream<String> skip = list.stream().skip(2);
        //合并需求1和2流，并把结果在控制台输出
        Stream.concat(limit,skip).forEach(System.out::println);
        //合并需求1和2流，并把结果在控制台输出要求不能重复
        Stream.concat(limit,skip).distinct().forEach(System.out::println);

        //按照字母顺序排序在控制台输出
        list.stream().sorted().forEach(System.out::println);
        //按照字符串长度,然后按照字母顺序在控制台输出
        list.stream().sorted((s1,s2)->{
            int num = s1.length() - s2.length();
            return num == 0 ? s1.compareTo(s2) : num;
        }).forEach(System.out::println);

        List<String> list1 = new ArrayList<>();
        list1.add("01");
        list1.add("10");
        list1.add("20");
        list1.add("30");
        list1.add("40");
        list1.add("50");

        //对字符进行数据转换
        list1.stream().map(Integer::parseInt).forEach(System.out::println);
        //mapToInt 不仅有map属性还带有特殊方法，求和求平均数等操作
        list1.stream().mapToInt(s->Integer.parseInt(s)).forEach(System.out::println);
        //mapToInt 转换int并求和
        int sum = list1.stream().mapToInt(Integer::parseInt).sum();
        System.out.println(sum);

        //把集合中的元素在控制台输出控制台上
        list.stream().forEach(System.out::println);
        //把集合中的元素数量输出到控制台上
        long count1 = list.stream().count();
        System.out.println(count1);

        String[] arr = {"林青霞,33","张曼玉,28","柳岩,22"};
        Map<String, String> maps = Stream.of(arr).collect(Collectors.toMap(s -> s.split(",")[0], s1 -> s1.split(",")[1]));
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            System.out.println("姓名："+entry.getKey()+",年龄"+entry.getValue());
        }

    }
}
