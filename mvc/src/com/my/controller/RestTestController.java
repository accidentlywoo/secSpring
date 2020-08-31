package com.my.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.vo.Product;

@RestController
public class RestTestController {

	@GetMapping(value = "/r.do", produces = MediaType.TEXT_PLAIN_VALUE)
	public String a() throws JsonProcessingException {
		return "{\"msg\":\"hello\", \"cnt\":5}";
	}
	
	@GetMapping(value = "/s.do", produces = "application/json; charset=UTF-8")
	public String b() {
		return "{\"msg\":\"테스트\", \"cnt\":5}";
	}
	
	@GetMapping(value = "/t.do")
	public Product c() {
		Product product = new Product("C0001", "아메리카노", 1000);
		return product;
	}
	
	@GetMapping(value = "/u.do")
	public List<Product> d(){
		List<Product> list = new ArrayList<>();
		list.add(new Product("C0001", "아메리카노", 1000));
		list.add(new Product("S0001", "팥빙수", 7000));
		return list;
	}
	
	// http://localhost/mvc/v.do?no=1 (X)
	// http://localhost/mvc/s.do/1 (0)
	@GetMapping(value = "/v.do/{no}", produces = MediaType.TEXT_PLAIN_VALUE)  // null일때 404 에러 발생
	public String e(@PathVariable(value = "no",
					required = false) int no ) {// unBoxing 상태이다.
		return "@PathVariacble : " + no;
	}
}
