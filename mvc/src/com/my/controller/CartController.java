package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.FindException;
import com.my.service.ProductService;
import com.my.vo.Product;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CartController {
	@Autowired
	private ProductService productService;
	//"prod_no=C0001&quantity=1"
	@PostMapping(value = "/putCart")
	public ModelAndView putCart(@RequestParam(name = "prod_no") String no,
			@RequestParam(name = "quantity") int qnt,
			HttpSession session
			) {
		ModelAndView mnv = new ModelAndView();
		Map<String, Integer> cart = new HashMap<String, Integer>();
		cart.put(no, qnt);
		session.setAttribute("cart", cart);
		mnv.setViewName("/success");
		return mnv;
	}
	
	@GetMapping(value = "/viewCart")
	public ModelAndView viewCart(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		if(cart.size() < 1) {
			mnv.addObject("errorMsg", "장바구니가 비었습니다.");
			mnv.setViewName("/fail");
			return mnv;
		}
		Map<Product, Integer> cartDetail = new HashMap<Product, Integer>();
		for(String prod_no : cart.keySet()) {
			try {
				cartDetail.put(productService.findByNo(prod_no), cart.get(prod_no));
			} catch (FindException e) {
				log.error(prod_no + "상품 상세정보 조회안됨");
			}
		}
		mnv.addObject("cartDetail", cartDetail);
		mnv.setViewName("/jsp/viewCart");
		return mnv;
	}
}
