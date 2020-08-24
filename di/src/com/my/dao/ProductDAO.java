package com.my.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Product product = session.selectOne("ProductMapper.selectByNo", no);
			if(product == null) {
				throw new FindException("상품이 없습니다.");
			} 
			return product;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
	public List<Product> selectByName(String word) throws FindException{return null;}
	public List<Product> selectAll(int page) throws FindException{return null;}
	
	public List<Product> selectAll() throws FindException{
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Product> list = session.selectList("ProductMapper.selectAll"); //ArrayList형태로 반환
			if(list.size() == 0) {
				throw new FindException("상품이 없습니다.");
			} 
			return list;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
		
		
	}
}
