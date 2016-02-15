package com.member;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bean.Member;
import com.factory.MemberDaoFactory;

/**
 * 添加会员信息
 */
public class AddMemberInfo  extends JPanel implements ActionListener{
	
	MemberDaoFactory md=new MemberDaoFactory();
	JOptionPane op=new JOptionPane();
	
	private JPanel p=null;
	private JPanel p1=null;
	private JPanel p2=null;
	private JPanel p3=null;
	private JPanel p4=null;
	private JPanel p5=null;
	private JPanel p6=null;
	private JPanel p7=null;
	private JPanel p8=null;
	private JPanel p9=null;
	private JPanel p10=null;
	private JPanel p11=null;
	private JPanel p12=null;
	private JPanel p13=null;
	
	private JLabel moneyLabel;
	private JTextField moneyText;
	private JButton okBtn;
	
	private JTextField nameText;
	private JLabel nameLabel;
	
	private JComboBox sexCombobox;
	private JLabel sexLabel;
	
	private JTextField birthdayText;
	private JLabel birthdayLabel;
	//电话
	private JTextField telText;
	private JLabel telLabel;
	//手机号码
	private JTextField mobileNoText;
	private JLabel mobileNoLabel;
	//邮寄地址
	private JTextField mailAddressText;
	private JLabel mailAddressLabel;
	//邮编
	private JTextField postcodeText;
	private JLabel postcodeLabel;
	//电子邮件
	private JTextField mailText;
	private JLabel mailLabel;
	//会员级别
	private JComboBox rankCombobox;
	private JLabel rankLabel;
	//卡号
	private JTextField cardNoText;	
	private JLabel cardNoLabel;
	//审核人
	private JTextField examineText;
	private JLabel examineLabel;
	//登记时间
	private JTextField registerTimeText;	
	private JLabel registerTimeLabel;
	
	private JButton addBtn=null;
	private JButton clearBtn=null;
	
	private String sex="女";
	
	private String type="普通卡";
	/*
	 * 采用GridBagLayout布局
	 */
	private GridBagLayout gridL=null;
	private GridBagConstraints gridC=null;
	
	int width=16;//JTextField的宽度,便于代码的修改
		
	public AddMemberInfo(){ 
		init();
   }
	
	public void init(){
		
		setLayout(new GridLayout(13,1));
		p=new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		moneyLabel=new JLabel("请输入金额:");
		moneyText=new JTextField(width);
		ImageIcon image=new ImageIcon("../mims/images/right.png");
		okBtn=new JButton("确定",image);
	
		p.add(moneyLabel);
		p.add(moneyText);
		p.add(okBtn);
		
		 p1=new JPanel();
		 nameLabel=new JLabel("姓名:         ");
		 nameText=new JTextField(width);
		 p1.add(nameLabel);
		 p1.add(nameText);
		 
		 p2=new JPanel();
		 sexLabel=new JLabel("性别:         ");
		 String arr[]={"女","男"};
		 sexCombobox=new JComboBox(arr);
		 sexCombobox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					sex=""+e.getItem();
				}
			}
		});
		 JLabel l1 = new JLabel("                                        ");
		 p2.add(sexLabel);
		 p2.add(sexCombobox);
		 p2.add(l1);
		 
		 p3=new JPanel();
		 birthdayLabel=new JLabel("生日:         ");
		 birthdayText=new JTextField(width);
		 p3.add(birthdayLabel);
		 p3.add(birthdayText);
		 
		 p4=new JPanel();
		 telLabel=new JLabel("电话:         ");
		 telText=new JTextField(width);
		 p4.add(telLabel);
		 p4.add(telText);
		 
		 p5=new JPanel();
		 mobileNoLabel=new JLabel("手机号码:");
		 mobileNoText=new JTextField(width);
		 p5.add(mobileNoLabel);
		 p5.add(mobileNoText);
		 
		 p6=new JPanel();
		 mailAddressLabel=new JLabel("邮寄地址:");
		 mailAddressText=new JTextField(width);
		 p6.add(mailAddressLabel);
		 p6.add(mailAddressText);
		 
		 p7=new JPanel();
		 postcodeLabel=new JLabel("邮编:         ");
		 postcodeText=new JTextField(width);
		 p7.add(postcodeLabel);
		 p7.add(postcodeText);
		 
		 p8=new JPanel();
		 mailLabel=new JLabel("电子邮件:");
		 mailText=new JTextField(width);
		 p8.add(mailLabel);
		 p8.add(mailText);
		 
		 p9=new JPanel();
		 rankLabel=new JLabel("会员级别:  ");
		 String array[]={"普通卡","银卡","金卡"};
		 rankCombobox=new JComboBox(array);
		 rankCombobox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					type=""+e.getItem();
				}
			}
		});
		 JLabel l2 = new JLabel("                                        ");
		 p9.add(rankLabel);
		 p9.add(rankCombobox);
		 p9.add(l2);
		 
		 p10=new JPanel();
		 cardNoLabel=new JLabel("会员卡号:");
		 cardNoText=new JTextField(width);
		 p10.add(cardNoLabel);
		 p10.add(cardNoText);

		 p11=new JPanel();
		 examineLabel=new JLabel("审核人:     ");
		 examineText=new JTextField(width);
		 p11.add(examineLabel);
		 p11.add(examineText);
		 
		 p12=new JPanel();
		 registerTimeLabel=new JLabel("登记日期:");
		 registerTimeText=new JTextField(width);
		 p12.add(registerTimeLabel);
		 p12.add(registerTimeText);
		 
		 p13=new JPanel();
		 ImageIcon image1=new ImageIcon("../mims/images/add.png");
		 addBtn=new JButton("新增",image1);
		 addBtn.addActionListener(this);
		 ImageIcon image2=new ImageIcon("../mims/images/clear.png");
		 clearBtn=new JButton("清除",image2);
		 clearBtn.addActionListener(this);
		 p13.add(addBtn);
		 p13.add(clearBtn);
		 
		 add(p1);add(p2);add(p3);add(p4);add(p5);add(p6);
	     add(p7);add(p8);add(p9);add(p10);add(p11); add(p12);add(p13);
	}

	/**
	 *重载方法说明：动作事件方法
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addBtn){
			//添加学生信息
			Member m=new Member();
			m.setMname(nameText.getText());
			m.setMsex(sex);
			m.setBirthday(birthdayText.getText());
			m.setTel(telText.getText());
			m.setMobileNo(mobileNoText.getText());
			m.setMailAddress(mailAddressText.getText());
			m.setMail(mailText.getText());
			m.setMrank(type);
			m.setMid(cardNoText.getText());
			m.setExaminer(examineText.getText());
			m.setRegisteTime(registerTimeText.getText());
			m.setCredits(0);
			m.setConsumeMoney(0);
			
			md.getMemberDaoInstance().addMemberInfo(m);
			
			op.showMessageDialog(null,"添加信息成功!");
		}else if(e.getSource()==clearBtn){
			
		}
	}

	

	
}
	
	

