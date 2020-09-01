package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Board;
import com.my.vo.Customer;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionFactory sessionFactory;
//	public static final int CNT_PER_PAGE = 3;
	public void insert(Board board) throws AddException {
	}

	public List<Board> selectAll(int startRow,int endRow) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Board> result = session.selectList("BoardMapper.selectAll");
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
	public int selectCount() throws FindException{
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			int result = session.selectOne("BoardMapper.selectCount");
			if(result < 1) {
				throw new FindException("고객 정보조회실패");
			} 
			return result;
		} catch (DataAccessException e) { // SQLSyntexError로 잡지 않는다.
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	public Optional<Board> selectByNo(int board_no) {
		return Optional.of(new Board());
	}
}
