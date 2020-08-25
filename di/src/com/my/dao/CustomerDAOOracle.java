package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

@Repository
@Qualifier(value = "customerDAOOracle")
@Primary
public class CustomerDAOOracle implements CustomerDAO{
	@Autowired
	private SqlSessionFactory sessionFactory;
	@Override
	public void insert(Customer customer) throws AddException, DuplicatedException, FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			int result = session.insert("CustomerMapper.insert", customer);
			if(result < 1) {
				throw new FindException("고객 정보등록 실패");
			} 
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Customer> result = session.selectList("CustomerMapper.selectAll");
			if(result.size() < 1) {
				throw new FindException("고객 정보조회실패");
			} 
			return result;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Customer result = session.selectOne("CustomerMapper.selectById", id);
			if(result == null) {
				throw new FindException("고객 정보조회실패");
			} 
			return result;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Customer> selectByName(String word) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Customer> result = session.selectList("CustomerMapper.selectByName",word);
			if(result.size() < 1) {
				throw new FindException("고객 정보조회실패");
			} 
			return result;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public void update(Customer customer) throws ModifyException, FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			int result = session.update("CustomerMapper.update", customer);
			if(result < 1) {
				throw new FindException("고객 정보수정 실패");
			} 
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public void delete(String id) throws RemoveException, FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			int result = session.delete("CustomerMapper.delete", id);
			if(result < 1) {
				throw new FindException("고객 정보삭제 실패");
			} 
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
}
