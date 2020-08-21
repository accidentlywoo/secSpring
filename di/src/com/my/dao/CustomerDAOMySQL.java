package com.my.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

@Repository
public class CustomerDAOMySQL implements CustomerDAO{
	@Autowired
	private DataSource ds;
	@Override
	public void insert(Customer customer) throws AddException, DuplicatedException, FindException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer selectById(String id) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> selectByName(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Customer customer) throws ModifyException, FindException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) throws RemoveException, FindException {
		// TODO Auto-generated method stub
		
	}

}
