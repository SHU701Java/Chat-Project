package com.oicq.frame;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.oicq.client.InteractWithServer;
import com.oicq.user.User;

/**
 * @ClassName: UserAdd
 * @Description: 好友群聊简易添加界面 仿照主界面downPanel的左右设计
 */

public class UserAdd extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField useridText, groupidText, groupNameText;
	String friendID, groupID;
//	JTextArea friendStatus, groupStatus;
	User userInfo;
	JButton friendAddButton, groupAddButton, groupCreateAddButton;
	JButton peopleButton, groupButton, groupCreateButton;
	JButton groupAddcancel,friendAddcancel,createGroupcancel;
	ButtonGroup peopleOrGroup;
	JRadioButton peopleRadioButton, groupRadioButton, groupCreateRadioButton;
	private JScrollPane friendScrollPane;
	JLabel friendLable, groupLable, groupCreateLable;
	JPanel upJPanel, friendAddJpanel, groupAddJpanel, groupCreateJpanel;
	JFrame thisFrame;

	void init() {
		// TODO 自动生成的构造函数存根
		upJPanel = new JPanel();
		upJPanel.setBorder(null);
		upJPanel.setLayout(null);
		upJPanel.setBounds(0, 0, 288, 432);
		upJPanel.setBackground(new Color(255, 255, 255));

		/**
		 * radiobutton peopel&group
		 */
		peopleButton = new JButton();
		peopleButton.setBounds(0, 0, 48, 36);
		peopleButton.setContentAreaFilled(false);
		peopleButton.setFocusPainted(false);
		peopleButton.setBorderPainted(false);
		peopleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				peopleRadioButton.setSelected(true);
				peopleRadioButton.requestFocus();
			}
		});
		peopleRadioButton = new JRadioButton();
		peopleRadioButton.setBounds(16, 0, 80, 36);
		peopleRadioButton.setBackground(new Color(255, 255, 255));
		peopleRadioButton.setText("添加好友");
		peopleRadioButton.setSelected(true);
		peopleRadioButton.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				friendScrollPane.setViewportView(friendAddJpanel);
			}
		});
		// 加群
		groupButton = new JButton();
		groupButton.setBounds(104, 0, 48, 36);
		groupButton.setContentAreaFilled(false);
		groupButton.setFocusPainted(false);
		groupButton.setBorderPainted(false);
		groupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupRadioButton.setSelected(true);
				groupRadioButton.requestFocus();
			}
		});
		groupRadioButton = new JRadioButton();
		groupRadioButton.setBackground(new Color(255, 255, 255));
		groupRadioButton.setBounds(106, 0, 80, 36);
		groupRadioButton.setText("添加群聊");
		groupRadioButton.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				friendScrollPane.setViewportView(groupAddJpanel);
			}
		});
		// 创建群
		groupCreateButton = new JButton();
		groupCreateButton.setBounds(194, 0, 48, 36);
		groupCreateButton.setContentAreaFilled(false);
		groupCreateButton.setFocusPainted(false);
		groupCreateButton.setBorderPainted(false);
		groupCreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupCreateRadioButton.setSelected(true);
				groupCreateRadioButton.requestFocus();
			}
		});
		groupCreateRadioButton = new JRadioButton();
		groupCreateRadioButton.setBackground(new Color(255, 255, 255));
		groupCreateRadioButton.setBounds(196, 0, 80, 36);
		groupCreateRadioButton.setText("创建群聊");
		groupCreateRadioButton.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				friendScrollPane.setViewportView(groupCreateJpanel);
			}
		});

		peopleOrGroup = new ButtonGroup();
		peopleOrGroup.add(peopleRadioButton);
		peopleOrGroup.add(groupRadioButton);
		peopleOrGroup.add(groupCreateRadioButton);

		/// right -> group
		groupAddJpanel = new JPanel();
		groupAddJpanel.setBorder(null);
		groupAddJpanel.setLayout(null);
		groupAddJpanel.setBackground(new Color(255, 255, 255));
		groupAddJpanel.setBounds(0, 0, 288, 280);

		groupLable = new JLabel("输入5位群聊id:");
		// groupLable.setBounds(10,95,70,36);
		groupLable.setBounds(100, 15, 230, 40);

		groupAddJpanel.add(groupLable);
		groupidText = new JTextField();
		groupidText.setBounds(68, 60, 150, 30);
		groupAddJpanel.add(groupidText);

		groupAddcancel=new JButton("取消");
		groupAddcancel.setBounds(145, 100, 60, 30);
		groupAddcancel.setBackground(new Color(9,163,220));
		groupAddJpanel.add(groupAddcancel);
		groupAddcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);	
			}
		});
		
		groupAddButton = new JButton("添加");
		groupAddButton.setBounds(77, 100, 60, 30);
		groupAddButton.setBackground(new Color(9, 163, 220));
		groupAddJpanel.add(groupAddButton);
		groupAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				groupID = groupidText.getText();
				try {
					Integer.valueOf(groupID).intValue();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(thisFrame, "输入的不是合法群聊的id，要求纯数字");
					return;
				}
				int curstatus = InteractWithServer.groupRequest(userInfo.getUserId(), groupID);
				if (curstatus == 0) {
					JOptionPane.showMessageDialog(thisFrame, "您输入的ID不存在,请确认!");
				} else if (curstatus == 1) {
					JOptionPane.showMessageDialog(thisFrame, "您已经是群成员,请确认!");
				} else {
					JOptionPane.showMessageDialog(thisFrame, "添加成功,您已经是该群聊成员,刷新生效");
				}
			}
		});
		
		// 创建群聊jpanel
		
		groupCreateJpanel = new JPanel();
		groupCreateJpanel.setBorder(null);
		groupCreateJpanel.setLayout(null);
		groupCreateJpanel.setBackground(new Color(255, 255, 255));
		groupCreateJpanel.setBounds(0, 0, 288, 280);

		groupCreateLable = new JLabel("输入你即将创建的群聊名字:");
		// groupLable.setBounds(10,95,70,36);
		groupCreateLable.setBounds(75, 15, 230, 40);

		groupCreateJpanel.add(groupCreateLable);
		groupNameText = new JTextField();
		groupNameText.setBounds(68, 60, 150, 30);
		groupCreateJpanel.add(groupNameText);
		
		createGroupcancel=new JButton("取消");
		createGroupcancel.setBounds(145, 100, 60, 30);
		createGroupcancel.setBackground(new Color(9,163,220));
		groupCreateJpanel.add(createGroupcancel);
		createGroupcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);	
			}
		});

		groupCreateAddButton = new JButton("确定");
		groupCreateAddButton.setBounds(77, 100, 60, 30);
		groupCreateAddButton.setBackground(new Color(9, 163, 220));
		groupCreateJpanel.add(groupCreateAddButton);
		groupCreateAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String groupName = groupNameText.getText();
				String groupID = InteractWithServer.createGroupID(userInfo.getUserId(), groupName);
				JOptionPane.showMessageDialog(thisFrame, "创建成功,您是该群群主,刷新生效,群id为" + groupID);
			}
		});

		/// left -> friend
		friendAddJpanel = new JPanel();
		friendAddJpanel.setBorder(null);
		friendAddJpanel.setLayout(null);
		friendAddJpanel.setBackground(new Color(255, 255, 255));
		friendAddJpanel.setBounds(0, 0, 288, 280);

		friendLable = new JLabel("输入5位用户id:");
		friendLable.setBounds(100, 15, 230, 40);

		friendAddJpanel.add(friendLable);
		useridText = new JTextField();
		useridText.setBounds(68, 60, 150, 30);
		friendAddJpanel.add(useridText);

		friendAddcancel=new JButton("取消");
		friendAddcancel.setBounds(145, 100, 60, 30);
		friendAddcancel.setBackground(new Color(9,163,220));
		friendAddJpanel.add(friendAddcancel);
		friendAddcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);	
			}
		});
		
		friendAddButton = new JButton("添加");
		friendAddButton.setBounds(77, 100, 60, 30);
		friendAddButton.setBackground(new Color(9, 163, 220));
		friendAddJpanel.add(friendAddButton);

		friendAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				friendID = useridText.getText();
				try {
					Integer.valueOf(friendID).intValue();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(thisFrame, "输入的不是合法用户名的id，要求纯数字");
					return;
				}
				if (userInfo.getUserId() == friendID) {
					JOptionPane.showMessageDialog(thisFrame, "您不能加自己为好友(⊙o⊙)!");
					return;
				}
				int curstatus = InteractWithServer.friendRequest(userInfo.getUserId(), friendID);
				if (curstatus == 0) {
					JOptionPane.showMessageDialog(thisFrame, "您输入的ID不存在,请确认!");
				} else if (curstatus == 1) {
					JOptionPane.showMessageDialog(thisFrame, "该用户已经是您的好友,请确认!");
				} else {
					JOptionPane.showMessageDialog(thisFrame, "添加成功,该用户成为您的好友,刷新生效");
				}
			}
		});
		friendScrollPane = new JScrollPane(friendAddJpanel);

		// 设置滚动条样式
		friendScrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		// 设置滚动速率
		friendScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		friendScrollPane.setBounds(0, 36, 288, 395);
		thisFrame = this;

	}

	public UserAdd(User info) {
		this.userInfo = info;

		// TODO 自动生成的构造函数存根
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/qq_logo.png"));
		setBackground(new Color(255, 255, 255));
		setTitle("添加好友和群聊");
		init();
		setLayout(null);

		upJPanel.add(peopleButton);
		upJPanel.add(peopleRadioButton);
		upJPanel.add(groupButton);
		upJPanel.add(groupRadioButton);
		upJPanel.add(groupCreateButton);
		upJPanel.add(groupCreateRadioButton);
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
