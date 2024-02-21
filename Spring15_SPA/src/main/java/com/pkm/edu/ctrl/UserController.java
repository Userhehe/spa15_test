package com.pkm.edu.ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.pkm.SpringUtils;
import com.pkm.edu.service.IUserService;
import com.pkm.edu.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private IUserService service;
	
	@GetMapping(value = "/loginForm.do")
	public String loginForm() {
		log.info("UserController 로그인화면 이동");
		return "loginForm";
	}
	
	@PostMapping(value = "/loginCheckText.do")
	@ResponseBody
	public ResponseEntity<?> loginCheckText(/* @RequestBody UserVo vo */ 
	@RequestBody Map<String, Object> map,
			HttpSession session){
		//log.info("UserController 로그인 정보 조회 비동기식 처리 : {}", vo);
		log.info("UserController 로그인 정보 조회 비동기식 처리 : {}", map);
		
		UserVo uVo = service.login(map);
		if(uVo!=null) {
			session.setAttribute("loginVo", uVo);
	//		return ResponseEntity.ok(true);
			return ResponseEntity.ok("{\"isc\":\"true\"}");
		}else {
			return new ResponseEntity<String>("등록 오류 입니다.", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/login.do")
	public String loginSession(HttpSession session, HttpServletResponse response) throws IOException {
		if(session.getAttribute("loginVo")==null) {
			SpringUtils.responseAlert(response, "잘못된 접근 입니다.", "loginForm.do");
			return "";
		}else {
			
			return "redirect:boardList.do";
		}
	}
	
	@GetMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.do";
	}
}
