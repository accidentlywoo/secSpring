package com.my.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;
import com.my.vo.Customer;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "id") String id,
			@RequestParam(name = "pwd") String pwd,
			HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		try {
			customerService.login(id, pwd);
			mnv.setViewName("/success");
			session.setAttribute("loginInfo", id);
		} catch (FindException e) {
			mnv.setViewName("/fail");
		}
		return mnv;
	}
	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		String sessionId = (String) session.getAttribute("loginInfo");
		if(sessionId == null ||sessionId.equals("")) {
			log.error("세션 정보가 없는데 로그인 상태임.");
			return mnv;
		}
		session.removeAttribute("loginInfo");
		mnv.setViewName("/success");
		return mnv;
	}
	
	@PostMapping(value = "idDupChk")
	public ModelAndView idDupChk(@RequestParam String id) {
		ModelAndView mnv = new ModelAndView();
		try {
			customerService.findById(id);
			mnv.setViewName("/success");
		} catch (FindException e) {
			mnv.setViewName("/fail");
		}
		return mnv;
	}
	//id=&pwd=&pwd=&name=&buildingno=&addr=
	@PostMapping(value = "signup")
	public ModelAndView signup(@RequestParam String id,
			@RequestParam String pwd, @RequestParam String name) {
		ModelAndView mnv = new ModelAndView();
		Customer customer = new Customer(id, pwd, name);
		try {
			customerService.add(customer);
			mnv.setViewName("/success");
		} catch (AddException | FindException e) {
			mnv.setViewName("/fail");
		}
		return mnv;
	}
}
