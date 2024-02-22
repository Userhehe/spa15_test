package com.min.edu.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.dao.IBoardService;
import com.min.edu.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private IBoardService service;
	
	@GetMapping(value = "/home.do")
	public String home(Model model) {
		List<BoardVo> lists = service.AllBoardList();
		model.addAttribute("lists",lists);
		log.info("HomeController 전체 리스트 조회 ");
		return "home";
	}
	
	
	
}
