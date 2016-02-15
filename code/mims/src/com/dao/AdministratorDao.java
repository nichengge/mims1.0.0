package com.dao;

import com.bean.Administrator;

public interface AdministratorDao {

	public String[] check(Administrator a);
	public void changePassword(Administrator a);
	public void addAdministratorInfo(Administrator a);
	
}
