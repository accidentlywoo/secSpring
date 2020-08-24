package com.my.vo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor()
@Getter
@Setter
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	//private String name;
	@Autowired(required = false)
	private Postal postal;// 도로명 우편번호 정보
	@NonNull
	protected String name;
	@NonNull
	private String addr;
	public Person(@NonNull String name) {
		this.name = name;
	}
	public void printInfo() {
		System.out.println("Person [name=" + name + ", addr=" + addr + "]");
	}
}