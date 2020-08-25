package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	TestController(){
		System.out.println("TestController 객체 생성");
	}
	@RequestMapping(value = "/a.do")
	public void a() {
		System.out.println("a() 호출됨");
	}
}
