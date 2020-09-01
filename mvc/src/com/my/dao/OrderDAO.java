package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class OrderDAO {
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Transactional(rollbackFor = AddException.class)
	public void insert(OrderInfo info) throws AddException { // Transaction
		log.info("");
		try {
			insertInfo(info);
			insertLines(info.getLines());
		} catch (Exception e) { // SQLSyntexError로 잡지 않는다.
			throw new AddException(e.getMessage());
		} finally {
		}
	}

	private void insertInfo(OrderInfo info) throws AddException {
		SqlSession session = sessionFactory.openSession();
		try {
			session.insert("OrderMapper.insertInfo", info.getOrder_c().getId());
		} catch (Exception e) {
			throw new AddException(e.getMessage());
		}
	}

	private void insertLines(List<OrderLine> lines) throws AddException {
		SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
		try {
			for (OrderLine line : lines) {
				session.insert("OrderMapper.insertLines", line);
			}
			// MyBatis는 Batch 기능이없다. DB에서 Batch 작업을 도와주는 기능을 주현해야 한다.
			session.flushStatements();
		} catch (Exception e) {
			throw new AddException(e.getMessage());
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
