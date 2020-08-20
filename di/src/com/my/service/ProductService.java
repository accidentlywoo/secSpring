package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productDAO;
	
//	public ProductService() {
//		productDAO = new ProductDAO();
//	}
	public Product findByNo(String prod_no) throws FindException {
		return productDAO.selectByNo(prod_no);
	}
	public List<Product> findAll() throws FindException{
		return productDAO.selectAll();
	}
}
