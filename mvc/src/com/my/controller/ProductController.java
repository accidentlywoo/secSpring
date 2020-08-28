package com.my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.service.ProductService;
import com.my.vo.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductService productSerive;
	
	@GetMapping(value = "/productList")
	public ModelAndView productList(){
		ModelAndView mnv = new ModelAndView();
		List<Product> list;
		try {
			list = productSerive.findAll();
			mnv.addObject("list", list);
			mnv.setViewName("productList");
		} catch (FindException e) {
			mnv.addObject("errorMsg", e.getMessage());
			mnv.setViewName("fail");
		}
		return mnv;
	}
	
	@GetMapping(value = "/productDetail")
	@ResponseBody
	public Product productDetail(@RequestParam(name="prod_no") String no) {
		try {
			return productSerive.findByNo(no);
		} catch (FindException e) {
			return new Product("", "", 0);
		}
	}
}
