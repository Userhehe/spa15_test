package com.pkm.edu.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pkm.edu.service.IBoardService;
import com.pkm.edu.vo.BoardVo;
import com.pkm.edu.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RESTBoardController {

		@Autowired
		private IBoardService service;
		
		//array 선언 방법
//		int[] a = new int[3]; 
//		int[] b = {1,2,3};
//		int[] c = new int[] {1,2,3};  -> 가장 많ㅇ ㅣ사용함
		
		/*
		 * jackson bind의 사용이 아닌 text를 JSON 형식으로 전달
		 * JSON의 형태를 만들어내는 객체는 Simple-json, Gson  //simple-json은 속도가 엄청 느리기 때문에 jackson bind를 사용함
		 */
		
		@PostMapping(path = {"/modifyForm.do","/replyForm.do"}, produces = "application/text; charset=UTF-8")
		public String modify(String seq) {
			log.info("RESTBoardController 게시판 수정 REST를 위한 seq 조회 : {}",seq);
			BoardVo vo = service.getOneBoard(seq);
			
			/*
			 1) Simple-json 사용방법
			JSONObject obj = new JSONObject();
			obj.put("a","가");
			obj.toJSONString();
			
			2) Gson사용방법
			Gson gson = new Gson();
			JsonObject gJsonObject = new JsonObject();
			gJsonObject.addProperty("a", "가");
			String gsonToString = gson.toJson(gJsonObject);
			*/
			

			Gson gson = new Gson();
			String voToJson = gson.toJson(vo);
			return voToJson;
		}
		
		@PostMapping(value = "/modify.do")
		public Map<String, Object> modify(@RequestParam Map<String, Object> map){
			log.info("RESTBoardController 게시판 수정 값 입력 : {}", map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			int n = service.setBoardUpdate(map);
			resultMap.put("isc",(n==1)? true : false);
			return resultMap;
		}
		
		@PostMapping(value = "/reply.do")
		public Map<String, Object> reply(BoardVo vo, HttpSession session){
			log.info("RESTBoardController 답글 입력 : {}" , vo);
			UserVo loginVo = (UserVo)session.getAttribute("loginVo");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			
			boolean isc = service.setReply(vo);
			resultMap.put("isc", isc);
			return resultMap;
		}
}
