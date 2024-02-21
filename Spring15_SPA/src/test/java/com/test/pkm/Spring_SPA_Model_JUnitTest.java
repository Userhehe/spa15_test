package com.test.pkm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pkm.edu.service.IBoardService;
import com.pkm.edu.vo.BoardVo;
import com.pkm.edu.vo.UserVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Spring_SPA_Model_JUnitTest {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private IBoardService service;
	
	@Before
	public void test() {
		assertNotNull(sqlSession);
	}
	
//	@Test
//	public void loginTest() {
//		Map<String, Object> map
//			=new HashMap<String, Object>(){{
//				put("id", "snoopy");
//				put("password", "a1234");
//			}};
//			
//			UserVo loginVo =
//			sqlSession.selectOne("com.pkm.edu.mapper.UserDaoImpl.login", map);
//			assertNotNull(loginVo);
//	}
	

	@Test
	public void boardPageTest() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("first","1");
			put("last","5");
			put("auth","A");
			
		}};
		
		List<BoardVo> lists = service.getAllBoardPage(map);
		assertEquals(5, lists.size());
		
//		int allCnt = service.getAllBoardCount(map);
//		assertNotEquals(0, allCnt);
	}

}
