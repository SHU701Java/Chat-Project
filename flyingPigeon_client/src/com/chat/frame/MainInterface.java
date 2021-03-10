package com.chat.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.*;

import com.chat.client.InteractWithServer;
import com.chat.config.UserInfo.FriendsOrGroups;
import com.chat.user.User;

/**
 * @ClassName: MainInterface
 * @Description: 用于显示主界面 用户的相关信息和好友等
 */
public final class MainInterface extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel upPanel, downPanel, friendPane, groupPane;
	private JButton minimize, close, tradesButton, peopelButtonExtend, groupButtonExtend;
	private JLabel /* logo, */ /* headPortrait, */ nameLabel;
	private JButton headPortrait, friendManage, refreshManage;
	private JButton friendSearch;
	private JTextField searchFriend;
	private Box nameBox;
	private JTextField tradesTextField;
	private ButtonGroup peopelOrGroup;
	private JRadioButton peopelButton, groupButton;
	User userInfo;
	private JScrollPane friendScrollPane;
	private ButtonGroup friendButtonGroup, groupButtonGroup;
	private String userId;
	private ScrollBarUI tt;

	// 用户信息部分
	private static HashMap<String, FriendModel> friend;
	private static HashMap<String, GroupModel> group;
	private static HashMap<String, ChatWithFriend> withFriend;
	private static HashMap<String, ChatWithFriend> withGroup;

	static {
		friend = new HashMap<String, FriendModel>();
		group = new HashMap<String, GroupModel>();
		withFriend = new HashMap<String, ChatWithFriend>();
		withGroup = new HashMap<String, ChatWithFriend>();
	}

	public MainInterface(String userId) {
		// 获取用户信息
		userInfo = InteractWithServer.getUserInfo(userId);

		// 调试使用
		System.out.println("----------- 个人信息 --------------");
		System.out.println("ID：" + userInfo.getUserId());
		System.out.println("昵称：" + userInfo.getUserName());
		System.out.println("Email：" + userInfo.getUserEmail());
		System.out.println("性别：" + userInfo.getUserSex());
		System.out.println("生日：" + userInfo.getUserBirthday());
		System.out.println("头像：" + userInfo.getUserAvatar());
		System.out.println("个性签名：" + userInfo.getUserTrades());
		System.out.println("注册时间：" + userInfo.getUserRegistertime());
		System.out.print("好友列表 : ");
		for (int i = 0; i < userInfo.getFriends().size(); i++)
			System.out.print(userInfo.getFriends().get(i).getName() + " ");
		System.out.print("\n群列表 ： ");
		for (int i = 0; i < userInfo.getGroups().size(); i++)
			System.out.print(userInfo.getGroups().get(i).getName() + " ");
		System.out.println("\n----------- END --------------");

		// 界面部分
		// 更改显示的小图标
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/qq_logo.png"));
		setTitle("主界面 --" + userInfo.getUserName() + "在线");
		init();
		setLayout(null);
		upPanel.add(close);
		upPanel.add(minimize);
		// upPanel.add(logo);
		upPanel.add(headPortrait);
		upPanel.add(nameBox);
		upPanel.add(tradesButton);
		upPanel.add(tradesTextField);
		upPanel.add(friendManage);
		upPanel.add(refreshManage);
		upPanel.add(friendSearch);
		upPanel.add(searchFriend);
		downPanel.add(peopelButtonExtend);
		downPanel.add(peopelButton);
		downPanel.add(groupButton);
		downPanel.add(groupButtonExtend);
		downPanel.add(friendScrollPane);
		add(upPanel);
		add(downPanel);

		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		setSize(288, 573);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setLocationRelativeTo(null);
		// min height = 281 height = 528
		// max height = 608 height = 730
		setVisible(true);
	}

	void updateFriendModelSearch(String Id) {
		int friendsNumber = userInfo.getFriends().size();
		
		friendPane = new JPanel();
		friendPane.setLayout(null);
		friendPane.setBounds(0, 0, 288, friendsNumber * 51);
		friendPane.setPreferredSize(new Dimension(270, friendsNumber * 51));
		friendButtonGroup = new ButtonGroup();
		for (int i = 0; i < friendsNumber; i++) {
			FriendsOrGroups userFriend = userInfo.getFriends().get(i);
			String nameOfFriend=userFriend.getId();
			System.out.println(nameOfFriend);
			if(nameOfFriend.equals(Id)) {
				String fAvatar = userFriend.getAvatar(), fName = userFriend.getName(), fTrades = userFriend.getTrades(),
					fid = userFriend.getId(), fOnline = userFriend.getStatus();
				friend.put(fid, new FriendModel(fAvatar, fName, fTrades, fid, fOnline));
				friend.get(fid).setBounds(0, 0, 288, 51);
				friend.get(fid).addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						withFriend.put(fid, new ChatWithFriend(userInfo.getUserId(), userInfo.getUserName(), fid,
								fAvatar, fName, fTrades, false));
					}
				}
				});
				friendPane.add(friend.get(fid));
				friendButtonGroup.add(friend.get(fid));
			}
		}
	}
	
	
	void updateFriendModel() {
		int friendsNumber = userInfo.getFriends().size();
		friendPane = new JPanel();
		friendPane.setLayout(null);
		friendPane.setBounds(0, 0, 288, friendsNumber * 51);
		friendPane.setPreferredSize(new Dimension(270, friendsNumber * 51));
		int idx=0;
		friendButtonGroup = new ButtonGroup();
		for (int i = 0; i < friendsNumber; i++) {
			FriendsOrGroups userFriend = userInfo.getFriends().get(i);
			String fAvatar = userFriend.getAvatar(), fName = userFriend.getName(), fTrades = userFriend.getTrades(),
					fid = userFriend.getId(), fOnline = userFriend.getStatus();
			if(fOnline.equals("在线")) {
				friend.put(fid, new FriendModel(fAvatar, fName, fTrades, fid, fOnline));
				friend.get(fid).setBounds(0, idx * 51, 288, 51);
				friend.get(fid).addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							withFriend.put(fid, new ChatWithFriend(userInfo.getUserId(), userInfo.getUserName(), fid,
									fAvatar, fName, fTrades, false));
						}
					}
				});
				friendPane.add(friend.get(fid));
				friendButtonGroup.add(friend.get(fid));
				idx++;
			}
		}
		for (int i = 0; i < friendsNumber; i++) {
			FriendsOrGroups userFriend = userInfo.getFriends().get(i);
			String fAvatar = userFriend.getAvatar(), fName = userFriend.getName(), fTrades = userFriend.getTrades(),
					fid = userFriend.getId(), fOnline = userFriend.getStatus();
			if(fOnline.equals("离线")) {
				friend.put(fid, new FriendModel(fAvatar, fName, fTrades, fid, fOnline));
				friend.get(fid).setBounds(0, idx * 51, 288, 51);
				friend.get(fid).addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							withFriend.put(fid, new ChatWithFriend(userInfo.getUserId(), userInfo.getUserName(), fid,
									fAvatar, fName, fTrades, false));
						}
					}
				});
				friendPane.add(friend.get(fid));
				friendButtonGroup.add(friend.get(fid));
				idx++;
			}
		}
	}

	void updateGroupModel() {
		int groupsNumber = userInfo.getGroups().size();
		groupPane = new JPanel();
		groupPane.setLayout(null);
		groupPane.setBounds(0, 0, 288, groupsNumber * 51);
		groupPane.setPreferredSize(new Dimension(270, groupsNumber * 51));
		groupButtonGroup = new ButtonGroup();
		for (int j = 0; j < groupsNumber; j++) {
			FriendsOrGroups userGroup = userInfo.getGroups().get(j);
			String gAvatar = userGroup.getAvatar(), gName = userGroup.getName(), gTrades = userGroup.getTrades(),
					gid = userGroup.getId();
			group.put(gid, new GroupModel(gAvatar, gName, gTrades, gid, "./Data/Avatar/Group/"));
			group.get(gid).setBounds(0, j * 51, 288, 51);
			group.get(gid).addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						withGroup.put(gid, new ChatWithFriend(userInfo.getUserId(), userInfo.getUserName(), gid,
								gAvatar, gName, gTrades, true));
					}
				}
			});
			groupPane.add(group.get(gid));
			groupPane.setVisible(true);
			groupButtonGroup.add(group.get(gid));
		}
	}

	private void init() {
		/**
		 * Panel up
		 */
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 288, 140);
		upPanel.setBackground(new Color(205, 205, 205));
		/**
		 * Button close
		 */
		close = new JButton("");
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBounds(261, 0, 25, 27);
		close.setContentAreaFilled(false); // set don't draw message area
		close.setBorderPainted(false); // set don't draw border
		close.setFocusPainted(false);
		close.setToolTipText("关闭");
		close.setIcon(new ImageIcon("./res/Misc/FileManager/closebutton_normal.png")); // normal
																						// png
		close.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png")); // rollover
																								// png
		close.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png")); // press
																							// png
		ExitListenter closeListenter = new ExitListenter();
		close.addActionListener(closeListenter);
		/**
		 * Button minimize
		 */
		minimize = new JButton();
		minimize.setMargin(new Insets(0, 0, 0, 0));
		minimize.setBounds(235, 0, 25, 27);
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
		headPortrait = new JButton();
		headPortrait.setBounds(10, 43, 61, 60);
		headPortrait.setVisible(true);
		Image headPic = (GetAvatar.getAvatarImage(userInfo.getUserId(), "./Data/Avatar/User/",
				userInfo.getUserAvatar())).getImage().getScaledInstance(61, 60, Image.SCALE_DEFAULT);
		headPortrait.setIcon(new ImageIcon(headPic));
		headPortrait.addActionListener(this);
		/*
		 * headPortrait.addActionListener(new ActionListener(){
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { // TODO 自动生成的方法存根
		 * //showOpenDialog(Component a); WindowReader win=new WindowReader();
		 * //win.setTitle("上传头像");
		 * 
		 * } });
		 */
		/**
		 * box name
		 */
		nameBox = Box.createHorizontalBox(); // 便于以后添加别的组件
		nameBox.setBounds(77, 43, 158, 17);
		String username = userInfo.getUserName(); // 用户昵称
		// Todo something to get user name !
		nameLabel = new JLabel(username);
		nameLabel.setForeground(Color.WHITE);
		nameBox.add(nameLabel);
		/**
		 * trades
		 */
		tradesButton = new JButton();
		tradesButton.setHorizontalAlignment(SwingConstants.LEFT);
		tradesButton.setMargin(new Insets(0, 0, 0, 0));
		tradesButton.setBounds(77, 64, 137, 23);
		tradesButton.setContentAreaFilled(false);
		tradesButton.setBorderPainted(false);
		tradesButton.setRolloverIcon(new ImageIcon("./res/MainInterface/ContactFilter_splitter.png"));
		String trades = userInfo.getUserTrades();
		// Todo somethings get trades
		if (trades.equals("")) {
			trades = "编辑个性签名";
		}
		tradesButton.setText(trades);
		tradesButton.setToolTipText(trades);
		tradesTextField = new JTextField();
		tradesTextField.setBounds(77, 64, 137, 23);
		tradesTextField.setVisible(false);

		tradesButton.addActionListener(new ActionListener() { // Button
																// 点击之后变为TextField

			@Override
			public void actionPerformed(ActionEvent e) {
				tradesButton.setVisible(false);
				tradesTextField.setVisible(true);
				if (tradesButton.getText().equals("编辑个性签名")) {
					tradesTextField.setText("");
				} else {
					tradesTextField.setText(tradesButton.getText());
				}
				tradesTextField.requestFocus();
			}
		});
		tradesTextField.addFocusListener(new FocusListener() { // TextField失去焦点之后变为Button并将更改后的内容传送给服务器

			@Override
			public void focusLost(FocusEvent e) {
				tradesTextField.setVisible(false);
				if (tradesTextField.getText().equals("")) {
					tradesButton.setText("编辑个性签名");
				} else {
					tradesButton.setText(tradesTextField.getText());

					// 更新服务端数据
					InteractWithServer.setMyTrades(userInfo.getUserId(), tradesTextField.getText());
				}
				// Send the message to server

				tradesButton.setVisible(true);
			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		tradesTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tradesTextField.setVisible(false);
				if (tradesTextField.getText().equals("")) {
					tradesButton.setText("编辑个性签名");
				} else {
					tradesButton.setText(tradesTextField.getText());
				}
				// Send the message to server

				tradesButton.setVisible(true);
			}
		});

		friendManage = new JButton();
		friendManage.setBounds(5, 105, 32, 32);
		friendManage.setVisible(true);
		friendManage.setToolTipText("好友管理");
		friendManage.setIcon(new ImageIcon("./res/Misc/FileManager/friendManage_normal.png")); // normal
																						// png
		// png
//		friendManage.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png")); // rollover
//																								// png
//		friendManage.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png")); // press
//																							// png
		friendManage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				UserAdd friendAdd=new UserAdd(userInfo);
			}
		});
		
//		infoManage=new JButton();
//		infoManage.setBounds(110,90,32,32);
//		infoManage.setVisible(true);
//		infoManage.setToolTipText("资料管理");
//		infoManage.setIcon(new ImageIcon("./res/Misc/FileManager/infoManage_normal.png")); // normal
//						
//		infoManage.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO 自动生成的方法存根
//				InfoManage manage=new InfoManage(userInfo);
//			}
//		});
		

		refreshManage = new JButton();
		refreshManage.setBounds(40, 105, 32, 32);
		refreshManage.setVisible(true);
		refreshManage.setToolTipText("刷新");
		refreshManage.setIcon(new ImageIcon("./res/Misc/FileManager/refreshManage_normal.png")); // normal

		// png
//		friendManage.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png")); // rollover
//																								// png
//		friendManage.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png")); // press
//																							// png
		refreshManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				userInfo = InteractWithServer.getUserInfo(userInfo.getUserId());
				updateFriendModel();
				updateGroupModel();
				update();
			}
		});
		
		friendSearch=new JButton();
		friendSearch.setBounds(77,105,32,32);
		friendSearch.setVisible(true);
		friendSearch.setToolTipText("搜索好友");
		friendSearch.setIcon(new ImageIcon("./res/Misc/FileManager/searchFriend_normal.png"));
		searchFriend=new JTextField();
		searchFriend.setBounds(110, 105, 100, 32);
		searchFriend.setVisible(true);
		friendSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String tmp=searchFriend.getText();
				userId=searchFriend.getText();
				updateFriendModelSearch(tmp);
				update();
			}
		});
		
		
		
		
		/**
		 * panel down
		 */
		downPanel = new JPanel();
		downPanel.setLayout(null);
		downPanel.setBounds(0, 140, 288, 432);
		/**
		 * radiobutton peopel&group
		 */
		peopelButtonExtend = new JButton();
		peopelButtonExtend.setBounds(0, 0, 48, 36);
		peopelButtonExtend.setContentAreaFilled(false);
		peopelButtonExtend.setFocusPainted(false);
		peopelButtonExtend.setBorderPainted(false);
		peopelButtonExtend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				peopelButton.setSelected(true);
				peopelButton.requestFocus();
			}
		});
		peopelButton = new JRadioButton();
		peopelButton.setBounds(48, 0, 96, 36);
		peopelButton.setHorizontalTextPosition(SwingConstants.CENTER);
		peopelButton.setIcon(new ImageIcon("./res/mainpanel/icon_contacts_normal.png"));
		peopelButton.setRolloverIcon(new ImageIcon("./res/mainpanel/icon_contacts_hover.png"));
		peopelButton.setSelectedIcon(new ImageIcon("./res/mainpanel/icon_contacts_selected.png"));
		peopelButton.setSelected(true);
		peopelButton.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				friendScrollPane.setViewportView(friendPane);
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
		groupButton.setBounds(192, 0, 96, 36);
		groupButton.setIcon(new ImageIcon("./res/mainpanel/icon_group_normal.png"));
		groupButton.setRolloverIcon(new ImageIcon("./res/mainpanel/icon_group_hover.png"));
		groupButton.setSelectedIcon(new ImageIcon("./res/mainpanel/icon_group_selected.png"));
		groupButton.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {
				friendScrollPane.setViewportView(groupPane);
			}
		});
		peopelOrGroup = new ButtonGroup();
		peopelOrGroup.add(peopelButton);
		peopelOrGroup.add(groupButton);
		/**
		 * friend model
		 */
		updateFriendModel();
		/**
		 * group model
		 */
		updateGroupModel();

		friendScrollPane = new JScrollPane(friendPane);
		// 设置滚动条样式
		tt=new ScrollBarUI();
		friendScrollPane.getVerticalScrollBar().setUI(tt);
		// 设置滚动速率
		friendScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		friendScrollPane.setBounds(0, 36, 288, 395);
	}
	void update() {
		friendScrollPane.setViewportView(friendPane);
	}
	public static HashMap<String, FriendModel> getFriend() {
		return friend;
	};

	public static HashMap<String, GroupModel> getGroup() {
		return group;
	}

	public static HashMap<String, ChatWithFriend> getFriendChat() {
		return withFriend;
	}

	public static HashMap<String, ChatWithFriend> getGroupChat() {
		return withGroup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(new JLabel(), "选择");
		File file = jfc.getSelectedFile();
		if (file.isDirectory()) {
			System.out.println("文件夹:" + file.getAbsolutePath());
		} else if (file.isFile()) {
			System.out.println("文件:" + file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
	}
}