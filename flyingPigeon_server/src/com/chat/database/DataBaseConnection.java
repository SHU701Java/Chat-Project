package com.chat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chat.config.ServerInfo;

import java.sql.PreparedStatement;

/**
 * @ClassName: DataBaseConnection
 * @Description: 创建数据库连接，支持通过sql查询数据库内容或更新
 */
public final class DataBaseConnection {
	/**
	 * @Fields conn : 数据库连接对象
	 */
	private Connection conn = null;

	private PreparedStatement psql = null;

	/**
	 * @Fields resultSet : 最终结果集
	 */
	private ResultSet resultSet = null;

	/**
	 * @Description: 与数据库Mysql建立联系，设置默认连接编码为UTF-8
	 */
	public DataBaseConnection() {
		// 数据库驱动名
		String dbDriver = "com.mysql.cj.jdbc.Driver";

		// 数据库所在域
		String dbUrl = "jdbc:mysql://" + ServerInfo.MYSQL_IP + ":" + ServerInfo.MYSQL_PORT + "/" + ServerInfo.DB_NAME
				+ "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";

		try {
			// 加载驱动
			Class.forName(dbDriver);

			// 获取连接对象
			conn = DriverManager.getConnection(dbUrl, ServerInfo.DB_USER_NAME, ServerInfo.DB_USER_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("无法连接到数据库：" + e.getMessage());
		}
	}

	/**
	 * @Title: getFromDatabase
	 * @Description: 利用当前的数据连接查询，通过一条SQL语句
	 */
	public ResultSet getFromDatabase(String sql) {
		try {
			// 创建对象参数化 sql
			psql = conn.prepareStatement(sql);

			// 开始查询
			resultSet = psql.executeQuery();
		} catch (SQLException e) {
			System.out.println("数据库查询失败：" + e.getMessage());
		}
		return resultSet;
	}

	/**
	 * @Title: putToDatabase
	 * @Description: 通过SQL语句更新数据库
	 */
	public void putToDatabase(String sql) {
		try {
			psql = conn.prepareStatement(sql);
			psql.executeUpdate();
		} catch (SQLException e) {
			System.out.println("更新数据库异常：" + e.getMessage());
		}
	}

	/**
	 * @Title: close
	 * @Description: 关闭所有打开的连接
	 */
	public void close() {
		try {
			if (resultSet != null && !resultSet.isClosed())
				resultSet.close();
		} catch (SQLException e) {
			System.out.println("结果集关闭异常：" + e.getMessage());
		}
		try {
			if (psql != null && !psql.isClosed())
				psql.close();
		} catch (SQLException e) {
			System.out.println("更新集关闭异常：" + e.getMessage());
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("数据库连接关闭异常：" + e.getMessage());
		}
	}
	public static void main(String[] args) {
		new DataBaseConnection();
	}
}
