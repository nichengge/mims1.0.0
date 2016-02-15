package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.bean.Member;
import com.dao.MemberDao;

public class MemberDaoImpl implements MemberDao{

	Connection conn;
	Statement sta;
	public MemberDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public void addMemberInfo(Member m) {
		if(isExistUserInfo(m.getMid())){
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "该会员卡号已经存在!");
		}else{
			String sql="insert into member values('"+m.getMname()+"','"+m.getMsex()+"','"+m.getBirthday()+"','"+m.getTel()+"','"+m.getMobileNo()+"','"+m.getMailAddress()+"','"+
			m.getMail()+"','"+m.getMrank()+"','"+m.getMid()+"','"+m.getExaminer()+"','"+m.getRegisteTime()+"','"+m.getCredits()+"','"+m.getConsumeMoney()+"')";
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
	public void deleteMemberInfo(String id) {
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
	public void updateMemberInfo(Member m) {
		String sql="update member set mname='"+m.getMname()
		+"',mobileNo='"+m.getMobileNo()+"',credits="+m.getCredits()
		+",mrank='"+m.getMrank()+"' where mid='"+m.getMid()+"'";
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
	public String[][] searchAll() {
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
				res.first();
				res.previous();
				while(res.next()){
					s[i][0]=res.getString(9);
					s[i][1]=res.getString(1);
					s[i][2]=res.getString(5);
					s[i][3]=String.valueOf(res.getInt(12));
					s[i][4]=res.getString(8);
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
		String sql="select * from member where mid='"+id+"'";
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
				res.first();
				res.previous();
				while(res.next()){
					s[i][0]=res.getString(9);
					s[i][1]=res.getString(1);
					s[i][2]=res.getString(5);
					s[i][3]=String.valueOf(res.getInt(12));
					s[i][4]=res.getString(8);
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
	public boolean isExistUserInfo(String id) {
		boolean flag=false;
		String sql="select * from  member where mid='"+id+"'";
		try {
			this.sta=this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
