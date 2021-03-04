package com.oicq.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.oicq.client.InteractWithServer;
import com.oicq.config.UserInfo;
import com.oicq.config.UserInfo.FriendsOrGroups;
import com.oicq.user.User;

/**   
 * @ClassName:  UserAdd  
 * @Description: 好友群聊简易添加界面  仿照主界面downPanel的左右设计
 */  

public class UserAdd extends JFrame{
	private LoginMousemove adapter;
	JTextField useridText,groupidText;
	String friendID,groupID;
	JTextArea friendStatus,groupStatus;
	User userInfo;
	JButton friendAddButton,groupAddButton,groupCreateButton,peopleButtonExtend, groupButtonExtend;;
	ButtonGroup peopleOrGroup;
	JRadioButton peopleButton, groupButton;
	private JScrollPane friendScrollPane;
	JLabel friendLable,groupLable;
	JPanel upJPanel,leftJPanel,rightJPanel;
	JFrame thisFrame;
	void init() {
		// TODO 自动生成的构造函数存根
		upJPanel = new JPanel();
		upJPanel.setBorder(null);
		upJPanel.setLayout(null);
		upJPanel.setBounds(0, 0, 288, 432);
		upJPanel.setBackground(new Color(255,255,255));
		
		/**
		 * radiobutton peopel&group
		 */
		peopleButtonExtend = new JButton();
		peopleButtonExtend.setBounds(0, 0, 48, 36);
		peopleButtonExtend.setContentAreaFilled(false);
		peopleButtonExtend.setFocusPainted(false);
		peopleButtonExtend.setBorderPainted(false);
		peopleButtonExtend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				peopleButton.setSelected(true);
				peopleButton.requestFocus();
			}
		});
		peopleButton = new JRadioButton();
		peopleButton.setBounds(24, 0, 96, 36);
		peopleButton.setBackground(new Color(255,255,255));
		peopleButton.setText("添加好友");
		peopleButton.setSelected(true);
		peopleButton.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) { }
			@Override
			public void focusGained(FocusEvent arg0) {
				friendScrollPane.setViewportView(leftJPanel);
			}
		});
		groupButtonExtend = new JButton();
		groupButtonExtend.setBounds(144, 0, 48, 36);
		groupButtonExtend.setContentAreaFilled(false);
		groupButtonExtend.setFocusPainted(false);
		groupButtonExtend.setBorderPainted(false);
		groupButtonExtend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupButton.setSelected(true);
				groupButton.requestFocus();
			}
		});
		groupButton = new JRadioButton();
		groupButton.setBackground(new Color(255,255,255));
		groupButton.setBounds(168, 0, 96, 36);
		groupButton.setText("添加群聊");
		groupButton.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { }
			@Override
			public void focusGained(FocusEvent e) {
				friendScrollPane.setViewportView(rightJPanel);
			}
		});
		
		peopleOrGroup = new ButtonGroup();
		peopleOrGroup.add(peopleButton);
		peopleOrGroup.add(groupButton);
		
		///right -> group
		rightJPanel=new JPanel();
		rightJPanel.setBorder(null);
		rightJPanel.setLayout(null);
		rightJPanel.setBackground(new Color(255,255,255));
		rightJPanel.setBounds(0, 0, 288, 280);
		
		groupLable=new JLabel("输入5位群聊id:");
		//groupLable.setBounds(10,95,70,36);
		groupLable.setBounds(55,15,230,40);

		rightJPanel.add(groupLable);
		groupidText=new JTextField();
		groupidText.setBounds(40,60,150,30);
		rightJPanel.add(groupidText);
		
		groupAddButton=new JButton("添加");
		groupAddButton.setBounds(60,100,60,30);
		groupAddButton.setBackground(new Color(9, 163, 220));
		rightJPanel.add(groupAddButton);
		groupAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				groupID=groupidText.getText();
				try {
					Integer.valueOf(groupID).intValue();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(thisFrame,"输入的不是合法群聊的id，要求纯数字");
					return;
				}
				int curstatus=InteractWithServer.groupRequest(userInfo.getUserId(), groupID);
				if(curstatus == 0) {
					JOptionPane.showMessageDialog(thisFrame,"您输入的ID不存在,请确认!");
				}else if (curstatus == 1){
					JOptionPane.showMessageDialog(thisFrame,"您已经是群成员,请确认!");
				}else {
					JOptionPane.showMessageDialog(thisFrame,"添加成功,您已经是该群聊成员,刷新生效");
				}
			}
		});
		
		
		///left -> friend
		leftJPanel=new JPanel();
		leftJPanel.setBorder(null);
		leftJPanel.setLayout(null);
		leftJPanel.setBackground(new Color(255,255,255));
		leftJPanel.setBounds(0, 0, 288, 280);

		friendLable=new JLabel("输入5位用户id:");
		friendLable.setBounds(55,15,230,40);
		
		leftJPanel.add(friendLable);
		useridText=new JTextField();
		useridText.setBounds(40,60,150,30);
		leftJPanel.add(useridText);
		
		friendAddButton=new JButton("添加");
		friendAddButton.setBounds(60,100,60,30);
		friendAddButton.setBackground(new Color(9, 163, 220));
		leftJPanel.add(friendAddButton);
		
		friendAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				friendID=useridText.getText();
				try {
					Integer.valueOf(friendID).intValue();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(thisFrame,"输入的不是合法用户名的id，要求纯数字");
					return;
				}
				if(userInfo.getUserId()==friendID) {
					JOptionPane.showMessageDialog(thisFrame,"您不能加自己为好友(⊙o⊙)!");
					return;
				}
				int curstatus=InteractWithServer.friendRequest(userInfo.getUserId(), friendID);
				if(curstatus == 0) {
					JOptionPane.showMessageDialog(thisFrame,"您输入的ID不存在,请确认!");
				}else if (curstatus == 1){
					JOptionPane.showMessageDialog(thisFrame,"该用户已经是您的好友,请确认!");
				}else {
					JOptionPane.showMessageDialog(thisFrame,"添加成功,该用户成为您的好友,刷新生效");
				}
			}
		});
		friendScrollPane = new JScrollPane(leftJPanel);
		
		// 设置滚动条样式
		friendScrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		// 设置滚动速率
		friendScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		friendScrollPane.setBounds(0, 36, 288, 395);
		thisFrame=this;

	}
	
	public UserAdd(User info) {
		this.userInfo=info;
		
		// TODO 自动生成的构造函数存根
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/qq_logo.png"));
		setBackground(new Color(255,255,255));
		setTitle("添加好友和群聊");
		init();
		setLayout(null);
		
		upJPanel.add(peopleButtonExtend);
		upJPanel.add(peopleButton);
		upJPanel.add(groupButtonExtend);
		upJPanel.add(groupButton);
		upJPanel.add(friendScrollPane);
		this.add(upJPanel);
		
		setSize(300, 300);
		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
