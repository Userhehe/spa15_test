package com.pkm.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.pkm.SpringUtils;
import com.pkm.edu.service.IBoardService;
import com.pkm.edu.vo.BoardVo;
import com.pkm.edu.vo.PageVo;
import com.pkm.edu.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private IBoardService service;
	
	@GetMapping(value = "/boardList.do")
	public String BoardList(Model model, HttpSession session,
							@RequestParam(required = false, defaultValue = "1") String page) {//페이징 처리를 하기 위함 이걸해주면 if 페이지가 null이라면~~ 안써도됨 페이지가 1페이지로 가게끔 함 
		log.info("BoardController 게시글 전체값을 조회하고 이동하는 boardList : 현재페이지{}",page);
		UserVo loginVo = (UserVo)session.getAttribute("loginVo");
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("first", "1");
			put("last", "5");
			put("auth", loginVo.getAuth());
		}};

		
		
		PageVo pVo = null;
		if(session.getAttribute("row")==null) {
			pVo=new PageVo();
			session.setAttribute("row", pVo);
		}else {
			pVo = (PageVo)session.getAttribute("row");
			page = String.valueOf(pVo.getPage());
		}
		
		log.info("현재 페이지 : {}", page);
		int selectPage = Integer.parseInt(page);
		log.info("현재 페이지 : {}",selectPage);
	
		
		pVo.setTotalCount(service.getAllBoardCount(map));
		pVo.setCountList(5);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalCount());
		pVo.setPage(selectPage);
		pVo.setStagePage(selectPage);
		pVo.setEndPage(pVo.getCountPage());
		
		map.put("first", pVo.getPage()*pVo.getCountList()-(pVo.getCountList()-1));
		map.put("last", pVo.getPage()*pVo.getCountList());
		
		List<BoardVo> lists = service.getAllBoardPage(map);
		model.addAttribute("lists", lists);
		model.addAttribute("page", pVo);
		

		return "boardList";
	}
	
	
	@GetMapping(value = "/boardDelete.do")
	public String setBoardDelFlag(String seq) throws IOException {
		log.info("BoardController 게시글 삭제 처리 setBoardDelFlag : {}", seq);
		int n = service.setBoardDelFlag(seq);
		if(n ==1) {
			return "redirect:/boardList.do";
		}else {
			SpringUtils.responseAlert(null, "잘못된 삭제요청", "logout.do");
			return null;
		}
	}
}
