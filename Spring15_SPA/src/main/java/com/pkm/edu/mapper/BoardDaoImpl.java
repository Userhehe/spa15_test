package com.pkm.edu.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkm.edu.vo.BoardVo;

@Repository
public class BoardDaoImpl implements IBoardDao {

	@Autowired
	private SqlSessionTemplate SqlSession;
	private final String NS = "com.pkm.edu.mapper.BoardDaoImpl.";
	
	
	
	@Override
	public List<BoardVo> getAllBoardPage(Map<String, Object> map) {
		return SqlSession.selectList(NS+"getAllBoardPage", map);
	}
	
	public int getAllBoardCount(Map<String, Object> map) {
		return SqlSession.selectOne(NS+"getAllBoardCount", map);
	}

	@Override
	public int setBoardDelFlag(String seq) {
		return SqlSession.delete(NS+"setBoardDelFlag",seq);
	}

	@Override
	public BoardVo getOneBoard(String seq) {
		return SqlSession.selectOne(NS+"getOneBoard",seq);
	}

	@Override
	public int setBoardUpdate(Map<String, Object> map) {
		return SqlSession.update(NS+"setBoardUpdate",map);
	}

	@Override
	public int setReplyUpdate(BoardVo vo) {
		return SqlSession.update(NS+"setReplyUpdate", vo);
	}

	@Override
	public int setReplyInsert(BoardVo vo) {
		return SqlSession.insert(NS+"setReplyInsert",vo);
		
	}
	
	
	
}
