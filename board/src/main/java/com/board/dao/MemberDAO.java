package com.board.dao;

import com.board.domain.MemberVO;

public interface MemberDAO {

	// 회원가입
	public void register(MemberVO vo) throws Exception;

	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;

	// 회원정보 수정
	public void memberUpdate(MemberVO vo) throws Exception;

	// 회원정보 삭제
	public void memberDeleter(MemberVO vo) throws Exception;
	
	// 패스워드 체크
	public int passChk(MemberVO vo) throws Exception;

}
