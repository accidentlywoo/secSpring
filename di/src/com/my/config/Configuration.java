package com.my.config;

import org.springframework.context.annotation.Bean;

import com.my.vo.OrderLine;
import com.my.vo.Product;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean(name = "product")
	public Product getProduct() {
//		Product p = new Product();
//		p.setProd_no("C0001");
//		return p;// Setter Injection
		return new Product("C0001", "Å×½ºÆ®", 1000); // Constructor Injection
	};
	
	@Bean(name = "line")
	public OrderLine getLine() {
		OrderLine line = new OrderLine();
		line.setOrder_no(1);
		line.setOrder_p(getProduct());
		line.setOrder_quantity(5);
		return line;
	}
}
