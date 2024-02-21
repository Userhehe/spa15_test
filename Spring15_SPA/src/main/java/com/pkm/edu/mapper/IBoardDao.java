package com.pkm.edu.mapper;

import java.util.List;
import java.util.Map;

import com.pkm.edu.vo.BoardVo;

public interface IBoardDao {
	
	
	public List<BoardVo> getAllBoardPage(Map<String, Object> map);
	
	public int getAllBoardCount(Map<String, Object> map);
	public int setBoardDelFlag(String seq);
	
	public BoardVo getOneBoard(String seq);
	public int setBoardUpdate(Map<String,Object> map);
	
	public int setReplyUpdate(BoardVo vo);
	public int setReplyInsert(BoardVo vo);
}
