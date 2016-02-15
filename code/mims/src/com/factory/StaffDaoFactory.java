package com.factory;

import com.dao.StaffDao;
import com.dao.proxy.StaffDaoProxy;

public class StaffDaoFactory {

	public static StaffDao getStaffDaoInstance(){
		return new StaffDaoProxy();
	}
}
