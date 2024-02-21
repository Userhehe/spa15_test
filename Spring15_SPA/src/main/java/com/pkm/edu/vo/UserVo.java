package com.pkm.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
	private String id      ;
	private String name    ;
	private String password;
	private String email   ;
	private String auth    ;
	private String enabled ;
	private String joindate;
	
	//Map을 사용하지 않기 위해서 만들엇음... map이 더 편해....
	private String opt;
	private String keyword;
}
