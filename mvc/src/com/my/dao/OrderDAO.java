package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
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

	public void insert(OrderInfo info) throws AddException { // Transaction
		SqlSession session = null;
		try {
			session = sessionFactory.openSession(ExecutorType.BATCH, false);
			insertInfo(session, info);
			insertLines(session, info.getLines());
			session.commit();
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			session.close();
		}
	}

	private void insertInfo(SqlSession session, OrderInfo info) throws AddException {
		int result = session.insert("OrderMapper.insertInfo", info.getOrder_c().getId());
	}

	private void insertLines(SqlSession session, List<OrderLine> lines) throws AddException {
		for (OrderLine item : lines) {
			session.insert("OrderMapper.insertLines", item);
		}
	}

	public List<OrderInfo> selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<OrderInfo> result = session.selectList("OrderMapper.selectById", id);
			if (result.size() < 1) {
				throw new FindException("고객 정보등록 실패");
			}
			return result;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			session.rollback();
			throw new FindException(e.getMessage());
		} finally {
			session.close();
		}
	}
}
