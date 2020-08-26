package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.vo.Product;

@Controller
//@RestController
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
	 * Controller의 메핑 메소드들은 리턴타입에(Model, Map, String, View,....) 상관없이 
	 * ModelAndView객체가 생성되서 DispatcherServlet에
	 * 반환된다.
	 */
	@PostMapping("/h.do")
	public void h(@RequestBody String data) {
		System.out.println("f() 호출됨");
		System.out.println("data : " + data);
		ObjectMapper mapper = new ObjectMapper();
		List<Product> list;
		try {
			list = mapper.readValue(data, new TypeReference<List<Product>>() {});
			for(Product item : list) 
				System.out.println("item : "+item);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/i.do")
	public ModelAndView i() {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("errorMsg", "test"); // Model 객체는 request 객체와 다르다.
		// DispatcherServlet이 Model의 Attribute를 request의 Attribute에 세팅해준다.
		mnv.setViewName("/fail"); // default : forword
		// mnv.setViewName("redirect:/fail.jsp"); redirect 설정하는 방법
		return mnv;
	}
	
	@GetMapping("/j.do")
	public String j() {
		return "/fail";
	}
	@GetMapping("/l-resolver.do")
	public void l() {
		// return Type이 void일 경우 ViewResolver가  url(여기에서 'l-resolver')기반으로 결정된 뷰를 보여준다.
	}
	
	//Json 객체로 return 하기
	@GetMapping("/n.do")
	@ResponseBody // ViewResolver로 가는 경로 차단. @RestController는 전체 Mapping메소드에서 ViewResolver 차단.
	public Product n() {
		return new Product("C0001", "아메리카노", 1000);
	}
	
	@GetMapping("/m.do")
	@ResponseBody
	public String m() {
		return "{\"id\":\"id1\"}";
	}
	@GetMapping("/o.do")
	public void o(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	}
	
	@GetMapping("p.do")
	public Map<String, Object> p(@ModelAttribute(name = "product")Product product) {
		Map<String, Object> attrs = new HashMap<String, Object>();
//		attrs.put("product", product);
		attrs.put("test", "hello");
		return attrs; // p.jsp로 Resolve될 것
	}
}
