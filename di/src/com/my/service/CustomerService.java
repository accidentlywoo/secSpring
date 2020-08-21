package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

@Service
public class CustomerService {
	@Autowired
	@Qualifier(value = "customerDAOOracle")
	private CustomerDAO customerDAO;
	
	public CustomerService(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public void add(Customer customer) throws AddException, FindException{
		customerDAO.insert(customer);
	}
	
	public List<Customer> findAll() throws FindException{
		return customerDAO.selectAll();
	}
	
	public Customer findById(String id) throws FindException{ 
		return customerDAO.selectById(id);
	}

	public List<Customer> findByName(String word) throws FindException{ 
		return customerDAO.selectByName(word);
	}
	
	public void login(String id, String pwd) throws FindException{
		try { 
			Customer customer = customerDAO.selectById(id);
			
			if(!customer.getPwd().equals(pwd)) {
				throw new FindException("로그인 실패");
			}

		} catch (FindException e) {
			throw new FindException("로그인 실패");
		}
		System.out.println("로그인 성공");
	}
	public void  modify(Customer customer) throws ModifyException, FindException {
		try {
			customerDAO.update(customer);
		} catch (ModifyException e) {
			throw new ModifyException("정보 수정에 실패했습니다.");
		}
	}
	public void remove(Customer customer) throws RemoveException, FindException {
		try {
			customerDAO.delete(customer.getId());
		} catch (RemoveException e) {
			throw new RemoveException("삭제에 실패했습니다.");
		}
	}
}
