package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.Credit;
import com.bean.Member;
import com.dao.CreditDao;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class CreditDaoImpl implements CreditDao{

	Connection conn;
	Statement sta;
	public CreditDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public String[][] searchAll() {
		String s[][]=null;
		int i=0;
		int row=0;
		String sql="select * from credit";
		try {
			this.sta=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet res=this.sta.executeQuery(sql);
			if(res.last()){
				row=res.getRow();
			}
			if(row==0){
				s=new String[1][2];
				s[0][0]="";
				s[0][1]="";
			}else{
				s=new String[row][2];
				res.first();
				res.previous();
				while(res.next()){
					s[i][0]=res.getString("creditType");
					s[i][1]=String.valueOf(res.getFloat("scale"));
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
	public void updateCreditType(Credit c) {
		String sql="update credit set scale="+c.getScale()+" where creditType='"+c.getCreditType()+"'";
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
	public String[][] getMemberInfo() {
		String[][] s=null;
		int i=0;
		int row=0;
		String sql="select * from member";
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
					s[i][0]=res.getString(9);
					s[i][1]=res.getString(1);
					s[i][2]=res.getString(5);
					s[i][3]=res.getString(7);
					s[i][4]=String.valueOf(res.getInt(12));
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
	public String[] searchById(String id) {
		String[] s=new String[6];
		String sql="select * from member where mid='"+id+"'";
		try {
			this.sta=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet res=this.sta.executeQuery(sql);
			while(res.next()){
				s[0]=res.getString(1);
				s[1]=res.getString(8);
				s[2]=res.getString(4);
				s[3]=res.getString(9);
				s[4]=res.getString(11);
				s[5]=res.getString(12);
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
	public void updateCredit(Member m) {
		String sql="update member set credits="+m.getCredits()+",mname='"+m.getMname()+"'where mid='"+m.getMid()+"'";
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
	public void deleteCredit(String id) {
		String sql="delete from member where mid='"+id+"'";
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
	public String[] searchCrditType() {
		String[] s=null;
		int i=0;
		int row=0;
		
		String sql="select * from credit";
		try {
			this.sta=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet res=this.sta.executeQuery(sql);
			if(res.last()){
				row=res.getRow();
			}			
			if(row==0){
				s=new String[1];
				s[0]="";
			}else{
				s=new String[row];
				res.first();
				res.previous();
				while(res.next()){
					s[i]=res.getString("creditType");
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
	public void addupCredit(String money, String registerTime,String credits,String id) {
		
		String sql="update member set consumeMoney=consumeMoney+'"+money+"',registerTime='"
		+registerTime+"',credits='"+credits+"' where mid='"+id+"'";
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
	public float getCreditProportion(String type) {
		float scale=0;
		String sql="select * from credit where creditType='"+type+"'";
		try {
			this.sta=this.conn.createStatement();
			ResultSet res=this.sta.executeQuery(sql);
			while(res.next()){
				scale=res.getFloat("scale");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scale;
	}

}
