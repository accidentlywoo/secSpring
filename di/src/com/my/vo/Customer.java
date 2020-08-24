package com.my.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"id"},callSuper = false)//callSuper = false : default
public class Customer extends Person{
	private static final long serialVersionUID = 1L;
	private String id;
	transient private String pwd; 
}
