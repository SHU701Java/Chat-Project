package com.chat.server;

/**
 * @ClassName: Start
 * @Description: 服务端启动入口,创建验证消息线程
 */
public class Start {
	public static void main(String[] args) {
		new Thread(new VerifyThread()).start();
		System.out.println("服务端已成功创建,当前在线人数：0");
	}
}
