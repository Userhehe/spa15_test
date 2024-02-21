package com.pkm.edu.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pkm.edu.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.pkm.edu.mapper.UserDaoImpl.";

	@Override
	public UserVo login(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"login",map);
	}
}
