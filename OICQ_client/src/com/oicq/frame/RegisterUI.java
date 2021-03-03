package com.oicq.frame;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import com.oicq.client.InteractWithServer;
import com.oicq.config.ChatVerify;

/**   
 * @ClassName:  RegisterUI  
 * @Description: 注册界面   
 * @author: 邬广星
 * @date: 2021-02
 *   
 * JFrame
 * @Copyright: 2021 www.wgx666.monster Inc. All rights reserved.  
 */  

public class RegisterUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame thisFrame;
	JPanel upPanel, all;
	JTextField nickName, email;
	JPasswordField pwd;
	JButton Register, minimize, close;
	
	public RegisterUI() {
		// TODO 自动生成的构造函数存根
		setLayout(null);
		// 更改显示的小图标
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/qq_logo.png"));
		setTitle("注册窗口");
		
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 450, 50);
		upPanel.setBackground(new Color(6, 157, 214));
		
		close = new JButton();
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBounds(420, 0, 30, 30);
		close.setContentAreaFilled(false); // set don't draw message area
		close.setBorderPainted(false); // set don't draw border
		close.setFocusPainted(false); // set don't draw focus painted
		close.setToolTipText("关闭");
		close.setIcon(new ImageIcon("./res/Misc/FileManager/closebutton_normal.png"));
		close.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png"));
		close.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png"));
//		ExitListenter closeListenter = new ExitListenter();
//		close.addActionListener(closeListenter);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
		
		minimize = new JButton();
		minimize.setMargin(new Insets(0, 0, 0, 0));
		minimize.setBounds(390, 0, 30, 30);
		minimize.setContentAreaFilled(false);
		minimize.setBorderPainted(false);
		minimize.setFocusPainted(false);
		minimize.setToolTipText("最小化");
		minimize.setIcon(new ImageIcon("./res/Misc/FileManager/minbutton_normal.png"));
		minimize.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/minbutton_hover.png"));
		minimize.setPressedIcon(new ImageIcon("./res/Misc/FileManager/minbutton_down.png"));
		minimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		
		upPanel.add(minimize);
		upPanel.add(close);
		add(upPanel);
		
		all = new JPanel();
		all.setBounds(0, 51, 450, 400);
		all.setLayout(null);
		
		nickName = new JTextField("昵称");
		nickName.setBounds(80, 50, 300, 50);
		nickName.setForeground(Color.GRAY);
		nickName.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (nickName.getText().trim().equals("")) {
					nickName.setForeground(Color.GRAY);
					nickName.setText("昵称");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (nickName.getText().trim().equals("昵称")) {
					nickName.setText("");
					nickName.setForeground(Color.BLACK);
				}
			}
		});
		
		email = new JTextField("邮箱, 用来找回密码时的验证");
		email.setBounds(80, 250, 300, 50);
		email.setForeground(Color.GRAY);
		email.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (email.getText().trim().equals("")) {
					email.setForeground(Color.GRAY);
					email.setText("邮箱, 用来找回密码时的验证");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (email.getText().trim().equals("邮箱, 用来找回密码时的验证")) {
					email.setText("");
					email.setForeground(Color.BLACK);
				}
			}
		});
		
		pwd = new JPasswordField("密码");
		pwd.setBounds(80, 150, 300, 50);
		pwd.setEchoChar((char) 0);
		pwd.setForeground(Color.GRAY);
		pwd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(pwd.getPassword()).trim().equals("")) {
					pwd.setEchoChar((char) 0);
					pwd.setForeground(Color.GRAY);
					pwd.setText("密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(pwd.getPassword()).trim().equals("密码")) {
					pwd.setEchoChar('•');
					pwd.setForeground(Color.BLACK);
					pwd.setText("");
				}
			}
		});
		
		Register = new JButton("注   册");
		Register.setBounds(50, 350, 350, 50);
		thisFrame = this;
		Register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ChatVerify user = new ChatVerify(nickName.getText().trim(), String.valueOf(pwd.getPassword()).trim());
				String userID = InteractWithServer.registerID(user.getUserId(), user.getUserPassword(), email.getText().trim());
				JOptionPane.showMessageDialog(thisFrame, "注册成功!你的账号为" + userID);
				thisFrame.dispose();
			}
		});
		
		all.add(nickName);
		all.add(pwd);
		all.add(email);
		all.add(Register);
		
		add(all);
		
		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		setSize(450, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Already there
		setExtendedState(JFrame.MAXIMIZED_BOTH); //set Jframe size？
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
}