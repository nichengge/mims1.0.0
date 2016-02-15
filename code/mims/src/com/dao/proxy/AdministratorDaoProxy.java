package com.dao.proxy;

import com.bean.Administrator;
import com.dao.AdministratorDao;
import com.dao.impl.AdministratorDaoImpl;
import com.dblink.DBConnection;

public class AdministratorDaoProxy implements AdministratorDao{

	DBConnection conn;
	AdministratorDao dao;
	public AdministratorDaoProxy(){
		conn=new DBConnection();
		dao=new AdministratorDaoImpl(conn.getConnection());
	}
	
	@Override
	public String[] check(Administrator a) {
		String[] s=new String[2];
		try{
			s=this.dao.check(a);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
		return s;
	}

	@Override
	public void changePassword(Administrator a) {
		try{
			dao.changePassword(a);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}

	@Override
	public void addAdministratorInfo(Administrator a) {
		try{
			dao.addAdministratorInfo(a);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}

}
