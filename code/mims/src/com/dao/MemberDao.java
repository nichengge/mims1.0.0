package com.dao;

import com.bean.Member;

public interface  MemberDao {

	public boolean isExistUserInfo(String id);
	public void addMemberInfo(Member m);
	public void deleteMemberInfo(String id);
	public void updateMemberInfo(Member m);
	public String[][] searchAll();
	public String[][] searchById(String id);
	
}
