package com.chat.server;

import java.io.IOException;
import java.net.Socket;

import com.chat.config.ServerInfo;

/**
 * @ClassName: ChatThread
 * @Description: 服务器聊天端口的监听，等待用户连接
 */
public final class ChatThread implements Runnable {

	private String userId;

	private Socket userSocket;

	private static ChatServer chatServerInfo;

	static {
		chatServerInfo = new ChatServer(ServerInfo.CHAT_PORT);
	}

	public ChatThread(String userId) {
		this.userId = userId;
	}

	/**
	 * @Description:等待客户端连接，成功连接之后为其创建一个聊天数据流对象
	 */
	@Override
	public void run() {
		// 先与客户端建立聊天端口的连接
		try {
			userSocket = chatServerInfo.getServerSocket().accept();
		} catch (IOException e) {
			System.out.println("未能与客户端成功建立连接：" + e.getMessage());
			return;
		}

		// 成功接入之后建立数据流
		DataStream dataStream = new DataStream(userSocket, userId);

		// 加入到在线人员映射里面
		ChatServer.getClientUser().put(userId, dataStream);

		System.out.println("用户 " + userId + " 已成功登录 ,Info: " + userSocket.getInetAddress());
		System.out.println("当前在线人数： " + ChatServer.getClientUser().size());

		dataStream.run();
	}
}
