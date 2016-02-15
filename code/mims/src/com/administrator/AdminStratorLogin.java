package com.administrator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

import com.credits.AddupCredits;
import com.credits.EditFormula;
import com.credits.UpdateDeleteCredit;
import com.employee.AddEmployeeInfo;
import com.employee.ChangeEmployeeInfo;
import com.index.Login;
import com.member.AddMemberInfo;
import com.member.ChangeMemberInfo;

/**
 * 管理员登录主界面
 */
public class AdminStratorLogin extends JFrame implements TreeSelectionListener{
	
	private JMenuBar menuBar=null;
		
	private JMenu helpMenu=null;
	private JMenu exitMenu=null;
	private JMenu adminMenu=null;
	
	//退出
	private JMenuItem exitLogin;
	private JMenuItem exitSystem;
	
	//帮助
	private JMenuItem about;
	private JMenuItem contactWe;
	
	//管理员管理
	private JMenuItem addAdministrator;
	private JMenuItem changePassword;
	
	private JSplitPane split;
	private JScrollPane scrollPanel;
	
	private JSplitPane splitPane ;//split面板
	private JPanel p1,p2;//左右面板
	private JScrollPane scrollPane;//滚动面板
	private JTree tree ;//树
	private Image image;//图片
	private JLabel label,imgLabel;
	private DefaultMutableTreeNode root;//树根
	private DefaultMutableTreeNode node1,node2,node3;//树节点1、2、3、4,对应模块
	private DefaultMutableTreeNode node11,node12,node13;//第一个节点的子节点
	private DefaultMutableTreeNode node21,node22;//第二个节点的子节点
	private DefaultMutableTreeNode node31,node32;//第三个节点的子节点
	private JPanel imagePanel=null;
	
	public static String s[]=new String[10];
	//构造函数，用于初始
	
	public AdminStratorLogin(){

		setTitle("会员信息管理      管理员,欢迎您!");
		Image image=Toolkit.getDefaultToolkit().getImage("../mims/images/member.png");
		setIconImage(image);
		
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(0,0,255,255));

		createMenuBar();
	
		CreateCenterPart();		
		
		setJMenuBar(menuBar);
		getContentPane().add("Center",splitPane);
			
		//设置JFrame的属性
		setResizable(false);//设置不可以改变大小
		pack();//自动调整
		setSize(700,600);
		
		//设置运行时窗口的位置
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension frameSize = getSize();
				if (frameSize.height > screenSize.height) {
					frameSize.height = screenSize.height;
				}
				if (frameSize.width > screenSize.width) {
					frameSize.width = screenSize.width;
				}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
	}
	/**
	 * 方法说明:创建菜单栏
	 */
 public void createMenuBar(){
		
		menuBar=new JMenuBar();
        menuBar.setBackground(new Color(197,228,251));
        
		exitMenu=new JMenu("退出");
		helpMenu=new JMenu("帮助");
		adminMenu=new JMenu("管理员管理");
			
		ImageIcon usersIcon=new ImageIcon("../mims/images/users.png");
	
		ImageIcon exitLoginIcon=new ImageIcon("../mims/images/login.png");
		exitLogin=new JMenuItem("退出登录",exitLoginIcon);
		exitLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
		ImageIcon addIcon=new ImageIcon("../mims/images/administrator.png");
		addAdministrator=new JMenuItem("添加管理员",addIcon);
		addAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddAdminiStrator();
			}
		});
		ImageIcon pwdIcon=new ImageIcon("../mims/images/password.png");
		changePassword=new JMenuItem("改变密码",pwdIcon);
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword();
			}
		});
		adminMenu.add(addAdministrator);
		adminMenu.add(changePassword);
		
		
		ImageIcon exitSystemIcon=new ImageIcon("../mims/images/exit.png");
		exitSystem=new JMenuItem("退出系统",exitSystemIcon);
		exitSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitMenu.add(exitLogin);
		exitMenu.add(exitSystem);
		
		ImageIcon aboutIcon=new ImageIcon("../mims/images/about.png");
		about=new JMenuItem("关于",aboutIcon);
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JLabel l=new JLabel("会员信息管理");
				 JDialog dialog=new JDialog(AdminStratorLogin.this, "会员信息管理系统", true);
				 dialog.add(l);
				 dialog.setLocation(300, 300);
				 dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
				 dialog.setSize(300, 200);   
				 dialog.setVisible(true); 
			}
		});
		
		ImageIcon contactIcon=new ImageIcon("../mims/images/contact.png");
		contactWe=new JMenuItem("联系我们",contactIcon);
		contactWe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JLabel l=new JLabel("邮箱:3211112@qq.com");
				 JDialog dialog=new JDialog(AdminStratorLogin.this, "联系我们", true);
				 dialog.add(l);
				 dialog.setLocation(300, 300);
				 dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
				 dialog.setSize(300, 200);   
				 dialog.setVisible(true); 
			}
		});
		helpMenu.add(about);
		helpMenu.add(contactWe);
	
		menuBar.add(adminMenu);
		menuBar.add(exitMenu);
		menuBar.add(helpMenu);
	}
					
	/**
	 * 方法说明:创建树形结构
	 */
	public void CreateCenterPart(){

		root = new DefaultMutableTreeNode("会员信息管理");

		//添加节点1的子节点
		node1 = new DefaultMutableTreeNode("积分管理");
		node11 = new DefaultMutableTreeNode("编辑积分类型");
		node12 = new DefaultMutableTreeNode("会员积分累计");
		node13 = new DefaultMutableTreeNode("修改删除会员积分");
		node1.add(node11);
		node1.add(node12);
		node1.add(node13);
		root.add(node1);
		//添加节点2的子节点
		node2 = new DefaultMutableTreeNode("员工管理");
		node21 = new DefaultMutableTreeNode("增加员工信息");
		node22 = new DefaultMutableTreeNode("更改员工信息");
		node2.add(node21);
		node2.add(node22);
		root.add(node2);
		
		//添加节点3的子节点
		node3 = new DefaultMutableTreeNode("会员管理");
		node31 = new DefaultMutableTreeNode("增加会员信息");
		node32 = new DefaultMutableTreeNode("更改会员信息");
		node3.add(node31);
		node3.add(node32);
		root.add(node3);
		
		//创建一棵树
		tree = new JTree(root);
		tree.addTreeSelectionListener(this);//添加树被选择事件

		scrollPane = new JScrollPane(tree);//将树添加到滚动面板
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane.getViewport().add(tree);
		scrollPane.setPreferredSize(new Dimension(200,530));
	
		//左面板
		p1 = new JPanel();
		
		p1.add(scrollPane);
		//右面板，可以被替改
		p2 = new JPanel();
		ImageIcon bg=new ImageIcon("../mims/images/1.jpg");
	
		JLabel imgLabel=new JLabel(bg);
        p2.setPreferredSize(new Dimension(Integer.MAX_VALUE, bg.getIconHeight()));
		
		p2.setOpaque(false);//此处要设为false
	
        p2.add(imgLabel);
       	
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(p1);
		splitPane.setRightComponent(p2);
		
	}
	/*
	 * 重载的valueChange方法
	 * 当单击树节点时，事件触发，
	 * 创建的添加人员信息、修改人员信息等面板会自动加到右面板区域
	 */
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode dnode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
		String dnode_str = dnode.toString();
		if(dnode_str == "会员信息管理"){
			splitPane.setRightComponent(p2);	
		}else if(dnode_str == "增加员工信息"){
			splitPane.setRightComponent(new AddEmployeeInfo());
		}else if(dnode_str == "更改员工信息"){
			splitPane.setRightComponent(new ChangeEmployeeInfo());	
		}else if(dnode_str == "增加会员信息"){
			splitPane.setRightComponent(new AddMemberInfo());
		}else if(dnode_str == "更改会员信息"){
			splitPane.setRightComponent(new ChangeMemberInfo());	
		}else if(dnode_str == "会员积分累计"){
			splitPane.setRightComponent(new AddupCredits());
		}else if(dnode_str == "修改删除会员积分"){
			splitPane.setRightComponent(new UpdateDeleteCredit());
		}else if(dnode_str == "编辑积分类型"){
			splitPane.setRightComponent(new EditFormula());
		}else{
			splitPane.setRightComponent(p2);
		}
		
	}
	
}
