package com.credits;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sun.awt.SunHints.Value;

import com.factory.CreditDaoFactory;
/**
 * 会员积分累计，通过卡号查询
 */
public class AddupCredits  extends JPanel implements ItemListener{
	
	CreditDaoFactory cd=new CreditDaoFactory();
	
	int width=10;
	private JLabel label;
	private JTextField textField;
	private JButton searchBtn;
		
	private JLabel nameLabel;
	private JTextField nameText;
	private JLabel rankLabel;//级别
	private JTextField rankText;
	private JLabel telLabel;//电话
	private JTextField telText;
	private JLabel cardNoLabel;//卡号
	private JTextField cardNoText;
	private JLabel registerTimeLabel;//登记时间
	private JTextField registerTimeText;
	private JLabel creditsLabel;//积分
	private JTextField creditsText;
	
	//消费金额
	private JLabel moneyLabel;
	private JTextField moneyText;
	//积分类型
	private JLabel creditsTypeLabel;
	private JComboBox creditsTypeComboBox;
	//累计积分
	private JLabel addupCreditsLabel;
	private JTextField addupCreditsText;
	//本次积分
	private JLabel theCreditsLabel;
	private JTextField theCreditsText;
	//消费日期
	private JLabel timeLabel;
	private JTextField timeText;
	
	private JButton addBtn;
	private JButton noBtn;
	
	private JPanel p,p1,p2,p3,p4,p5,p6,p7,p8,p9;
	
	private String theCredit;
	private String addupCredit;
	
	private String type="正价积分";
	

	
	public AddupCredits(){
		
		setLayout(new GridLayout(10,1));
		
		p=new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		label=new JLabel("卡号:");
		textField=new JTextField(10);
		ImageIcon image=new ImageIcon("../mims/images/search.png");
		searchBtn=new JButton("查询",image);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] s=cd.getCreditDaoInstance().searchById(textField.getText());
				nameText.setText(s[0]);
				rankText.setText(s[1]);
				telText.setText(s[2]);
				cardNoText.setText(s[3]);
				registerTimeText.setText(s[4]);
				creditsText.setText(s[5]);
			}
		});
		p.add(label);
		p.add(textField);
		p.add(searchBtn);
		
		p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		nameLabel=new JLabel("会员姓名:");
		nameText=new JTextField(width);
		nameText.setEditable(false);
		rankLabel=new JLabel("会员级别:");
		rankText=new JTextField(width);
		rankText.setEditable(false);
		p1.add(nameLabel);
		p1.add(nameText);
		p1.add(rankLabel);
		p1.add(rankText);
		
	    p2=new JPanel();
	    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    telLabel=new JLabel("联系电话:");
	    telText=new JTextField(width);
	    telText.setEditable(false);
	    cardNoLabel=new JLabel("会员卡号:");
	    cardNoText=new JTextField(width);
	    cardNoText.setEditable(false);
	    p2.add(telLabel);
	    p2.add(telText);
	    p2.add(cardNoLabel);
	    p2.add(cardNoText);
	    
	    p3=new JPanel();
	    p3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    registerTimeLabel=new JLabel("登记日期:");
	    registerTimeText=new JTextField(width);
	    registerTimeText.setEditable(false);
	    creditsLabel=new JLabel("会员积分:");
	    creditsText=new JTextField(width);
	    creditsText.setEditable(false);
	    p3.add(registerTimeLabel);
	    p3.add(registerTimeText);
	    p3.add(creditsLabel);
	    p3.add(creditsText);
	    
	    p4=new JPanel();
	    p4.setLayout(new FlowLayout(FlowLayout.LEFT));
	    moneyLabel=new JLabel("消费金额:");
	    moneyText=new JTextField(10);
	    p4.add(moneyLabel);
	    p4.add(moneyText);
	    
	    p5=new JPanel();
	    p5.setLayout(new FlowLayout(FlowLayout.LEFT));
	    theCreditsLabel=new JLabel("本次积分:");
	    theCreditsText=new JTextField(width);
	    theCreditsText.setEditable(false);
	    p5.add(theCreditsLabel);
	    p5.add(theCreditsText);
	    
	    p6=new JPanel();
	    p6.setLayout(new FlowLayout(FlowLayout.LEFT));
	    creditsTypeLabel=new JLabel("积分类型:");
	    String arr[]=cd.getCreditDaoInstance().searchCrditType();//从数据库获取
	    creditsTypeComboBox=new JComboBox(arr);
	    creditsTypeComboBox.addItemListener(this);
	    p6.add(creditsTypeLabel);
	    p6.add(creditsTypeComboBox);
	    
	    p7=new JPanel();
	    p7.setLayout(new FlowLayout(FlowLayout.LEFT));
	    addupCreditsLabel=new JLabel("累计积分:");
	    addupCreditsText=new JTextField(width);
	    addupCreditsText.setEditable(false);
	    p7.add(addupCreditsLabel);
	    p7.add(addupCreditsText);
	    
	    p8=new JPanel();
	    p8.setLayout(new FlowLayout(FlowLayout.LEFT));
	    timeLabel=new JLabel("消费日期:");
	    timeText=new JTextField(width);
	    p8.add(timeLabel);
	    p8.add(timeText);
	    
	    p9=new JPanel();
	    p9.setLayout(new FlowLayout(FlowLayout.LEFT));
	    ImageIcon image1=new ImageIcon("../mims/images/right.png");
	    addBtn=new JButton("确定",image1);
	    addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				float scale=cd.getCreditDaoInstance().getCreditProportion(type);
				
				float credit=Float.parseFloat(moneyText.getText())/scale;
				theCredit=String.valueOf(credit);
				theCreditsText.setText(theCredit);
				
				float totalCredit=credit+Float.parseFloat(creditsText.getText());
				addupCredit=String.valueOf(totalCredit);
				addupCreditsText.setText(addupCredit);
				
				cd.getCreditDaoInstance().addupCredit(moneyText.getText(), timeText.getText(),addupCredit,textField.getText());
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(null, "修改成功!");
			}
		});
	    ImageIcon image2=new ImageIcon("../mims/images/exit.png");
	    noBtn=new JButton("取消",image2);
	    noBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addupCreditsText.setText("");
				registerTimeText.setText("");
			}
		});
	    p9.add(addBtn);
	    p9.add(noBtn);
		
	    add(p);add(p1);add(p2);add(p3);
	    add(p4);add(p5);add(p6);add(p7);add(p8);add(p9);
	}
	


	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			type=""+e.getItem();
		}
	}
}



