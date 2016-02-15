package com.dao;

import com.bean.Staff;

public interface  StaffDao {

	public String[] check(Staff s);
	public boolean isExistUserInfo(String id);
	public void addStaffInfo(Staff s);
	public String[][] searchAll();
	public String[][] searchById(String id);
	public void deleteStaffInfo(String id);
	public void updateStaffInfo(Staff s);
	
}
