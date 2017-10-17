package com.sm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("loginPage")
	private String loginPage(){
		
		
		return "login";
	}
	
	
	@RequestMapping("/login")
	public String login(String username, String password, HttpSession session) {
		if (username == null || username.equals("")) {
			return "login";
		}
		// 将username放入session中
		session.setAttribute("username", username);

		// 重定向到商品列表页面
		return "redirect:/queryItem";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		// 清空session
		session.invalidate();

		// 重定向到登录页面
		return "redirect:/loginPage";
	}
	
	
}
