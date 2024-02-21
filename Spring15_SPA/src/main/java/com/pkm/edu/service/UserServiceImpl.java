package com.pkm.edu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkm.edu.mapper.IUserDao;
import com.pkm.edu.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private	IUserDao dao;

	@Override
	public UserVo login(Map<String, Object> map) {
		return dao.login(map);
	}


}
