package com.credits;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.bean.Credit;
import com.factory.CreditDaoFactory;

/**
 * 编辑积分类型
 */
public class EditFormula extends JPanel implements ActionListener,ListSelectionListener{
	private JLabel title;
	
	private JLabel creditsTypeLabel;
	private JTextField creditsTypeText;
	
	private JLabel creditsPercentageLabel;
	private JTextField creditsPercentageText;
	
	private JButton add;
	private JButton update;//修改按钮
	private JButton delete;//删除按钮
	private JPanel p1,p2,p3;
	private JTable table;//表格
	private JScrollPane scrollPane;
	
	//表格标题
	String tableTitle[] = {"积分类型","积分比例"};
	//表格的数据，从数据库获取
	CreditDaoFactory cd=new CreditDaoFactory();
	
	String [][]tableValues = cd.getCreditDaoInstance().searchAll();
	
	DefaultTableModel model=null;
	//设置表格选择模式
	private ListSelectionModel listModel=null;
	
	String type;
	String scale;
	
	public EditFormula(){
	
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
		creditsTypeLabel=new JLabel("积分类型");
		creditsTypeText=new JTextField(width);
		creditsTypeText.setEditable(false);
		
		creditsPercentageLabel=new JLabel("积分比例");
		creditsPercentageText=new JTextField(width);
		
		p2.add(creditsTypeLabel);
		p2.add(creditsTypeText);
		p2.add(creditsPercentageLabel);
		p2.add(creditsPercentageText);
		
		//c.下部布局
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    p3.setLayout(new FlowLayout(FlowLayout.CENTER));
	    //新增按钮
	    ImageIcon image1=new ImageIcon("../mims/images/add.png");
	    add=new JButton("新增",image1);
	    p3.add(add);
		//修改按钮
	    ImageIcon image2=new ImageIcon("../mims/images/update.png");
	    update=new JButton("修改",image2);
	    update.addActionListener(this);
	    p3.add(update);
		
		//删除按钮
	    ImageIcon image3=new ImageIcon("../mims/images/clear.png");
		delete = new JButton("删除",image3);
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
		if(e.getSource()==update){//选择修改按钮
			Credit c=new Credit();
			c.setCreditType(creditsTypeText.getText());
			c.setScale(Float.parseFloat(creditsPercentageText.getText()));
			cd.getCreditDaoInstance().updateCreditType(c);
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(null, "修改信息成功!");
		    updateTable();
			//文本框清空
		}
	}
	
	public void updateTable(){
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		while(model.getRowCount()>0){
			model.removeRow(model.getRowCount()-1);
		}
		tableValues=cd.getCreditDaoInstance().searchAll();
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
				type=tableValues[row[i]][0];
				scale=tableValues[row[i]][1];
				creditsTypeText.setText(type);
				creditsPercentageText.setText(scale);
			}	
	}	
}
