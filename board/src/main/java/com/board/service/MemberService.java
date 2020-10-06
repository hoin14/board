package com.board.service;

import com.board.domain.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void register(MemberVO vo) throws Exception;
	
}
