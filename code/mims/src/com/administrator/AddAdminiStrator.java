package com.administrator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bean.Administrator;
import com.factory.AdministratorDaofactory;
/**
 * 
 *新增管理员信息
 */
public class AddAdminiStrator extends JFrame {
	AdministratorDaofactory ad=new AdministratorDaofactory();
	private JPanel p1,p2,p3;
	
	private JLabel accountLabel;
	private JTextField accountText;
	
	private JLabel passwordLabel;
	private JTextField passwordText;
	
	private JButton addBtn;
	private JButton closeBtn;
	
	public AddAdminiStrator(){
		super("新增");
		
		Image image=Toolkit.getDefaultToolkit().getImage("../mims/images/administrator.png");
		setIconImage(image);
		
		setLayout(new GridLayout(3,1));
		p1=new JPanel();
		p1.setLayout(new GridLayout(2,1));
		
		JPanel panel=new JPanel();
		Image img=Toolkit.getDefaultToolkit().getImage("../mims/images/icon.png");
		JLabel imgLabel=new JLabel(new ImageIcon(img));
		imgLabel.setPreferredSize(new Dimension(100,310));
		panel.add(imgLabel);
		
		JPanel p=new JPanel();
		accountLabel=new JLabel("账号:");
		accountText=new JTextField(10);
		p.add(accountLabel);
		p.add(accountText);
		
		p1.add(imgLabel);
		p1.add(p);
		
		p2=new JPanel();
		passwordLabel=new JLabel("密码:");
		passwordText=new JTextField(10);
		p2.add(passwordLabel);
		p2.add(passwordText);
		
		p3=new JPanel();
		ImageIcon image1=new ImageIcon("../mims/images/add.png");
		addBtn=new JButton("新增",image1);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Administrator a=new Administrator();
				a.setAid(accountText.getText());
				a.setApwd(passwordText.getText());
				ad.getAdministratorDaoInstance().addAdministratorInfo(a);
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(null, "新增管理员信息成功!");
			}
		});
		ImageIcon image2=new ImageIcon("../mims/images/exit.png");
		closeBtn=new JButton("关闭",image2);
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		p3.add(addBtn);
		p3.add(closeBtn);
		
		add(p1);
		add(p2);
		add(p3);
		
		setSize(200, 250);
		setVisible(true);
		setLocation(300, 300);
		setResizable(false);//设置不可以改变大小
	}

}
