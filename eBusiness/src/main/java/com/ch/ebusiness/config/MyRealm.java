package com.ch.ebusiness.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.repository.before.BUserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    BUserRepository bUserRepository;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行doGetAuthenticationInfo()---------->");

        Object principal = authenticationToken.getPrincipal();
        System.out.println("获得了principal这个用户" + "=====>" + principal);
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        System.out.println("获得的token是这个===========》"+userToken);
        String email = userToken.getUsername();
        System.out.println("获得的username是这个===========》"+email);
        LambdaQueryWrapper<BUser> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(BUser::getBemail, email);
        List<BUser> list = bUserRepository.selectList(lambdaQueryWrapper);
        if (list.size() <= 0) {
            return null;
        }
        System.out.println("The list contains =============>"+list.get(0));
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, list.get(0).getBpwd(), getName());


        return new SimpleAuthenticationInfo(principal,list.get(0).getBpwd(), getName());
    }
}
