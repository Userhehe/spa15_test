package com.pkm.edu.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pkm.edu.service.IBoardService;
import com.pkm.edu.vo.BoardVo;
import com.pkm.edu.vo.PageVo;
import com.pkm.edu.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RESTPageController {

   @Autowired
   private IBoardService service;
   
   @PostMapping(value = "/page.do")
   public Map<String, Object> page(@RequestParam(name = "page") int selectPage,
                           HttpSession session,
                           Model model){
      log.info("RESTPageController 게시판 페이징 REST page.do {}", selectPage);
      log.info("Session 확인 {} \n {} ", session.getAttribute("loginVo"), session.getAttribute("row"));
      
      UserVo loginVo =  (UserVo)session.getAttribute("loginVo");
      PageVo pVo = (PageVo)session.getAttribute("row");
      
      pVo.setTotalCount(service.getAllBoardCount(new HashMap<String, Object>(){{put("auth",loginVo.getAuth());}}));
      pVo.setCountList(5);
      pVo.setCountPage(5);
      pVo.setTotalPage(pVo.getTotalCount());
      pVo.setPage(selectPage);
      pVo.setStagePage(selectPage);
      pVo.setEndPage(pVo.getCountPage());
      
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("first", pVo.getPage()*pVo.getCountList() - (pVo.getCountList()-1));
      map.put("last", pVo.getPage()*pVo.getCountList());
      
      List<BoardVo> lists = service.getAllBoardPage(map);
      
      
      
      Map<String, Object> result_map = new HashMap<String, Object>();
      result_map.put("lists", lists);   //자바스크립트에선 세션을 못받으니
      result_map.put("row", pVo);
      result_map.put("memId", loginVo.getId());
      
      return result_map;
   }
   
   
   
}