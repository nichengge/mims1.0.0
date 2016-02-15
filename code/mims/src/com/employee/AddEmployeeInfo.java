package com.employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import com.bean.Staff;
import com.factory.StaffDaoFactory;

/**
 * 添加员工信息
 */
public class AddEmployeeInfo  extends JPanel implements ActionListener,ItemListener{

	StaffDaoFactory sd=new StaffDaoFactory();
	JOptionPane op=new JOptionPane();
	
	private JPanel p1=null;
	private JPanel p2=null;
	private JPanel p3=null;
	private JPanel p4=null;
	private JPanel p5=null;
	private JPanel p6=null;
	private JPanel p7=null;
	private JPanel p8=null;
	//工号
	private JTextField numText;
	private JLabel numLabel;
	
	private JTextField nameText;
	private JLabel nameLabel;
	
	private JComboBox sexCombobox;
	private JLabel sexLabel;
	
	private JTextField passwordText;
	private JLabel passwordLabel;
	//电话
	private JTextField telText;
	private JLabel telLabel;
	//手机号码
	private JTextField mobileNoText;
	private JLabel mobileNoLabel;
	//登记日期
	private JTextField registerTimeText;	
	private JLabel registerTimeLabel;
	
	private JButton addBtn=null;
	private JButton clearBtn=null;
	
	private String sex="女";
	
	/*
	 * 采用GridBagLayout布局
	 */
	private GridBagLayout gridL=null;
	private GridBagConstraints gridC=null;
	
	int width=16;//JTextField的宽度,便于代码的修改
		
	public AddEmployeeInfo(){ 
		init();
   }
	
	public void init(){
		
		setLayout(new GridLayout(8,1));
		
		 p1=new JPanel();
		 numLabel=new JLabel("工号:         ");
		 numText=new JTextField(width);
		 p1.add(numLabel);
		 p1.add(numText);
		 
		 p2=new JPanel();
		 nameLabel=new JLabel("姓名:         ");
		 nameText=new JTextField(width);
		 p2.add(nameLabel);
		 p2.add(nameText);
		 
		 p3=new JPanel();
		 sexLabel=new JLabel("性别:         ");
		 String arr[]={"女","男"};
		 sexCombobox=new JComboBox(arr);
		 JLabel l1 = new JLabel("                                        ");
		 p3.add(sexLabel);
		 p3.add(sexCombobox);
		 p3.add(l1);
		 
		 p4=new JPanel();
		 passwordLabel=new JLabel("密码:         ");
		 passwordText=new JTextField(width);
		 p4.add(passwordLabel);
		 p4.add(passwordText);
		 
		 p5=new JPanel();
		 telLabel=new JLabel("电话:         ");
		 telText=new JTextField(width);
		 p5.add(telLabel);
		 p5.add(telText);
		 
		 p6=new JPanel();
		 mobileNoLabel=new JLabel("手机号码:");
		 mobileNoText=new JTextField(width);
		 p6.add(mobileNoLabel);
		 p6.add(mobileNoText);
		 
		 p7=new JPanel();
		 registerTimeLabel=new JLabel("登记日期:");
		 registerTimeText=new JTextField(width);
		 p7.add(registerTimeLabel);
		 p7.add(registerTimeText);
		 
		 p8=new JPanel();
		 ImageIcon image1=new ImageIcon("../mims/images/add.png");
		 addBtn=new JButton("新增",image1);
		 addBtn.addActionListener(this);
		 ImageIcon image2=new ImageIcon("../mims/images/search.png");
		 clearBtn=new JButton("清除",image2);
		 p8.add(addBtn);
		 p8.add(clearBtn);
		 
		 add(p1);add(p2);add(p3);add(p4);
		 add(p5);add(p6);add(p7);add(p8);
	}

	/**
	 *重载方法说明：选中下拉菜单方法
	 *
	 */
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			sex=""+e.getItem();
		}
	}

	/**
	 *重载方法说明：动作事件方法
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addBtn){
			//添加信息
			Staff s=new Staff();
			s.setSidNo(numText.getText());
			s.setSname(nameText.getText());
			s.setSsex(sex);
			s.setSpwd(passwordText.getText());
			s.setTel(telText.getText());
			s.setMobileNo(mobileNoText.getText());
			s.setRegisterTime(registerTimeText.getText());
			sd.getStaffDaoInstance().addStaffInfo(s);
			op.showMessageDialog(null,"添加信息成功!");
		}else if(e.getSource()==clearBtn){
			
		}
	}
	
}