package com.factory;

import com.dao.MemberDao;
import com.dao.proxy.MemberDaoProxy;

public class MemberDaoFactory {
	public static MemberDao getMemberDaoInstance(){
		return new MemberDaoProxy();
	}
}
