package com.pkm.edu.service;

import java.util.Map;

import com.pkm.edu.vo.UserVo;

public interface IUserService {

	public UserVo login(Map<String, Object> map);
}
