package com.pkm.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pkm.edu.mapper.IBoardDao;
import com.pkm.edu.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements IBoardService {

	@Autowired
	private IBoardDao dao;
	
	@Override
	public List<BoardVo> getAllBoardPage(Map<String, Object> map) {
		log.info("게시글 getAllBoardPage 전체조회 페이징: 범위 및 권한 \t{}" , map);
		return dao.getAllBoardPage(map);
	}
	@Override
	public int getAllBoardCount(Map<String, Object> map) {
		log.info("게시글 getAllBoardCount 전체게시글 갯수: 권한 \t{}" , map);
		return dao.getAllBoardCount(map);
	}
	@Override
	public int setBoardDelFlag(String seq) {
		log.info("게시글 setBoardDelFlag삭제 delflag변경 : seq \t {}", seq );
		return dao.setBoardDelFlag(seq);
	}
	@Override
	public BoardVo getOneBoard(String seq) {
		log.info("게시글 getOneBoard 수정내용확인 상세 조회 : seq \t {}",seq);
		return dao.getOneBoard(seq);
	}
	@Override
	public int setBoardUpdate(Map<String, Object> map) {
		log.info("게시글 setBoardUpdate 수정내용 입력: {}",map);
		return dao.setBoardUpdate(map);
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean setReply(BoardVo vo) {
		log.info("게시글 답글 업데이트 및 입력 : {}", vo);
		int n = dao.setReplyUpdate(vo);
		int m = dao.setReplyInsert(vo);
		return (m+n)>0 ?true : false;
	}
	
}
