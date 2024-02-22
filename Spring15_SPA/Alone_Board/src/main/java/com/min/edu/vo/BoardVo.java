package com.min.edu.vo;

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
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {

	private int seq     ;
	private String id      ;
	private String title   ;
	private String content ;
	private int depth   ;
	private int ref     ;
	private int step    ;
	private String delflag ;
	private String createat;
	
}
