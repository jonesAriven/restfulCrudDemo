package com.swdeve.springboot.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@PostMapping(value = "/user/login")
	// @RequestParam("username") String user 获取前台提交的 username 参数，
	// @RequestParam String user择获取 user 参数
	public String dashboard(@RequestParam("username") String username, @RequestParam("password") String password,
			Map<String, Object> map,HttpSession session) {
		if (StringUtils.isEmpty(username)) {
			map.put("msg", "用户名不能为空");
			return "login";
		} else if (StringUtils.isEmpty(password)) {
			map.put("msg", "密码不能为空");
			return "login";
		} else if (!username.equals("admin") || !password.equals("123456")) {
			map.put("msg", "用户名或密码错误");
			return "login";
		} else if (username.equals("admin") && password.equals("123456")) {
		    session.setAttribute("loginUser", username);
			//为防止登录成功后表达重复提交，重定向到main.html
			return "redirect:/main.html";
			// return "dashboard";
		} else {
			map.put("msg", "系统异常，请联系管理员");
			return "login";
		}

	}
}
