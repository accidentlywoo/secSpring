package com.my.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.BoardDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public void write(Board board) throws AddException {
		if(board.getParent_no() != 0) {
			throw new AddException("부모 글번호가 필요없습니다.");
		}
		boardDAO.insert(board);
	}
	public void reply(Board board) throws AddException{
		if(board.getParent_no() == 0) {
			throw new AddException("부모 글번호가 없습니다.");
		}
		boardDAO.insert(board);
	}
	public PageBean findAll(int page) throws FindException{
		if(page < 1) {
			page = 1;
			throw new FindException(page + " 페이지가 존재하지 않습니다.");
		}
		PageBean pageBean = new PageBean(page);
		pageBean.setList(boardDAO.selectAll(pageBean.getStartRow(), pageBean.getEndRow()));
		int rowCnt = boardDAO.selectCount(); // 게시판 총 수
		int totalPage = 0;
		if(rowCnt % PageBean.CNT_PER_PAGE > 0) {
			 totalPage =  rowCnt / PageBean.CNT_PER_PAGE+ 1;
		}else {
			 totalPage =  rowCnt / PageBean.CNT_PER_PAGE;
		}
		 // 총페이지 수
		pageBean.setTotalPage(totalPage);
		pageBean.setStartPage(page);
		pageBean.setEndPage(totalPage);
		
		return pageBean;
	}
	public Optional<Board> findByNo(int board_no) throws FindException{
		return boardDAO.selectByNo(board_no);
	}
}
