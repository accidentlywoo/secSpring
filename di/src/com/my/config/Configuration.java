package com.my.config;

import org.springframework.context.annotation.Bean;

import com.my.vo.Product;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean(name = "product")
	public Product getProduct() {
//		Product p = new Product();
//		p.setProd_no("C0001");
//		return p;// Setter Injection
		return new Product("C0001", "¾Æ¸Þ", 1000); // Constructor Injection
	};
}
