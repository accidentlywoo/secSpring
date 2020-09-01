package com.my.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Board;

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
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			List<Board> result = session.selectList("BoardMapper.selectAll", map);
			if(result.size() < 1) {
				throw new FindException("게시물 없음.");
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
