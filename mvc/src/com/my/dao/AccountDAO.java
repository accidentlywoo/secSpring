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
	
//	@Transactional(propagation = Propagation.MANDATORY)
	@Transactional(rollbackFor = Exception.class)//Propagation.REQUIRED 기본 세팅
	public int insert(Map<String, Object> param) throws Exception {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.selectOne("AccountMapper.insert", param);
			throw new Exception("Checked Exception은 컴파일 시점에 체크되고, 기본은 롤백되지 않는다.");
		}catch (RuntimeException e) {
			throw new RuntimeException("rollback");
		}finally {
			session.close();
		}
	}
}
