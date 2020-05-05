package com.board.dao;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardDAO {
	// 게시물 목록
	public List list() throws Exception;

	// 게시물 작성
	public void write(BoardVO vo) throws Exception;
}