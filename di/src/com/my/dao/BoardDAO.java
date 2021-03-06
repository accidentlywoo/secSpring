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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.Board;

@Repository
public class BoardDAO {
	@Autowired
	private DataSource ds;
//	public static final int CNT_PER_PAGE = 3;
	public void insert(Board board) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSql = null;
		try {
			insertSql = "INSERT INTO board " + "VALUES(board_seq.NEXTVAL, ?,?,?,SYSTIMESTAMP,?)";
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertSql);
			pstmt.setLong(1, board.getParent_no());
			pstmt.setString(2, board.getBoard_title());
			pstmt.setString(3, board.getBoard_writer());
			pstmt.setString(4, board.getBoard_content());
			pstmt.executeUpdate();
		} catch ( SQLException e) {
			throw new AddException(e.getMessage());
		}
	}

	public List<Board> selectAll(int startRow,int endRow) throws FindException {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet resultSet = null;
		try {

			String pageSql = "SELECT  b.*\r\n" 
					+ "        FROM (SELECT ROWNUM list,level, a.*\r\n"
					+ "                FROM (SELECT * \r\n"
					+ "                        FROM board\r\n"
					+ "                        ORDER BY board_no DESC) a\r\n"
					+ "                START WITH parent_no = 0\r\n"
					+ "                CONNECT BY PRIOR board_no = parent_no\r\n"
					+ "                ORDER SIBLINGS BY a.board_no DESC) b\r\n" + "WHERE list BETWEEN ? AND ?";
			con = ds.getConnection();
			pstm = con.prepareStatement(pageSql);
			// page가 1이면 startrow 1 endRow 3
//			int startRow = (page -1)* CNT_PER_PAGE +1;
//			int endRow = startRow +2;
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);
			resultSet = pstm.executeQuery();
			List<Board> list = new ArrayList<Board>();
			while (resultSet.next()) {
				long board_no = resultSet.getLong("BOARD_NO");
				long parent_no = resultSet.getLong("PARENT_NO");
				 String board_title = resultSet.getString("BOARD_TITLE");
				 String board_writer =  resultSet.getString("BOARD_WRITER");
				 Date board_dt = resultSet.getDate("BOARD_DT");
				 String board_content = resultSet.getString("BOARD_CONTENT");
				 int level = resultSet.getInt("LEVEL");
				 Board board = new Board(board_no, parent_no, board_title, board_writer, board_dt, board_content, level);
				 list.add(board);
			}
			if(list.size() == 0) {
				throw new FindException("결과가 없음");
			}
			return list;
		} catch ( SQLException e) {
			e.printStackTrace();
			throw new FindException();
		}
	}
	public int selectCount() throws FindException{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet resultSet = null;
		try {
			String pageSql = "select count(*) cnt from board";
			con = ds.getConnection();
			pstm = con.prepareStatement(pageSql);
			resultSet = pstm.executeQuery();
			if(!resultSet.next()) {
				throw new FindException("게시글이 없습니다.");
			}
			return resultSet.getInt("cnt");
		}catch (Exception e) {
			e.printStackTrace();
			throw new FindException("error");
		}
	}

	public Optional<Board> selectByNo(int board_no) {
		String sql = "select * from board where board_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Board board = new Board(
						rs.getLong("board_no"),
						rs.getLong("parent_no"),
						rs.getString("board_title"),
						rs.getString("board_writer")
						,rs.getDate("board_dt"),
						rs.getString("board_content"));
				return Optional.of(board);
			}else {
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
