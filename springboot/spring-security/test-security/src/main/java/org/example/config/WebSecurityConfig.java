package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//开启SpringSecurity的默认行为
//@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        DBUserDetailsManager manager = new DBUserDetailsManager();
        return manager;
    }
//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http
//                .authorizeRequests()
                // 开放接口访问权限，不需要登录授权就可以访问
//                .antMatchers("/test/toLogin11").permitAll()
//                // 其余所有请求全部需要鉴权认证
//                .anyRequest().authenticated()
//        .and()
                .formLogin()
                // 设置登录页面的 URL
                .loginPage("/test/toLogin")
                // 设置登录请求的 URL，即表单提交的 URL
                .loginProcessingUrl("/test/userLogin")
                // 设置登录表单中用户名字段的参数名，默认为username
                .usernameParameter("admin")
                // 设置登录表单中密码字段的参数名，默认为password
                .passwordParameter("123456")
        ;
        return http.build();
    }
}