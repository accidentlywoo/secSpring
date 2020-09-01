package com.my.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDAO {
	@Autowired
	SqlSessionFactory sessionFactory;
	
	//@Transactional(propagation = Propagation.REQUIRED) // default
	@Transactional
	public int insert(Map<String, Object> param) {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.selectOne("AccountMapper.insert", param);
			throw new RuntimeException("rollback");
		}catch (Exception e) {
			throw new RuntimeException("rollback");
		}finally {
			session.close();
		}
	}
}
