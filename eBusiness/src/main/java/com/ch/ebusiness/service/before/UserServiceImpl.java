package com.ch.ebusiness.service.before;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ch.ebusiness.repository.before.BUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.util.MD5Util;
@Service
public class UserServiceImpl implements UserService {





	@Autowired
	private BUserRepository bUserRepository;

	@Override
	public String isUse(BUser bUser) {

		QueryWrapper qw = new QueryWrapper();
		qw.eq("bemail", bUser.getBemail());
		List list = bUserRepository.selectList(qw);

//		userRepository.isUse(bUser)

		if(list.size() > 0) {
			return "no";
		}
		return "ok";
	}
	@Override
	public String register(BUser bUser) {
		//对密码MD5加密
		bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));


		if(bUserRepository.insert(bUser) > 0) {
			return "user/login";
		}
		return "user/register";
	}


	@Override
	public String login(BUser bUser, HttpSession session, Model model) {
		//对密码MD5加密
		bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
		String rand = (String)session.getAttribute("rand");
		if(!rand.equalsIgnoreCase(bUser.getCode())) {
			model.addAttribute("errorMessage", "验证码错误！");
			return "user/login";
		}

//		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken(bUser.getBemail(),bUser.getBpwd());
//		if (subject.isAuthenticated()){
//			return "redirect:/";
//		}
//		try{
//			subject.login(token);
//			session.setAttribute("bUser", bUser);
//			return "redirect:/";
//		}catch (UnknownAccountException e){
//			model.addAttribute("errorMessage","用户名错误");
//			return "user/login";
//
//		}catch (IncorrectCredentialsException e){
//			model.addAttribute("errorMessage","密码错误");
//			return "user/login";
//		}

		QueryWrapper qw = new QueryWrapper();
		qw.eq("bemail",bUser.getBemail());
		qw.eq("bpwd",bUser.getBpwd());
		List list = bUserRepository.selectList(qw);


//		List<BUser> list = userRepository.login(bUser);
		if(list.size() > 0) {
			session.setAttribute("bUser", list.get(0));
			System.out.println("这个是session"+ session.getAttribute("bUser"));
			return "redirect:/";//到首页
		}
		model.addAttribute("errorMessage", "用户名或密码错误！");
		return "user/login";
	}

}
