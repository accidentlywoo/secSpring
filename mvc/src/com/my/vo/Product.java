package com.my.vo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "product")
public class Product {
	private String prod_no;
	private String prod_name;
	private int prod_price;
	
	
	public Product() {
		System.out.println("Product Default Constructor");
	}
	public Product(String prod_no, String prod_name, int prod_price) {
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
	}
	public String getProd_no() {
		return prod_no;
	}

	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
		System.out.println("Setter Injection Test");
	}


	public String getProd_name() {
		return prod_name;
	}


	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}


	public int getProd_price() {
		return prod_price;
	}


	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	
	@PostConstruct //javax.~ 자바 어노테이션 JDK 1.9~ Deprecated
	public void postConstructTest() {
		System.out.println("객체 생성 후 postConstructTest() 호출");
	}
	
	@PreDestroy //javax.~ 자바 어노테이션 
	public void preDestroyTest() {
		System.out.println("객체 소멸전 preDestroyTest() 호출");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (prod_no == null) {
			if (other.prod_no != null)
				return false;
		} else if (!prod_no.equals(other.prod_no))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [prod_no=" + prod_no + ", prod_name=" + prod_name + ", prod_price=" + prod_price + "]";
	}
}
