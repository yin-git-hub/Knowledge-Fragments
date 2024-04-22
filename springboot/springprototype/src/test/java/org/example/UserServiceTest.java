package org.example;

import org.junit.Before;
import org.junit.Test;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 public class UserServiceTest {
    // 声明要测试的bean



    @Test
    public void testComponentName() {
         ApplicationContext iocContainer = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 在测试方法中验证属性是否正确注入
        UserService happyComponent = (UserService) iocContainer.getBean("userService");
        System.out.println("happyComponent = " + happyComponent);
    }
}
