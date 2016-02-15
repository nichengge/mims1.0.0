package com.credits;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.bean.Member;
import com.factory.CreditDaoFactory;

/**
 * 修改删除会员积分
 */
public class UpdateDeleteCredit extends JPanel implements ActionListener,ListSelectionListener{
	CreditDaoFactory cd=new CreditDaoFactory();
	private JLabel title;
	
	private JLabel cardNoLabel;
	private JTextField cardNoText;
	
	private JLabel nameLabel;
	private JTextField nameText;
	
	private JLabel creditLabel;
	private JTextField creditText;
	
	private JButton update;//修改按钮
	private JButton delete;//删除按钮
	private JPanel p1,p2,p3;
	private JTable table;//表格
	private JScrollPane scrollPane;
	
	//表格标题
	String tableTitle[] = {"卡号","姓名","手机","邮件","积分"};
	//表格的数据，从数据库获取
	String [][]tableValues = cd.getCreditDaoInstance().getMemberInfo();
	
	DefaultTableModel model=null;
	//设置表格选择模式
	private ListSelectionModel listModel=null;
	
	String no,name,phone,mail,credit;
	
	public UpdateDeleteCredit(){
	
		init();
	}
	//初始组件
	public void init(){
		int width = 10;//JTextField组件的宽度
		
		setLayout(new BorderLayout());//设置布局为BorderLayout
		
		//a.上部布局
	
		
		//b.中部布局
		p1 = new JPanel();
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
    	scrollPane.getViewport().setPreferredSize(new Dimension(430,430));
		
		p1.add(scrollPane);
		
		//
		p2=new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		cardNoLabel=new JLabel("卡号");
		cardNoText=new JTextField(width);
		cardNoText.setEditable(false);
		
		nameLabel=new JLabel("姓名");
		nameText=new JTextField(width);
		
		creditLabel=new JLabel("积分");
		creditText=new JTextField(width);
		p2.add(cardNoLabel);
		p2.add(cardNoText);
		p2.add(nameLabel);
		p2.add(nameText);
		p2.add(creditLabel);
		p2.add(creditText);
		
		//c.下部布局
		p3 = new JPanel();
	    p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		//修改按钮
	    ImageIcon image1=new ImageIcon("../mims/images/update.png");
	    update=new JButton("修改",image1);
	    update.addActionListener(this);
	    p3.add(update);
		
		//删除按钮
	    ImageIcon image2=new ImageIcon("../mims/images/clear.png");
		delete = new JButton("删除",image2);
		delete.addActionListener(this);//添加动作事件
		p3.add(delete);
		
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
			cd.getCreditDaoInstance().deleteCredit(no);
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "删除信息成功!");
		    updateTable();
			//文本框清空
		}else if(e.getSource()==update){
			Member m=new Member();
			m.setMid(cardNoText.getText());
			m.setMname(nameText.getText());
			m.setCredits(Integer.parseInt(creditText.getText()));
			cd.getCreditDaoInstance().updateCredit(m);
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "修改信息成功!");
			updateTable();
		}
		
	}
	
	public void updateTable(){
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}
		tableValues=cd.getCreditDaoInstance().getMemberInfo();
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
				credit=tableValues[row[i]][4];
				
				cardNoText.setText(no);
				nameText.setText(name);
				creditText.setText(credit);
			}	
	}	
}
