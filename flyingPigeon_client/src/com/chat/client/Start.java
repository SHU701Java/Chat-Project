/**
 * @ClassName: Start
 * @Description: 客户端入口,登陆界面入口
 */

package com.chat.client;

import com.chat.frame.Login;

public class Start {
	public static void main(String[] args) {
		try{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		new Login();
	}
}
