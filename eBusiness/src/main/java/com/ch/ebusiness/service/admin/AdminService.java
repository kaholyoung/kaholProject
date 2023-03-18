package com.ch.ebusiness.service.admin;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;

import com.ch.ebusiness.entity.AUser;

public interface AdminService extends IService<AUser> {
	public String login(AUser aUser, HttpSession session, Model model);
}
