package com.factory;

import com.dao.CreditDao;
import com.dao.proxy.CreditDaoProxy;

public class CreditDaoFactory {

	public static CreditDao getCreditDaoInstance(){
		return new CreditDaoProxy();
	}
}
