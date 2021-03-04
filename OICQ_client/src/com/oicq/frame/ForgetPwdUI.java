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
 * @ClassName:  ForgetPwdUI
 * @Description: 注册界面 unused
 */

public class ForgetPwdUI extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JFrame thisFrame;
    JPanel upPanel, all;
    JTextField userID, email;
    JPasswordField pwd;
    JButton SetPwd, minimize, close;

    final String str1 = "账号", str2 = "注册时的邮箱", str3 = "新的密码";

    public ForgetPwdUI() {
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

        userID = new JTextField(str1);
        userID.setBounds(80, 50, 300, 50);
        userID.setForeground(Color.GRAY);
        userID.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (userID.getText().trim().equals("")) {
                    userID.setForeground(Color.GRAY);
                    userID.setText(str1);
                }
            }

            @Override
            public void focusGained(FocusEvent arg0) {
                if (userID.getText().trim().equals(str1)) {
                    userID.setText("");
                    userID.setForeground(Color.BLACK);
                }
            }
        });

        email = new JTextField(str2);
        email.setBounds(80, 250, 300, 50);
        email.setForeground(Color.GRAY);
        email.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().trim().equals("")) {
                    email.setForeground(Color.GRAY);
                    email.setText(str2);
                }
            }

            @Override
            public void focusGained(FocusEvent arg0) {
                if (email.getText().trim().equals(str2)) {
                    email.setText("");
                    email.setForeground(Color.BLACK);
                }
            }
        });

        pwd = new JPasswordField(str3);
        pwd.setBounds(80, 150, 300, 50);
        pwd.setEchoChar((char) 0);
        pwd.setForeground(Color.GRAY);
        pwd.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(pwd.getPassword()).trim().equals("")) {
                    pwd.setEchoChar((char) 0);
                    pwd.setForeground(Color.GRAY);
                    pwd.setText(str3);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(pwd.getPassword()).trim().equals(str3)) {
                    pwd.setEchoChar('•');
                    pwd.setForeground(Color.BLACK);
                    pwd.setText("");
                }
            }
        });

        SetPwd = new JButton("设 置 密 码");
        SetPwd.setBounds(50, 350, 350, 50);
        thisFrame = this;
        SetPwd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                ChatVerify user = new ChatVerify(userID.getText().trim(), String.valueOf(pwd.getPassword()).trim());
                boolean isSuccess = InteractWithServer.ForgetPwd(user.getUserId(), user.getUserPassword(), email.getText().trim());
                if (!isSuccess)
                    JOptionPane.showMessageDialog(thisFrame, "对不起, 你的邮箱错误!");
                else
                    JOptionPane.showMessageDialog(thisFrame, "你的密码已设置成功!");
            }
        });

        all.add(userID);
        all.add(pwd);
        all.add(email);
        all.add(SetPwd);

        add(all);

        LoginMousemove adapter = new LoginMousemove();
        addMouseMotionListener(adapter);
        addMouseListener(adapter);
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Already there
        // setExtendedState(JFrame.MAXIMIZED_BOTH); //set Jframe size？
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
