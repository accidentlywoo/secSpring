package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.vo.Product;

@Repository(value = "productDAO")
public class ProductDAO {
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public void insert(Product product) throws AddException, DuplicatedException{}
	public Product selectByNo(String no) throws FindException{
		SqlSession session = sessionFactory.openSession();
		return session.selectOne("ProductMapper.selectByNo", no);
	}
	public List<Product> selectByName(String word) throws FindException{return null;}
	public List<Product> selectAll(int page) throws FindException{return null;}
	
	public List<Product> selectAll() throws FindException{
		SqlSession session = sessionFactory.openSession();
		return session.selectList("ProductMapper.selectAll");
	}
}
