package com.administrator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
 * 修改密码
 */
public class ChangePassword extends JFrame{
	
	AdministratorDaofactory ad=new AdministratorDaofactory();
	private JPanel p1,p2,p3;
	private JLabel loginPwdLabel;
	private JTextField loginPwdText;
	
	private JLabel changePwdLabel;
	private JTextField changePwdText;
	
	private JButton changeBtn;
	private JButton closeBtn;
	public static String id;
	public static String pwd;
	public ChangePassword(){
		
		super("密码");
		Image image=Toolkit.getDefaultToolkit().getImage("../mims/images/password.png");
		setIconImage(image);
		
		setLayout(new GridLayout(3,1));
		p1=new JPanel();
        p1.setLayout(new GridLayout(2,1));
		
		Image img=Toolkit.getDefaultToolkit().getImage("../mims/images/icon.png");
		JLabel imgLabel=new JLabel(new ImageIcon(img));
		imgLabel.setPreferredSize(new Dimension(100,310));
		
		JPanel p=new JPanel();
		loginPwdLabel=new JLabel("登录密码:");
		loginPwdText=new JTextField(10);
		loginPwdText.setText(pwd);
		p.add(loginPwdLabel);
		p.add(loginPwdText);
		
		p1.add(imgLabel);
		p1.add(p);
		
		p2=new JPanel();
		changePwdLabel=new JLabel("新密码");
		changePwdText=new JTextField(10);
		p2.add(changePwdLabel);
		p2.add(changePwdText);
		
		p3=new JPanel();
		ImageIcon image1=new ImageIcon("../mims/images/update.png");
		changeBtn=new JButton("改变",image1);
		changeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Administrator a=new Administrator();
				a.setAid(id);
				a.setApwd(changePwdText.getText());
				ad.getAdministratorDaoInstance().changePassword(a);
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(null, "修改密码成功!");
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
		p3.add(changeBtn);
		p3.add(closeBtn);
		
		add(p1);
		add(p2);
		add(p3);
	
		setVisible(true);
		setSize(200, 250);
		setLocation(300, 300);
		setResizable(false);//设置不可以改变大小
	
	}
}
