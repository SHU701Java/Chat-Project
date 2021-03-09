package com.chat.config;

/**
 * @ClassName: ServerInfo
 * @Description: 记录一些与服务器有关的常量信息
 */
public final class ServerInfo {
	/**
	 * @Fields SERVER_IP : 服务器地址
	 */
	public final static String SERVER_IP = "127.0.0.1";

	/**
	 * @Fields CHAT_PORT : 聊天端口监听
	 */
	public final static int CHAT_PORT = 6666;

	/**
	 * @Fields VERIFY_PORT : 验证端口监听
	 */
	public final static int VERIFY_PORT = 7777;

	/**
	 * @Fields MYSQL_IP : Mysql 服务器地址
	 */
	public final static String MYSQL_IP = "127.0.0.1";

	/**
	 * @Fields MYSQL_PORT : Mysql 端口号
	 */
	public final static int MYSQL_PORT = 3306;

	/**
	 * @Fields DB_NAME : Mysql 数据库名称
	 */
	public final static String DB_NAME = "chat_db";

	/**
	 * @Fields DB_USER_NAME : Mysql 数据库连接用户名
	 */
	public final static String DB_USER_NAME = "root";

	/**
	 * @Fields DB_USER_PASSWORD : Mysql 数据库连接密码
	 */
	public final static String DB_USER_PASSWORD = "";
}
