package com.employee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.credits.AddupCredits;
import com.index.Login;
import com.member.AddMemberInfo;
import com.member.SearchMemberInfo;

/**
 * 管理员登录主界面
 */
public class EmployeeLogin extends JFrame implements TreeSelectionListener{
	
	private JMenuBar menuBar=null;
		
	private JMenu helpMenu=null;
	private JMenu exitMenu=null;
	
	//退出
	private JMenuItem exitLogin;
	private JMenuItem exitSystem;
	
	//帮助
	private JMenuItem about;
	private JMenuItem contactWe;
	
	private JSplitPane split;
	private JScrollPane scrollPanel;
	
	private JSplitPane splitPane ;//split面板
	private JPanel p1,p2;//左右面板
	private JScrollPane scrollPane;//滚动面板
	private JTree tree ;//树
	private Image image;//图片
	private JLabel label,imgLabel;
	private DefaultMutableTreeNode root;//树根
	private DefaultMutableTreeNode node1,node2;//树节点1、2、3、4,对应模块
	private DefaultMutableTreeNode node11;//第一个节点的子节点
	private DefaultMutableTreeNode node21,node22;//第二个节点的子节点
	private JPanel imagePanel=null;
	
	public static String s[]=new String[10];
	//构造函数，用于初始
	
	public EmployeeLogin(){

		setTitle("会员信息管理      员工,欢迎您!");
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
		
		ImageIcon exitLoginIcon=new ImageIcon("../mims/images/login.png");
		exitLogin=new JMenuItem("退出登录",exitLoginIcon);
		exitLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});
		
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
				 JDialog dialog=new JDialog(EmployeeLogin.this, "会员信息管理系统", true);
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
				 JDialog dialog=new JDialog(EmployeeLogin.this, "联系我们", true);
				 dialog.add(l);
				 dialog.setLocation(300, 300);
				 dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
				 dialog.setSize(300, 200);   
				 dialog.setVisible(true); 
			}
		});
		helpMenu.add(about);
		helpMenu.add(contactWe);
	
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
		node11 = new DefaultMutableTreeNode("会员积分累计");
		node1.add(node11);
		root.add(node1);
		//添加节点2的子节点
		node2 = new DefaultMutableTreeNode("会员管理");
		node21 = new DefaultMutableTreeNode("增加会员信息");
		node22 = new DefaultMutableTreeNode("查询会员信息");
		node2.add(node21);
		node2.add(node22);
		root.add(node2);
		
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
		}else if(dnode_str == "增加会员信息"){
			splitPane.setRightComponent(new AddMemberInfo());
		}else if(dnode_str == "查询会员信息"){
			splitPane.setRightComponent(new SearchMemberInfo());	
		}else if(dnode_str == "会员积分累计"){
			splitPane.setRightComponent(new AddupCredits());
		}else{
			splitPane.setRightComponent(p2);
		}
		
	}
	
}
