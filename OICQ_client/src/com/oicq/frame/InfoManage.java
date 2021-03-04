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
import com.sun.org.apache.xml.internal.security.Init;

/**   
 * @ClassName:  infoManage  
 * @Description: 好友群聊简易添加界面  仿照主界面downPanel的左右设计
 */  

public class InfoManage extends JFrame{
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
	void init() {
		// TODO 自动生成的构造函数存根
		upJPanel = new JPanel();
		upJPanel.setLayout(null);
		upJPanel.setBounds(0, 0, 288, 432);
		
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
		peopleButton.setText("信息查询");
		peopleButton.setSelected(true);
		peopleButton.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {

			}

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
		groupButton.setBounds(168, 0, 96, 36);
		groupButton.setText("信息修改");
		groupButton.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

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
		rightJPanel.setLayout(null);
		rightJPanel.setBounds(0, 0, 288, 280);
		
		groupStatus=new JTextArea();
		groupStatus.setEditable(false);
		groupStatus.setBounds(30,10,228,36);
		groupStatus.setText("未完善 咕咕咕");
		rightJPanel.add(groupStatus);
		
		groupLable=new JLabel();
		groupLable.setBounds(0,100,50,36);
		groupLable.setText("输入id:");
		
		rightJPanel.add(groupLable);
		groupidText=new JTextField();
		groupidText.setBounds(50,100,188,36);
		rightJPanel.add(groupidText);
		
		groupAddButton=new JButton("添加");
		groupAddButton.setBounds(100,200,88,48);
		rightJPanel.add(groupAddButton);
		
		groupAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				groupStatus.setText("");
				groupID=groupidText.getText();
				try {
					Integer.valueOf(groupID).intValue();
				} catch (NumberFormatException e2) {
					groupStatus.setText("输入的不是合法群聊的id，要求纯数字");
					return;
				}

				int curstatus=InteractWithServer.groupRequest(userInfo.getUserId(), groupID);
				if(curstatus == 0) {
					groupStatus.setText("您输入的ID不存在,请确认!");
				}else if (curstatus == 1){
					groupStatus.setText("您已经是群成员,请确认!");
				}else {
					groupStatus.setText("添加成功,您已经是该群聊成员,重启界面生效");
				}
			}
		});
		
		///left -> friend
		leftJPanel=new JPanel();
		leftJPanel.setLayout(null);
		leftJPanel.setBounds(0, 0, 288, 280);
		
		friendStatus=new JTextArea();
		friendStatus.setEditable(false);
		friendStatus.setBounds(30,10,228,36);
		friendStatus.setText("未完善 咕咕咕");
		leftJPanel.add(friendStatus);
		
		friendLable=new JLabel();
		friendLable.setBounds(0,50,50,36);
		friendLable.setText("输入id:");
		
		leftJPanel.add(friendLable);
		useridText=new JTextField();
		useridText.setBounds(50,50,188,36);
		leftJPanel.add(useridText);
		
		friendAddButton=new JButton("添加");
		friendAddButton.setBounds(100,100,88,48);
		leftJPanel.add(friendAddButton);
		
		friendAddButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				friendStatus.setText("");
				friendID=useridText.getText();
				try {
					Integer.valueOf(friendID).intValue();
				} catch (NumberFormatException e2) {
					friendStatus.setText("输入的不是合法用户名的id，要求纯数字");
					return;
				}
				if(userInfo.getUserId()==friendID) {
					friendStatus.setText("您不能加自己为好友(⊙o⊙)!");
					return;
				}
				int curstatus=InteractWithServer.friendRequest(userInfo.getUserId(), friendID);
				if(curstatus == 0) {
					friendStatus.setText("您输入的ID不存在,请确认!");
				}else if (curstatus == 1){
					friendStatus.setText("该用户已经是您的好友,请确认!");
				}else {
					friendStatus.setText("添加成功,重启生效!单向好友则只能单向聊天哦");
				}
			}
		});
		friendScrollPane = new JScrollPane(leftJPanel);
		// 设置滚动条样式
		friendScrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		// 设置滚动速率
		friendScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		friendScrollPane.setBounds(0, 36, 288, 395);

	}
	
	public InfoManage(User info) {
		this.userInfo=info;
		// TODO 自动生成的构造函数存根
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/qq_logo.png"));
		
		setTitle("聊天管理");
		init();
		setLayout(null);
		upJPanel.add(peopleButtonExtend);
		upJPanel.add(peopleButton);
		upJPanel.add(groupButtonExtend);
		upJPanel.add(groupButton);
		upJPanel.add(friendScrollPane);
		this.add(upJPanel);
		
		setSize(288, 432);
		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
