package com.board.domain;

import java.util.Date;

public class MemberVO {
	
//	CREATE TABLE `MR_MEMBER` (
//			`USERID` VARCHAR(40) NOT NULL,
//			`USERPASS` VARCHAR(100) NOT NULL,
//			`USERNAME` VARCHAR(40) NOT NULL,
//			`REGDATE` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
//			PRIMARY KEY (`USERID`)
//		);
	
	private String userId;
	private String userPass;
	private String userName;
	private Date regDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
