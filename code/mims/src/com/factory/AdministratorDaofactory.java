package com.factory;

import com.dao.AdministratorDao;
import com.dao.proxy.AdministratorDaoProxy;


public class AdministratorDaofactory {

	public static AdministratorDao getAdministratorDaoInstance(){
		
		return new AdministratorDaoProxy();
	}

}
