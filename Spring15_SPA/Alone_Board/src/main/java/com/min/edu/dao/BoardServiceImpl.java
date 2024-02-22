package com.min.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.vo.BoardVo;

@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired
	public IBoardDao dao;
	
	@Override
	public List<BoardVo> AllBoardList() {
		return dao.AllBoardList();
	}

}
