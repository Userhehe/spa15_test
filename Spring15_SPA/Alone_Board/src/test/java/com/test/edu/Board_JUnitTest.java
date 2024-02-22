package com.test.edu;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.dao.IBoardService;
import com.min.edu.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@Slf4j
public class Board_JUnitTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private IBoardService service;
	
	@Test
	public void test() {
		BasicDataSource datasource = context.getBean("dataSource", BasicDataSource.class);
		assertNotNull(datasource);
		
		SqlSessionFactory factory = context.getBean("sqlSessionFactoryBean", SqlSessionFactory.class);
		assertNotNull(factory);
		
		assertNotNull(session);
		
		
		List<BoardVo> lists = service.AllBoardList();
		System.out.println(lists);
		assertNotEquals(0, lists.size());
		
		
		
	}

}
