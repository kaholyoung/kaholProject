package com.ch.ebusiness.controller.before;
import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ch.ebusiness.repository.before.BUserRepository;
import com.ch.ebusiness.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.service.before.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private BUserRepository bUserRepository;
	@RequestMapping("/toRegister")
	public String toRegister(@ModelAttribute("bUser") BUser bUser) {
		return "user/register";
	}
	@RequestMapping("/toLogin")
	public String toLogin(@ModelAttribute("bUser") BUser bUser) {
		//@ModelAttribute("bUser")与th:object="${bUser}"相对应
		return "user/login";
	}


	@RequestMapping("/login")
	public String login( @ModelAttribute("bUser") @Validated BUser bUser,
						 BindingResult rs,
						 HttpSession session, Model model) {

		if(rs.hasErrors()){//验证失败
	        return "user/login";
	    }

		Subject subject = SecurityUtils.getSubject();
		bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
		UsernamePasswordToken token = new UsernamePasswordToken(bUser.getBemail(),bUser.getBpwd());
		if (subject.isAuthenticated()){
			return "redirect:/";
		}
		try{
			subject.login(token);
			LambdaQueryWrapper<BUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			lambdaQueryWrapper.eq(BUser::getBemail,String.valueOf(subject.getPrincipal()));
			List<BUser> bUsers = bUserRepository.selectList(lambdaQueryWrapper);
			bUser = bUsers.get(0);
			session.setAttribute("bUser", bUser);
			System.out.println("这个是session"+session.getAttribute("bUser"));
			return "redirect:/";
		}catch (UnknownAccountException e){
			model.addAttribute("errorMessage","用户名错误");
			return "user/login";

		}catch (IncorrectCredentialsException e){
			model.addAttribute("errorMessage","密码错误");
			return "user/login";
		}
//		return userService.login(bUser, session, model);
	}




	@RequestMapping("/isUse")
	@ResponseBody
	public String isUse(@RequestBody BUser bUser) {
		return userService.isUse(bUser);
	}

	@RequestMapping("/register")
	public String register(@ModelAttribute("bUser") @Validated BUser bUser,BindingResult rs) {
		if(rs.hasErrors()){//验证失败
	        return "user/register";
	    }
		return userService.register(bUser);
	}
}
