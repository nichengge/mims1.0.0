package com.member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.factory.MemberDaoFactory;

import sun.security.jgss.spi.MechanismFactory;

/**
 * 查询会员信息
 */
public class SearchMemberInfo extends JPanel{

	MemberDaoFactory md=new MemberDaoFactory();
	private String id;
	private JPanel p1,p2,p3;
	private JTable table;//表格
	private JScrollPane scrollPane;
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	
	private JTextField numTextField;
	
	private JComboBox telComb;//手机号
	
	private JButton searchBtn;

	private DefaultTableModel model=null;
	
	private String tableValues[][]=md.getMemberDaoInstance().searchAll();
	String tableTitle[] = {"卡号","姓名","手机","级别","积分"};
	
	public SearchMemberInfo(){
		setLayout(new BorderLayout());
		init();
	}
	public void init(){
		//a.上部布局
		p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
	
		l1=new JLabel("会员卡号:");
		l2=new JLabel("手机号码:");
		
		numTextField=new JTextField(10);
		
		ImageIcon img=new ImageIcon("../cms/images/searchBtn.png");
		searchBtn=new JButton("查找",img);
		
		String Arr[]={};//从数据库获取
		telComb=new JComboBox(Arr);
		
		p1.add(l1);
		p1.add(numTextField);
		p1.add(searchBtn);
		//p1.add(l2);
		//p1.add(telComb);
		
		//b.中部布局
		p2 = new JPanel();
		
	   //获取数据库里的数据
		model=new DefaultTableModel(tableValues,tableTitle);
		
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane.getViewport().add(table);
    	scrollPane.getViewport().setPreferredSize(new Dimension(430,450));
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}
				  tableValues=md.getMemberDaoInstance().searchById(numTextField.getText());
			      model.setDataVector(tableValues,tableTitle);
			}
		});
		
		telComb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
				
					String s=""+e.getItem();
					int i=s.indexOf("-");
					String deptid=s.substring(0, i);
					
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					while(model.getRowCount()>0){
						model.removeRow(model.getRowCount()-1);
					}
					//tableValues=sbean.selectStaffMsgByWno(deptid);
					model.setDataVector(tableValues, tableTitle);

				}
			}
		});
		
		p2.add(scrollPane);
		
		//c.下部布局
		p3 = new JPanel();

		add("North",p1);
		add("Center",p2);
		add("South",p3);
	}

}
