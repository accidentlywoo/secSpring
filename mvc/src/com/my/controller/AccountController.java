package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
	@Autowired
	private SqlSessionFactory sqlseeionFactory;
	
	@GetMapping(value = "/account/add")
	@ResponseBody
	public String add(String no, int balance) {
		//service.add() -> dao.insert()
		SqlSession session = sqlseeionFactory.openSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("account_no", "101");
		parameter.put("account_balance", 1000);
		
		session.insert("AccountMapper.insert", parameter);
		
		throw new RuntimeException("rollback");
//		return "insert account";
		
	}
}
