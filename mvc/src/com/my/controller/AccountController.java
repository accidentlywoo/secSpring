package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dao.AccountDAO;

@Controller
public class AccountController {
	@Autowired
	private AccountDAO accountDao;
	
	@GetMapping(value = "/account/add")
	@ResponseBody
	public String add(String no, int balance) {
		//service.add() -> dao.insert()
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("account_no", "105");
		parameter.put("account_balance", 1000);
		accountDao.insert(parameter);
		return "등록 성공";
	}
}
