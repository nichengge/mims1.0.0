package com.dao.proxy;

import com.bean.Member;
import com.dao.MemberDao;
import com.dao.impl.MemberDaoImpl;
import com.dblink.DBConnection;

public class MemberDaoProxy implements MemberDao{

	DBConnection conn;
	MemberDao dao;
	public MemberDaoProxy(){
		conn=new DBConnection();
		dao=new MemberDaoImpl(conn.getConnection());
	}
	@Override
	public void addMemberInfo(Member m) {
		try{
			if(isExistUserInfo(m.getMid())){
			  
			}else{
				  dao.addMemberInfo(m);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}

	@Override
	public void deleteMemberInfo(String id) {
		try{
			dao.deleteMemberInfo(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}

	@Override
	public void updateMemberInfo(Member m) {
		try{
			dao.updateMemberInfo(m);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}

	@Override
	public String[][] searchAll() {
		String[][] s=null;
		try{
			s=dao.searchAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
		return s;
	}

	@Override
	public String[][] searchById(String id) {
		String[][] s=null;
		try{
			s=dao.searchById(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
		return s;
	}
	@Override
	public boolean isExistUserInfo(String id) {
		boolean flag=false;
		 try{
			 flag=dao.isExistUserInfo(id);
		 }catch(Exception e){
			e.printStackTrace();
		 }
		return flag;
	}

}
