package com.dao;

import com.bean.Credit;
import com.bean.Member;

public interface  CreditDao {
	
	public String[][] searchAll();
	public String[][] getMemberInfo();
	public String[] searchById(String id);
	public String[] searchCrditType();
	public float getCreditProportion(String type);
	public void updateCredit(Member m);
	public void deleteCredit(String id);
	public void updateCreditType(Credit c);
	public void addupCredit(String money,String registerTime,String credits,String id);
}
