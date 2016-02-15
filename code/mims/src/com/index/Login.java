package com.index;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.administrator.AdminStratorLogin;
import com.administrator.ChangePassword;
import com.bean.Administrator;
import com.bean.Staff;
import com.employee.EmployeeLogin;
import com.factory.AdministratorDaofactory;
import com.factory.StaffDaoFactory;


/**
 * 登录界面
 */
public class Login extends JFrame{

	StaffDaoFactory sd=new StaffDaoFactory();
	AdministratorDaofactory ad=new AdministratorDaofactory();
	private JOptionPane op=new JOptionPane();
	
	private Image img=null;
	private JPanel p1=null;
	private JPanel p2=null;
	private JPanel p3=null;

	private JLabel l1=null;
	private JLabel l2=null;
	private JLabel l3=null;
	
	private JComboBox usersTypeComboBox;
	private  JTextField userTextField=null;
	private JPasswordField passwordField=null;
	private JTextField port=null;
	private JButton registerBtn=null;
	private JButton exitBtn=null;
	private GridBagLayout gridL=null;
	private GridBagConstraints gridC=null;
	
	private String usersType="管理员";
    private Container con=null;

    private JLabel imgLabel;
    
    private JPanel imagePanel ;
    private ImageIcon background;
	public Login(){
		
		super("会员信息管理");

		con=this.getContentPane();
		
		Image image = Toolkit.getDefaultToolkit().getImage("../mims/images/member.png");
		setIconImage(image);//设置标题栏图标
		p1=new JPanel();
		
		p2=new JPanel();
		gridL=new GridBagLayout();
		p2.setLayout(gridL);
		
		p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		init();
		//添加组件到面板
		p1.add(imgLabel);
		p2.add(l1);
		p2.add(usersTypeComboBox);
		p2.add(l2);
		p2.add(userTextField);
		p2.add(l3);
		p2.add(passwordField);		
		p3.add(registerBtn);
		p3.add(exitBtn);
		
		 background = new ImageIcon("../mims/images/login_bg.jpg");// 背景图片
		  JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		  // 把标签的大小位置设置为图片刚好填充整个面板
		  label.setBounds(0, 0, background.getIconWidth(),
		    background.getIconHeight());
		  // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		  imagePanel = (JPanel) getContentPane();
		  imagePanel.setOpaque(false);
		  // 内容窗格默认的布局管理器为BorderLayout
		  imagePanel.setLayout(new FlowLayout());
		  imagePanel.add("North",p1);
		  imagePanel.add("Center",p2);
		  imagePanel.add("South",p3);
		  
		  p1.setOpaque(false);
		  p2.setOpaque(false);
		  p3.setOpaque(false);
		  
		  getLayeredPane().setLayout(null);
		  // 把背景图片添加到分层窗格的最底层作为背景
		  getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	
		  setSize(300, 300);

		  //设置运行时窗口的位置
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 3, (screenSize.height - frameSize.height) / 3);

		
		setVisible(true);
		setResizable(false);
		check();
	}
	
	public void init(){
		img=Toolkit.getDefaultToolkit().getImage("../mims/images/member.png");
		imgLabel=new JLabel(new ImageIcon(img));
		imgLabel.setPreferredSize(new Dimension(300,60));
		gridC=new GridBagConstraints();
		gridC.gridx=5;
		gridC.gridy=0;
		gridC.insets=new Insets(30, 30, 30, 30);
		gridL.addLayoutComponent(imgLabel, gridC);
		
		int width=10;
		
		//登录者的类型
		l1=new JLabel("请选择:");
		gridC=new GridBagConstraints();
		gridC.gridx=0;
		gridC.gridy=1;
		gridC.insets=new Insets(10,5,10,5);
		gridL.addLayoutComponent(l1, gridC);
		
		String usersTypeArr[]={"管理员","员工"};
		usersTypeComboBox=new JComboBox(usersTypeArr);
		gridC=new GridBagConstraints();
		gridC.gridx=1;
		gridC.gridy=1;
		gridC.insets=new Insets(10,5,10,5);
		gridL.addLayoutComponent(usersTypeComboBox, gridC);
		
		usersTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					usersType=""+e.getItem();
				}else{
					usersType=""+e.getItem();
				}
			}
		});
		
		//用户名
		l2=new JLabel("账号:");
		gridC=new GridBagConstraints();
		gridC.gridx=0;
		gridC.gridy=2;
		gridC.insets=new Insets(10,5,10,5);
		gridL.addLayoutComponent(l2, gridC);
		
		userTextField=new JTextField(width);
		gridC=new GridBagConstraints();
		gridC.gridx=1;
		gridC.gridy=2;
		gridC.insets=new Insets(10,5,10,5);
		gridL.addLayoutComponent(userTextField, gridC);
		
		//密码	
		l3=new JLabel("密码:");
		gridC=new GridBagConstraints();
		gridC.gridx=0;
		gridC.gridy=3;
		gridC.insets=new Insets(10,5,10,5);
		gridL.addLayoutComponent(l3, gridC);
		
		passwordField=new JPasswordField(width);
		gridC=new GridBagConstraints();
		gridC.gridx=1;
		gridC.gridy=3;
		gridC.insets=new Insets(10,5,10,5);
		gridL.addLayoutComponent(passwordField, gridC);
		
		ImageIcon image1=new ImageIcon("../mims/images/login.png");
		//登录按钮
		registerBtn=new JButton("登录",image1);
		
		ImageIcon image2=new ImageIcon("../mims/images/clear.png");
		//退出按钮
		exitBtn=new JButton("退出",image2);	
	
		/*
		 * 登录按钮的事件监听器
		 */
	   registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==registerBtn){
				   check();
				}
			}
		});
	   /*
	    * 退出按钮的事件监听器
	    */
	   exitBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	   });
	   /*
	    * 用户名的事件监听器
	    */
	   userTextField.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			passwordField.requestFocusInWindow();
		}
    	});
	   
	   passwordField.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
             port.requestFocusInWindow();  			
		}
	});
	}
	
	/**
	 * 验证
	 */
	public void check(){
	    if(usersType.equals("员工")){
	    	if(!userTextField.getText().equals("")&&userTextField.getText()!=null
	    			&&!passwordField.getText().equals("")&&passwordField.getText()!=null){
	    		Staff sta=new Staff();
	    		sta.setSidNo(userTextField.getText());
	    		sta.setSpwd(passwordField.getText());
		    	String[] s=sd.getStaffDaoInstance().check(sta);
		    	if(userTextField.getText().equals(s[0])&&passwordField.getText().equals(s[1])){
		    		op.showMessageDialog(this, "欢迎登录会员信息管理系统!");
		    		setVisible(false);
		    		new EmployeeLogin();
		    	}else{
		    		op.showMessageDialog(this, "帐号或密码错误!");
		    	}
	    	}
	    }else if(usersType.equals("管理员")){
	    	if(!userTextField.getText().equals("")&&userTextField.getText()!=null
	    			&&!passwordField.getText().equals("")&&passwordField.getText()!=null){
	    		Administrator a=new Administrator();
	    		a.setAid(userTextField.getText());
	    		a.setApwd(passwordField.getText());
		    	String[] s=ad.getAdministratorDaoInstance().check(a);
		    	if(userTextField.getText().equals(s[0])&&passwordField.getText().equals(s[1])){
		    		ChangePassword.id=userTextField.getText();
		    		ChangePassword.pwd=passwordField.getText();
		    		op.showMessageDialog(this, "欢迎登录会员信息管理系统!");
		    		setVisible(false);
		    		new AdminStratorLogin();
		    	}else{
		    		op.showMessageDialog(this, "帐号或密码错误!");
		    	}
	    	}
	    }else{
	    	op.showConfirmDialog(this,"请选择用户类型!");
	    }
	}

	public void clearAll(){
		userTextField.setText(null);
		passwordField.setText(null);
	}
}
