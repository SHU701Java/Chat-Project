package com.oicq.frame;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//import com.oicq.client.InteractWithServer;
//import com.sun.org.apache.xml.internal.security.Init;
//
///**   
// * @ClassName:  register  
// * @Description: 注册界面 已废弃 采用wgg的  
// * @author: Zztrans
// * @date: 2021-02
// *   
// * JFrame
// * @Copyright: Inc. All rights reserved.  
//<<<<<<< HEAD
//// */  
//
////public class register extends JFrame{
////	
////	JTextField usernameText;
////	JPasswordField passwordText1,passwordText2;
////	String username,password1,password2;
////	JTextArea status;
////	JButton registerButton;
////	JLabel usernameLable,passwordLable1,passwordLable2;
////	JPanel upPanel;
////	void init() {
////		// TODO 自动生成的构造函数存根
////		upPanel=new JPanel();
////		upPanel.setLayout(new GridLayout(3,2));
////		usernameText=new JTextField();
////		passwordText1=new JPasswordField();
////		passwordText2=new JPasswordField();
////		usernameLable=new JLabel("用户名");
////		passwordLable1=new JLabel("密码");
////		passwordLable2=new JLabel("重复密码");
////		usernameText.setColumns(30);
////		passwordText1.setColumns(30);
////		passwordText2.setColumns(30);
////		upPanel.add(usernameLable);
////		upPanel.add(usernameText);
////		upPanel.add(passwordLable1);
////		upPanel.add(passwordText1);		
////		upPanel.add(passwordLable2);
////		upPanel.add(passwordText2);
////		
////		status=new JTextArea("请填写信息");
////		status.setEditable(false);
////		registerButton=new JButton("注册");
////		this.add(status,"North");
////		this.add(upPanel,"Center");
////		this.add(registerButton,"South");
////		registerButton.addActionListener(new ActionListener() {
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				// TODO 自动生成的方法存根
////				status.setText("");
////				username=usernameText.getText();
////				password1=passwordText1.getText();
////				password2=passwordText2.getText();
////				if(password1.equals(password2)) {
////					status.append("注册成功,您的账号为:");
////					status.append(InteractWithServer.registerID(username, password1));
////				}else {
////					status.setText("注册失败，请确认密码");
////				}
////			}
////		});
////	}
////	
////	public register() {
////		// TODO 自动生成的构造函数存根
////		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/qq_logo.png"));
////		setTitle("注册窗口");
////		init();
////		setSize(430, 335);
////		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Already there
////		setResizable(false);
////		setVisible(true);
////	}
////}
