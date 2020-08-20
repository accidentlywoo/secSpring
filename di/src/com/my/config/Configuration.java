package com.my.config;

import org.springframework.context.annotation.Bean;

import com.my.vo.Product;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean(name = "product")
	public Product getProduct() {
		return new Product();
	};
}
