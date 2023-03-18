package com.ch.ebusiness.service.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.ebusiness.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ch.ebusiness.entity.AUser;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminRepository,AUser> implements AdminService {
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public String login(AUser aUser, HttpSession session, Model model) {
		List<AUser> list = adminRepository.login(aUser);
		if(list.size() > 0) {//登录成功
			session.setAttribute("auser", aUser);
			return "forward:/goods/selectAllGoodsByPage?currentPage=1&act=select";
		}else {//登录失败
			model.addAttribute("errorMessage", "用户名或密码错误！");
			return "admin/login";
		}
	}
}
