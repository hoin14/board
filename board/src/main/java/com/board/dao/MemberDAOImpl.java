package com.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sql;

	private static String namespace = "com.board.mappers.member";

	// 회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert(namespace + ".register", vo);
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return sql.selectOne(namespace + ".login", vo);
	}

	// Service에서 보낸 Parameter를 담아 Mapper쿼리에 넣어줌
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		sql.update(namespace + ".memberUpdate", vo);
	}

	@Override
	public void memberDeleter(MemberVO vo) throws Exception {
		sql.delete(namespace + ".memberDelete", vo);
	}

	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = sql.selectOne(namespace + ".passChk", vo);
		return result;
	}

}
