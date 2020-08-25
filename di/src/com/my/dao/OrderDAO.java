package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;

@Repository
public class OrderDAO {
	@Autowired
	private SqlSessionFactory sessionFactory;
	public void insert(OrderInfo info) throws AddException{ //Transaction
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			insertInfo(info);
			insertLines(info.getLines());
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new AddException(e.getMessage());
		}finally {
			session.close();
		}
	}
	private void insertInfo(OrderInfo info) throws AddException{
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			int result = session.insert("OrderMapper.insertInfo", info.getOrder_c().getId());
			if(result < 1) {
				throw new AddException("고객 정보등록 실패");
			} 
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new AddException(e.getMessage());
		}finally {
			session.close();
		}
	}
	private void insertLines(List<OrderLine> lines) throws AddException{
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			int result = 0;
			for(OrderLine item : lines) {
				session.insert("OrderMapper.insertLines", item);
				result++;
			}
			
			if(result < 1) {
				throw new AddException("고객 정보등록 실패");
			} 
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new AddException(e.getMessage());
		}finally {
			session.close();
		}
	}
	public List<OrderInfo> selectById(String id) throws FindException{
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<OrderInfo> result = session.selectList("OrderMapper.selectById", id);
			if(result.size() < 1) {
				throw new FindException("고객 정보등록 실패");
			} 
			return result;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
}
