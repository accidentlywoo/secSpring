package com.my.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.service.BoardService;
import com.my.vo.Board;

import lombok.RequiredArgsConstructor;

//@RestController
@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping(value = "/boardRest")
	public String root() {
		return "boardRest";
	}
	@GetMapping(value = { "/list/{currentPage}", "/list" })
	@ResponseBody
	public ResponseEntity<PageBean> list(@PathVariable(value = "currentPage", required = false) Optional<Integer> cp) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(boardService.findAll(cp.orElse(1)));
		} catch (FindException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(value = "/detail/{board_no}")
	public Board detail(@PathVariable(value = "board_no") Integer no) {
		try {
			return boardService.findByNo(no).orElse(new Board());
		} catch (FindException e) {
			return new Board();//Exception?
		}
	}
}
