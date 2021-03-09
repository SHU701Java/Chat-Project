package com.chat.config;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: ChatVerify
 * @Description: 用作登录验证所要发送的对象，实现了 java.io.Serializable 接口
 */

public final class ChatVerify implements Serializable {

	private static final long serialVersionUID = -4490443980607193791L;

	private String userId;
	private String userPassword;

	/**
	 * @Title: ChatVerify
	 * @Description: 创建一个存储用户ID、密码经过MD5加密后的对象
	 */
	public ChatVerify(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword = getMd5(userPassword);
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @Title: getMd5
	 * @Description: 对一个字符串进行MD5加密并返回加密后的串
	 */
	public String getMd5(String str) {
		String mdPassword = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 计算md5函数
			md.update(str.getBytes());

			// 保留16位
			mdPassword = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5加密失败：" + e.getMessage());
		}
		return mdPassword;
	}
}