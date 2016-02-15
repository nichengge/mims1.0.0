package com.dao.proxy;

import javax.swing.JOptionPane;

import com.bean.Staff;
import com.dao.StaffDao;
import com.dao.impl.StaffDaoImpl;
import com.dblink.DBConnection;

public class StaffDaoProxy implements StaffDao{

	DBConnection conn;
	StaffDao dao;
	public StaffDaoProxy(){
		conn=new DBConnection();
		dao=new StaffDaoImpl(conn.getConnection());
	}
	@Override
	public String[] check(Staff s) {
		String[] arr=new String[2];
		try{
			arr=dao.check(s);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
		return arr;
	}

	@Override
	public void addStaffInfo(Staff s) {
		try{
			if(isExistUserInfo(s.getSidNo())){
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(null, "该工号已经注册过!");
			}else{
				dao.addStaffInfo(s);
			}
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
	public void deleteStaffInfo(String id) {
		try{
			dao.deleteStaffInfo(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}

	@Override
	public void updateStaffInfo(Staff s) {
		try{
			dao.updateStaffInfo(s);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.conn.close();
		}
	}
	@Override
	public boolean isExistUserInfo(String id) {
		boolean flag=false;
		try{
			flag=dao.isExistUserInfo(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
