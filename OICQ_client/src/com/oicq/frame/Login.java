package com.oicq.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.oicq.client.InteractWithServer;
import com.oicq.config.ChatVerify;
import com.oicq.util.getImage;

/**   
 * @ClassName:  Login  
 * @Description: 用于登录界面和主界面的退出按钮 
 */  

public final class Login extends JFrame {
	private JFrame thisJFrame;
	private LoginListener l;
	private static final long serialVersionUID = 1L;
	private static final int DIALOG_WIDTH=400;
    private static final int DIALOG_HEIGHT=320;
    private static final int DIALOG_HEIGHT_EXTEND=500;
    private JButton minimize, close;
    private final JScrollPane contentPanel = new JScrollPane();
    private final JPanel upPanel;
    private JCheckBox remeberPasswdCheckBox, autoLoginCheckBox;
    private JTextField userID_1, userID_2,userID_3,email_address,email;
    private JPasswordField passwd_1, passwd_2,passwd_3;
    private String username, userPasswd;

    public Login() {
    	/*
		 * close
		 */
    	thisJFrame = this;
    	
    	upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 400, 28);
		upPanel.setBackground(new Color(6, 157, 214));
		
		close = new JButton();
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBounds(372, 0, 28, 28);
		close.setContentAreaFilled(false); // set don't draw message area
		close.setBorderPainted(false); // set don't draw border
		close.setFocusPainted(false); // set don't draw focus painted
		close.setToolTipText("关闭");
		close.setIcon(new ImageIcon("./res/Misc/FileManager/closebutton_normal.png"));
		close.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png"));
		close.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png"));
		ExitListenter closeListenter = new ExitListenter();
		close.addActionListener(closeListenter);
		
		/*
		 * minimize
		 */
		minimize = new JButton();
		minimize.setMargin(new Insets(0, 0, 0, 0));
		minimize.setBounds(344, 0, 28, 28);
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
		
		setUndecorated(true);
    	setAlwaysOnTop(true);
    	setResizable(false);
    	setBounds(400,100,DIALOG_WIDTH,DIALOG_HEIGHT);
    	
    	getContentPane().setLayout(new BorderLayout());
    	contentPanel.setBorder(new EmptyBorder(5,5,5,5));
    	getContentPane().add(contentPanel,BorderLayout.CENTER);
    	contentPanel.setLayout(null);
    	contentPanel.setBackground(new Color(255, 255, 255));

		/** 注册延申
		 *
		 */
		JPanel panel = new JPanel();
		panel.setBorder((Border) new TitledBorder(null, "\u6CE8\u518C\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 330, 350, 150);
		panel.setBackground(new Color(255, 255, 255));
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label_3 = new JLabel("昵 称");
		JLabel label_4 = new JLabel("密 码");
        JLabel email_label = new JLabel("邮 箱");
		label_3.setBounds(74, 28, 55, 18);
		label_4.setBounds(74, 62, 55, 18);
        email_label.setBounds(74,96,55,18);
		panel.add(label_3);
		panel.add(label_4);
        panel.add(email_label);
		userID_2 = new JTextField();
		passwd_2 = new JPasswordField();
        email = new JTextField();
		userID_2.setBounds(125,25,150,25);
		passwd_2.setBounds(125,60,150,25);
		email.setBounds(125,95,150,25);
		panel.add(userID_2);
		panel.add(passwd_2);
		panel.add(email);
		userID_2.setColumns(10);
		passwd_2.setColumns(10);
        email.setColumns(10);
		JButton btn_1 = new JButton("取 消");
		JButton btn_2 = new JButton("确 认");

		btn_1.setBounds(70,120,93,23);
		btn_2.setBounds(200,120,93, 23);

		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userID_2.setText("");
				passwd_2.setText("");
				if(Login.this.getHeight()==DIALOG_HEIGHT_EXTEND){
					Login.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
				}
			}
		});
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatVerify user = new ChatVerify(userID_2.getText().trim(), String.valueOf(passwd_2.getPassword()).trim());
				String userID = InteractWithServer.registerID(userID_2.getText(),passwd_2.getText(),email.getText());
				JOptionPane.showMessageDialog(thisJFrame, "注册成功! 你的账号为" + userID);
				userID_2.setText("");
				passwd_2.setText("");
				email.setText("");
				if(Login.this.getHeight()==DIALOG_HEIGHT_EXTEND){
					Login.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
				}
			}
		});

		panel.add(btn_1);
		panel.add(btn_2);

		//找回密码延生
		JPanel findpanel = new JPanel();
		findpanel.setBorder((Border) new TitledBorder(null, "忘 记 密 码", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		findpanel.setBounds(20, 320, 350, 160);
		findpanel.setBackground(new Color(255, 255, 255));
		contentPanel.add(findpanel);
		findpanel.setLayout(null);
		JLabel label_5 = new JLabel("账 号");
		JLabel label_6 = new JLabel("新的密码");
		JLabel label_7 = new JLabel("注册时的邮箱");
		label_5.setBounds(14,28,100,18);
		label_6.setBounds(14,62,100,18);
		label_7.setBounds(14,94,100,18);
		findpanel.add(label_5);
		findpanel.add(label_6);
		findpanel.add(label_7);
		userID_3=new JTextField();
		passwd_3=new JPasswordField();
		email_address=new JTextField();
		userID_3.setBounds(125,25,150,25);
		passwd_3.setBounds(125,60,150,25);
		email_address.setBounds(125,95,150,25);
		findpanel.add(userID_3);
		findpanel.add(passwd_3);
		findpanel.add(email_address);
		userID_3.setColumns(10);
		passwd_3.setColumns(10);
		email_address.setColumns(10);
		JButton btn_3=new JButton("设 置 新 的 密 码");
		btn_3.setBounds(135,130,120,20);
		btn_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChatVerify user=new ChatVerify(userID_3.getText().trim(),String.valueOf(passwd_3.getPassword()).trim());
				boolean isSuccess=InteractWithServer.ForgetPwd(user.getUserId(),user.getUserPassword(),email_address.getText().trim());
				if(!isSuccess){
					JOptionPane.showMessageDialog(thisJFrame, "对不起, 你的邮箱错误!");
				} else{
					JOptionPane.showMessageDialog(thisJFrame, "你的密码已设置成功!");
				}
				
			}
		});
		findpanel.add(btn_3);
		//找回密码到此结束

    	//找回密码
    	JButton btnsignfind = new JButton("找 回 密 码");

		btnsignfind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Login.this.getHeight()==DIALOG_HEIGHT_EXTEND){
					Login.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
				}
				else{
					panel.setVisible(false);
					findpanel.setVisible(true);
					Login.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
					//contentPanel.setViewportView(findpanel);
					
				}
			}
		});
		btnsignfind.setBounds(260,280,93,23);
		contentPanel.add(btnsignfind);

    	JButton btnsignup = new JButton("注 册");
    	btnsignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Login.this.getHeight()==DIALOG_HEIGHT_EXTEND){
                    Login.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
                }
                else{
                    panel.setVisible(true);
                    findpanel.setVisible(false);
                	Login.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
					//contentPanel.setViewportView(panel);
                }
            }
        });
    	btnsignup.setBounds(40,280,93,23);
    	contentPanel.add(btnsignup);
    	
    	JButton btnsignin = new JButton("登 录");
    	btnsignin.setBounds(150,280,93,23);
    	contentPanel.add(btnsignin);
    	
    	autoLoginCheckBox = new JCheckBox();
    	autoLoginCheckBox.setMargin(new Insets(0, 0, 0, 0));
    	autoLoginCheckBox.setBounds(130,255,20,17);
    	autoLoginCheckBox.setIcon(new ImageIcon("./res/Loginpanel2/checkbox_normal.png"));
		autoLoginCheckBox.setRolloverIcon(new ImageIcon("./res/Loginpanel2/checkbox_hover.png"));
		autoLoginCheckBox.setPressedIcon(new ImageIcon("./res/Loginpanel2/checkbox_press.png"));
		autoLoginCheckBox.setSelectedIcon(new ImageIcon("./res/loginui/checkbox_tick_normal1.png"));
		autoLoginCheckBox.setRolloverSelectedIcon(new ImageIcon("./res/loginui/checkbox_tick_highlight1.png"));
    	
		JLabel autoJLabel = new JLabel("自动登录");
		autoJLabel.setForeground(Color.GRAY);
		autoJLabel.setBounds(150,253,50,20);
		contentPanel.add(autoJLabel);
		contentPanel.add(autoLoginCheckBox);
		
		remeberPasswdCheckBox = new JCheckBox();
		remeberPasswdCheckBox.setMargin(new Insets(0, 0, 0, 0));
		remeberPasswdCheckBox.setBounds(220, 255, 20, 17);
		remeberPasswdCheckBox.setIcon(new ImageIcon("./res/Loginpanel2/checkbox_normal.png"));
		remeberPasswdCheckBox.setRolloverIcon(new ImageIcon("./res/Loginpanel2/checkbox_hover.png"));
		remeberPasswdCheckBox.setPressedIcon(new ImageIcon("./res/Loginpanel2/checkbox_press.png"));
		remeberPasswdCheckBox.setSelectedIcon(new ImageIcon("./res/loginui/checkbox_tick_normal1.png"));
		remeberPasswdCheckBox.setRolloverSelectedIcon(new ImageIcon("./res/loginui/checkbox_tick_highlight1.png"));
		
		JLabel remJLabel = new JLabel("记住密码");
		remJLabel.setForeground(Color.GRAY);
		remJLabel.setBounds(240,253,50,20);
		contentPanel.add(remJLabel);
		contentPanel.add(remeberPasswdCheckBox);
    	/** 用于登录的账号密码
    	 * 
    	 */
    	userID_1 = new JTextField();
    	passwd_1 = new JPasswordField();
    	
        userID_1.setBounds(145, 180,150,25);
        passwd_1.setBounds(145,222,150,25);
        
        contentPanel.add(userID_1);
        contentPanel.add(passwd_1);
        
        userID_1.setColumns(10);
        passwd_1.setColumns(10);
        
        /** login 
         * 
         */
        l = new LoginListener();
        l.setNow(this);
        l.setUserId(userID_1);
        l.setPasswd(passwd_1);
        l.setIsRemeberPasswd(remeberPasswdCheckBox);
		l.setIsAutoLogin(autoLoginCheckBox);
        userID_1.addActionListener(l);
        passwd_1.addActionListener(l);
        btnsignin.addActionListener(l);
        
        try {
			FileInputStream in = new FileInputStream("./Data/UserInfo.uif");
			int t;
			username = "";
			userPasswd = "";
			while ((t = in.read()) != -1) {
				if (t == '\n')
					break;
				t ^= 'I';
				username = username + (char) t;
			}
			if (!username.equals("")) {
				while ((t = in.read()) != -1) {
					if (t == '\n')
						break;
					t ^= 'P';
					userPasswd = userPasswd + (char) t;
				}
				userID_1.setForeground(Color.BLACK);
				userID_1.setText(username);
				passwd_1.setEchoChar('•');
				passwd_1.setForeground(Color.BLACK);
				passwd_1.setText(userPasswd);
				t = (char) in.read();
				remeberPasswdCheckBox.setSelected(true);
				if (t == '1') {
					autoLoginCheckBox.setSelected(true);
				}
			}
			in.close();
		} catch (Exception e) {
		}
        
        
        JLabel label_l = new JLabel("账 号");
        JLabel label_2 = new JLabel("密 码");
        
        label_l.setBounds(95, 185, 54, 15);
        label_2.setBounds(95, 225, 54, 15);
        
        contentPanel.add(label_l);
        contentPanel.add(label_2);
        
        /** 图片
         * 
         */
        JLabel lblNewLabel_2 = new JLabel("New label");     
        lblNewLabel_2.setBounds(0, 25, 400, 136);
        ImageIcon icon=new ImageIcon("src/back_img.jpeg");
        icon=getImage.getImage(icon, lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight());
        lblNewLabel_2.setIcon((icon));
        contentPanel.add(lblNewLabel_2);

		//找回密码到此结束

        LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		
		setVisible(true);
    	
    }
}
