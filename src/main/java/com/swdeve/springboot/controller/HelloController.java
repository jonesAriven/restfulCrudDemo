package com.swdeve.springboot.controller;


import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
//	@RequestMapping({"/","/index"})
//	public String index() {
//		return "index";
//	}
//	
	@RequestMapping("/success")
	public String success(Map<String,Object> map) {
		//classpath:/templates/success.html
		map.put("hello", "<h1>你好，这是一个成功页面</h1>");
		map.put("hi", "<h1>你好，这是一个成功页面</h1>");
		map.put("users", Arrays.asList("<h5>zhansan</h5>","<h5>lishi</h5>","<h5>wangwu</h5>"));
		return "success";
	}
}
