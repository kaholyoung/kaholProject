package com.ch.ebusiness.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String ,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/**/toLogin","anon");
        filterMap.put("/**/login","anon");
        filterMap.put("/user/toRegister","anon");
        filterMap.put("/user/register","anon");



        filterMap.put("/user/**","authc");
        filterMap.put("/admin/**","authc");
        filterMap.put("/cart/**","authc");
        filterMap.put("/type/**","authc");
        filterMap.put("/goods/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        shiroFilterFactoryBean.setSuccessUrl("/");
        return shiroFilterFactoryBean;
    }



    //DefaultWebSecurityManager ：2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }


    //创建Realm对象，需要自定义
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }
}
