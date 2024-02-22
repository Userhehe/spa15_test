package com.min.edu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDaoImpl implements IBoardDao {

	private final String NS = "com.min.edu.dao.BoardDaoImpl.";
	
	@Autowired
	public SqlSessionTemplate session;
	
	@Override
	public List<BoardVo> AllBoardList() {
		log.info("BoardDaoImpl AllBoardList");
		return session.selectList(NS+"AllBoardList");
	}

}
