/**
 * @ClassName: Start
 * @Description:聊天端建立，首先启动登录窗口，填入登录等信息之后会与服务端验证端口建立
 * 				连接，发送一个序列化对象ChatVerify到服务端，返回处理结果之后判断如果
 * 				成功登录则进入主界面，并接入服务器聊天端口，发送获取个人信息资料等请求，
 * 				服务端返回结果之后将其更改到主界面，好友列表里面每一个用户面板都对应该
 * 				好友/群的ID信息(这样可以在发送信息的时候判断是发送给谁的)，在聊天界面中，
 * 				首先向服务器请求聊天记录信息，并展示原有的聊天记录，发送新消息时会首先对
 * 				这个消息内容进行预处理(添加标识发送给谁与是谁发送的)，然后通过数据输出流
 * 				发出。
 * 
 * 				在更改个性签名中，也是通过连接的验证端口发送出请求，后续步骤交给服务器处理。
 * 				
 * 				在客户端实现中，用到了多线程处理机制，一方面保持主界面的流畅(不会因为线程
 * 				处理而卡顿)，另一方便也提高了聊天时与服务端的通讯效率。
 */

package com.oicq.client;

import com.oicq.frame.Login;

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
