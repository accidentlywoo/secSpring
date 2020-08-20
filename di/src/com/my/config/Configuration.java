package com.my.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;

import com.my.vo.Customer;
import com.my.vo.MapVO;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;
import com.my.vo.PropertiesVO;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean(name = "product")
	public Product getProduct() {
//		Product p = new Product();
//		p.setProd_no("C0001");
//		return p;// Setter Injection
		return new Product("C0001", "테스트", 1000); // Constructor Injection
	};
	
	@Bean(name = "line")
	public OrderLine getLine() {
		OrderLine line = new OrderLine();
		line.setOrder_no(1);
		line.setOrder_p(getProduct());
		line.setOrder_quantity(5);
		return line;
	}
	@Bean(name = "line2")
	public OrderLine getLine2() {
		OrderLine line = new OrderLine();
		line.setOrder_no(1);
		line.setOrder_p(getProduct());
		line.setOrder_quantity(2);
		return line;
	}
	
	@Bean(name = "customer")
	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId("id1");
		customer.setPwd("p1");
		customer.setName("홍길동");
		customer.setAddr("");
		return customer;
	}
	
	@Bean(name = "info")
	public OrderInfo getInfo() {
		OrderInfo info = new OrderInfo();
		info.setOrder_c(getCustomer());
		List<OrderLine> list = new ArrayList<>();
		list.add(getLine());
		list.add(getLine2());
		return info;
	}
	
	@Bean(name = "mapvo")
	public MapVO getMapVo() {
		MapVO mapVO = new MapVO();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		mapVO.setMap(map);
		return mapVO;
	}
	
	@Bean(name = "propertiesvo")
	public PropertiesVO getPropertiesvo() {
		Properties properties = new Properties();
		properties.put("controller", "controller.properties");
		properties.put("dao", "dao.properties");
		properties.put("msg", "msg.properties");
		PropertiesVO result = new PropertiesVO(properties);
		return result;
	}
	
}
