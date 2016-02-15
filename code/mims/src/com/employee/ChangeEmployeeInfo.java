package com.employee;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.bean.Staff;
import com.factory.StaffDaoFactory;

/**修改删除员工信息
 */
public class ChangeEmployeeInfo extends JPanel implements ActionListener,ListSelectionListener{

	StaffDaoFactory sd=new StaffDaoFactory();
	private JLabel title;
	
	private JTextField idText;
	
	private JLabel numLabel;
	private JTextField numText;
	
	private JLabel nameLabel;
	private JTextField nameText;
	
	private JLabel mobileNoLabel;
	private JTextField mobileNoText;
	
	private JLabel sexLabel;
	private JTextField sexText;
	
	private JLabel telLabel;
	private JTextField telText;
	
	private JButton update;//修改按钮
	private JButton delete;//删除按钮
	private JButton search;//查询按钮
	private JPanel p1,p2,p3;
	private JTable table;//表格
	private JScrollPane scrollPane;

	//表格标题
	String tableTitle[] = {"工号","姓名","性别","手机","电话"};
	//表格的数据，从数据库获取
	String [][]tableValues = sd.getStaffDaoInstance().searchAll();
	
	DefaultTableModel model=null;
	//设置表格选择模式
	private ListSelectionModel listModel=null;
	
	String no,name,sex,phone,tel;
	
	public ChangeEmployeeInfo(){
	
		init();
	}
	//初始组件
	public void init(){
		int width = 12;//JTextField组件的宽度
		
		setLayout(new BorderLayout());//设置布局为BorderLayout
		
		
		//a.上部布局

		p1 = new JPanel();
		JLabel l=new JLabel("工号:");
		idText=new JTextField(width);
		ImageIcon image1=new ImageIcon("../mims/images/search.png");
		search=new JButton("查询",image1);
		search.addActionListener(this);
		p1.add(l);
		p1.add(idText);
		p1.add(search);
		
		//b.中部布局
	    p2=new JPanel();
		//创建表格
		//tableValues=sbean.getStaffMsg();
		model=new DefaultTableModel(tableValues,tableTitle);
		table = new JTable(model);
		
		//设置表格
		listModel = table.getSelectionModel();
		listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listModel.addListSelectionListener(this);
		
		//将表格添加到滚动面板
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane.getViewport().add(table);
    	scrollPane.getViewport().setPreferredSize(new Dimension(430,320));
		
		p2.add(scrollPane);
		
		//c.下部布局
		p3=new JPanel();
		p3.setLayout(new GridLayout(4,1));
		JPanel p31=new JPanel();
		
		numLabel=new JLabel("工号:");
		numText=new JTextField(width);
		numText.setEditable(false);
		
		nameLabel=new JLabel("姓名:");
		nameText=new JTextField(width);
		
		JPanel p32=new JPanel();

		mobileNoLabel=new JLabel("手机:");
		mobileNoText=new JTextField(width);
		
		sexLabel=new JLabel("性别:");
		sexText=new JTextField(width);
		
		JPanel p33=new JPanel();
		telLabel=new JLabel("电话:");
		telText=new JTextField(28);
		
		JPanel p34=new JPanel();
		ImageIcon image2=new ImageIcon("../mims/images/update.png");
		update = new JButton("修改",image2);
		update.addActionListener(this);	
		
		//删除按钮
		ImageIcon image3=new ImageIcon("../mims/images/clear.png");
		delete = new JButton("删除",image3);
		delete.addActionListener(this);//添加动作事件
		
		p31.add(numLabel);
		p31.add(numText);
		p31.add(nameLabel);
		p31.add(nameText);
		p32.add(mobileNoLabel);
		p32.add(mobileNoText);
		p32.add(sexLabel);
		p32.add(sexText);
		p33.add(telLabel);
		p33.add(telText);
		p34.add(update);
		p34.add(delete);
		
		p3.add(p31);
		p3.add(p32);
		p3.add(p33);
		p3.add(p34);
		
		

		//添加到主面板
		add("North",p1);
		add("Center",p2);
		add("South",p3);
	}
	
	/*
	 * 
	 * 重载的动作事件方法
	 * */
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==delete){//选择删除按钮
			sd.getStaffDaoInstance().deleteStaffInfo(no);
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "删除信息成功!");
		    updateTable();
			//文本框清空
			
		}else if(e.getSource()==update){
			Staff s = new Staff();
			s.setSidNo(numText.getText());
			s.setSname(nameText.getText());
			s.setSsex(sexText.getText());
			s.setMobileNo(mobileNoText.getText());
			s.setTel(telText.getText());
			sd.getStaffDaoInstance().updateStaffInfo(s);
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "修改信息成功!");
		    updateTable();
		}else if(e.getSource()==search){
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			while(model.getRowCount()>0){
				model.removeRow(model.getRowCount()-1);
			}
			tableValues=sd.getStaffDaoInstance().searchById(idText.getText());
			model.setDataVector(tableValues, tableTitle);
		}
	}
	
	public void updateTable(){
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}
		tableValues=sd.getStaffDaoInstance().searchAll();
		model.setDataVector(tableValues, tableTitle);
	}
	/*
	 * 重载的表格选择事件
	 * 
	 * */
	public void valueChanged(ListSelectionEvent e) {
		int row[]=table.getSelectedRows();//表格的行
		int col[]=table.getSelectedColumns();//表格的列
		
		for(int i=0;i<row.length;i++)
			for(int j=0;j<col.length;j++){
				no=tableValues[row[i]][0];
				name=tableValues[row[i]][1];
				sex=tableValues[row[i]][2];
				phone=tableValues[row[i]][3];
				tel=tableValues[row[i]][4];
				
				numText.setText(no);
				nameText.setText(name);
				sexText.setText(sex);
				mobileNoText.setText(phone);
				telText.setText(tel);
				
			}	
	}	
}
