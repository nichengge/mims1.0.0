package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.bean.Staff;
import com.dao.StaffDao;

public class StaffDaoImpl implements StaffDao{


	Connection conn;
	Statement sta;
	public StaffDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public String[] check(Staff s) {
		String[] arr=new String[2];
		String sql="select * from staff where sidNo='"+s.getSidNo()+"'and spwd='"+s.getSpwd()+"'";
		try {
			this.sta=this.conn.createStatement();
			ResultSet res=this.sta.executeQuery(sql);
			if(res.next()){
				arr[0]=res.getString("sidNo");
				arr[1]=res.getString("spwd");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	@Override
	public void addStaffInfo(Staff s) {
		if(isExistUserInfo(s.getSidNo())){
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "改工号已经被注册过!");
		}else{
			String sql="insert into staff values('"+s.getSidNo()+"','"+s.getSname()+"','"+s.getSsex()+"','"
			+s.getSpwd()+"','"+s.getTel()+"','"+s.getMobileNo()+"','"+s.getRegisterTime()+"')";
			try {
				this.sta=this.conn.createStatement();
				this.sta.executeUpdate(sql);
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

	@Override
	public String[][] searchAll() {
		String[][] s=null;
		int i=0;
		int row=0;
		String sql="select * from staff";
		try {
			this.sta=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet res=this.sta.executeQuery(sql);
			if(res.last()){
				row=res.getRow();
			}
			
			if(row==0){
				s=new String[1][5];
				s[0][0]="";
				s[0][1]="";
				s[0][2]="";
				s[0][3]="";
				s[0][4]="";
			}else{
				s=new String[row][5];
				res.first();res.previous();
				while(res.next()){
					s[i][0]=res.getString(1);
					s[i][1]=res.getString(2);
					s[i][2]=res.getString(3);
					s[i][3]=res.getString(6);
					s[i][4]=String.valueOf(res.getInt(5));
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public String[][] searchById(String id) {
		String[][] s=null;
		int i=0;
		int row=0;
		String sql="select * from staff where sidNo='"+id+"'";
		try {
			this.sta=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet res=this.sta.executeQuery(sql);
			if(res.last()){
				row=res.getRow();
			}
			
			if(row==0){
				s=new String[1][5];
				s[0][0]="";
				s[0][1]="";
				s[0][2]="";
				s[0][3]="";
				s[0][4]="";
			}else{
				s=new String[row][5];
				res.first();res.previous();
				while(res.next()){
					s[i][0]=res.getString(1);
					s[i][1]=res.getString(2);
					s[i][2]=res.getString(3);
					s[i][3]=res.getString(6);
					s[i][4]=String.valueOf(res.getInt(5));
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public void deleteStaffInfo(String id) {
		String sql="delete from staff where sidNo='"+id+"'";
		try {
			this.sta=this.conn.createStatement();
			this.sta.executeUpdate(sql);
			
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
	public void updateStaffInfo(Staff s) {
		String sql="update  staff set sname='"+s.getSname()+"',ssex='"+s.getSsex()+
		"',mobileNo='"+s.getMobileNo()+"',tel='"+s.getTel()+"' where sidNo='"+s.getSidNo()+"'";
		try {
			this.sta=this.conn.createStatement();
			this.sta.executeUpdate(sql);
			
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
	public boolean isExistUserInfo(String id) {
		boolean flag=false;
		String sql="select * from staff where sidNo='"+id+"'";
		try {
			this.sta=this.conn.createStatement();
			ResultSet res=this.sta.executeQuery(sql);
			
			if(res.next()){
				flag=true;
			}else{
				flag=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}


}
