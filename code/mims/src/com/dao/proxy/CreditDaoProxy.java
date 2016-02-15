package com.dao.proxy;

import com.bean.Credit;
import com.bean.Member;
import com.dao.CreditDao;
import com.dao.impl.CreditDaoImpl;
import com.dblink.DBConnection;

public class CreditDaoProxy implements CreditDao{

	DBConnection conn;
	CreditDao dao;
	public CreditDaoProxy(){
		conn=new DBConnection();
		dao=new CreditDaoImpl(conn.getConnection());
	}
	
	@Override
	public String[][] searchAll() {
		String[][] s=null;
		try{
			s=dao.searchAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void updateCreditType(Credit c) {
		try{
			dao.updateCreditType(c);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}
	
	@Override
	public String[][] getMemberInfo() {
		String s[][]=null;
		try{
			s=dao.getMemberInfo();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
		return s;
	}
	
	@Override
	public String[] searchById(String id) {
		String[] s=null;
		try{
			s=dao.searchById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
	
	@Override
	public void updateCredit(Member m) {
		try{
			dao.updateCredit(m);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}
	
	@Override
	public void deleteCredit(String id) {
		try{
			dao.deleteCredit(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}
	
	@Override
	public String[] searchCrditType() {
		String s[]=null;
		try{
			s=dao.searchCrditType();
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
	
	@Override
	public void addupCredit(String money, String registerTime,String credits,String id) {
		try{
			dao.addupCredit(money, registerTime,credits,id);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}
	
	@Override
	public float getCreditProportion(String type) {
		float scale=0;
		try{
			scale=dao.getCreditProportion(type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return scale;
	}

}
