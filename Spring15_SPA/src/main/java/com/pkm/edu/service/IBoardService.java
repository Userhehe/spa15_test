package com.pkm.edu.service;

import java.util.List;
import java.util.Map;

import com.pkm.edu.vo.BoardVo;

public interface IBoardService {

	public List<BoardVo> getAllBoardPage(Map<String, Object> map);
	public int getAllBoardCount(Map<String, Object> map);
	
	public int setBoardDelFlag(String seq);
	
	public BoardVo getOneBoard(String seq);
	public int setBoardUpdate(Map<String,Object> map);
	
	public boolean setReply(BoardVo vo);
}

