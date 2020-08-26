package com.my.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"id"},callSuper = false)//callSuper = false : default
public class Customer extends Person{
	private static final long serialVersionUID = 1L;
	@NonNull
	private String id;
	@NonNull
	transient private String pwd;
	public Customer(@NonNull String id, @NonNull String pwd, String name) {
		super(name);
		this.id = id;
		this.pwd = pwd;
	}
	
}
