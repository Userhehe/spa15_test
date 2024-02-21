package com.pkm.edu.mapper;

import java.util.Map;

import com.pkm.edu.vo.UserVo;

public interface IUserDao {

		public UserVo login(Map<String, Object> map);
}
