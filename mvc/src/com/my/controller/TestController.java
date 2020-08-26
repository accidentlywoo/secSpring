package com.my.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.vo.Product;

@Controller
public class TestController {
	TestController() {
		System.out.println("TestController 객체 생성");
	}

	@RequestMapping(value = "/a.do")
	public void a() { // public 필수
		System.out.println("a() 호출됨");
	}

	@GetMapping("/b.do")
	public void b() { // public 필수
		System.out.println("b() 호출됨");
	}

	@PostMapping("/c.do")
	public void c() {
		System.out.println("c() 호출됨");
	}

	@GetMapping(value = "/d.do")
	public void d(String t, String p, int c) { // queryString 자동 형변롼
		System.out.println("d() 호출됨 : " + t + " , " + p + " , " + c);
	}
	// ~/d.do?p=a&c=1 -> t = null로 전달

	@GetMapping(value = "/e.do")
	public void e(String t, String p, int[] c) { // queryString 자동 형변롼
		System.out.println("d() 호출됨 : " + t + " , " + p + " , ");
		for (int i : c) {
			System.out.println(i);
		}
	}
	// ~/e.do?p=a& -> NPE 발생!
	// String, int 형변환은 자동으로 이루어진다.
	// NumberFormatException 발생하는 "a" 를 int로 받으면?
	// ->
	// org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

	@GetMapping("/f.do")
	public void f(@RequestParam(name = "first") String a,
			@RequestParam(name = "second", required = false, defaultValue = "0") int b) {
		System.out.println("f() 호출됨 : a = " + a + " , b = " + b);
		// 요청 전달 마름과 파라미터 이름이 다를 경우
		// ~/f.do?first=hello&second=5
		// ~/f.do?first=hello => MissingServletRequestParameterException
		// -> @RequestParam(name = "second", required = false, defaultValue = "0")
	}

	@GetMapping("/g.do")
	public void g(Product product) { // queryString 객체로 자동 형변환
		System.out.println("e() 호출됨 Product : " + product);
	}

	/*
	 * Controller의 메핑 메소드들은 리턴타입에 상관없이 ModelAndView객체가 생성되서 Dispatcher Servlet에
	 * 반환된다.
	 */
	@PostMapping("/h.do")
	public void h(@RequestBody String data) throws JsonMappingException, JsonProcessingException {
		System.out.println("f() 호출됨");
		System.out.println("data : " + data);
		ObjectMapper mapper = new ObjectMapper();
		List<Product> list = mapper.readValue(data, new TypeReference<List<Product>>() {});
		for(Product item : list) {
			System.out.println("item : "+item);
		}
	}
}
