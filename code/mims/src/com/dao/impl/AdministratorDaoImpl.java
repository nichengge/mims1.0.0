package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Administrator;
import com.dao.AdministratorDao;

public class AdministratorDaoImpl implements AdministratorDao{

	Connection conn;
	PreparedStatement sta;
	public AdministratorDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public String[] check(Administrator a) {
		String[] s=new String[2];
		String sql="select * from administrator where aid='"+a.getAid()+"'";
		try {
			this.sta=this.conn.prepareStatement(sql);
			ResultSet res=this.sta.executeQuery();
			if(res.next()){
				s[0]=res.getString("aid");
				s[1]=res.getString("apwd");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void changePassword(Administrator a) {
		String sql="update administrator set apwd='"+a.getApwd()+"' where aid='"+a.getAid()+"'";
		try {
			this.sta=this.conn.prepareStatement(sql);
			this.sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addAdministratorInfo(Administrator a) {
		
		String sql="insert into administrator values('"+a.getAid()+"','"+a.getApwd()+"')";
		try {
			this.sta=this.conn.prepareStatement(sql);
			this.sta.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
