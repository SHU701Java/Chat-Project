package com.chat.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.chat.config.UserInfo;
import com.chat.config.UserInfo.FriendsOrGroups;
import com.chat.server.ChatServer;
import com.chat.user.User;

/**
 * @ClassName: DataCheck
 * @Description: 针对数据库的一些查询与更新函数，并对每种操作提供相应的SQL语句
 */
public final class DataCheck {
	/**
	 * @Title: isLoginSuccess
	 * @Description: 利用客户端发送来的用户名与MD5加密后的密码查询该用户是否存在于数据库
	 */
	public Boolean isLoginSuccess(String userId, String userPassword) {
		Boolean isSuccess = false;
		try {
			// 查询该用户是否存在
			DataBaseConnection dataCon = new DataBaseConnection();
			String sql = "select * from dw_user where user_id = " + userId + " and user_password = '" + userPassword
					+ "'";

			// 如果存在该用户，返回true
			isSuccess = dataCon.getFromDatabase(sql).next();

			// 关闭与服务器连接对象
			dataCon.close();
		} catch (SQLException e) {
			System.out.println("身份验证信息查询失败:" + e.getMessage());
		}
		return isSuccess;
	}

	/**
	 * @Title: getMemberFromId
	 * @Description: 通过ID与SQL获取最终结果集
	 */
	private static Vector<String> getMemberFromId(String sql, String row) {
		// 与数据库创建连接
		DataBaseConnection dataCon = new DataBaseConnection();

		// 最终结果Vector数组
		Vector<String> member = new Vector<String>();

		// 利用该sql语句查询，返回ResultSet结果集
		ResultSet resultSet = dataCon.getFromDatabase(sql);
		try {
			while (resultSet.next()) {
				member.add(resultSet.getString(row));
			}
			// 关闭连接
			dataCon.close();
		} catch (SQLException e) {
			System.out.println("查询成员ID列表失败：" + e.getMessage());
		}
		return member;
	}

	/**
	 * @Title: getFriendMember
	 * @Description: 查询用户的所有好友ID
	 */
	public static Vector<String> getFriendMember(String myselfId) {
		String sqlString = "select myfriend from dw_useruser where myself = " + myselfId;
		return getMemberFromId(sqlString, "myfriend");
	}

	/**
	 * @Title: getGroupMember
	 * @Description: 查询群中所有成员的ID
	 */
	public static Vector<String> getGroupMember(String groupId) {
		String sqlString = "select user_id from dw_usergroup where group_id = " + groupId;
		return getMemberFromId(sqlString, "user_id");
	}

	/**
	 * @Title: getUserFriends
	 * @Description: 获取用户好友信息
	 */
	public Vector<FriendsOrGroups> getUserFriends(String userId, DataBaseConnection dataCon) {
		Vector<FriendsOrGroups> friends = new Vector<FriendsOrGroups>();

		// 查询好友信息
		String sqlString = "select * from view_useruser where myself = " + userId;
		ResultSet resultSet = dataCon.getFromDatabase(sqlString);
		try {
			while (resultSet.next()) {
				String fId = resultSet.getString("myfriend");
				String fName = resultSet.getString("user_name");
				String fAvatar = resultSet.getString("user_avatar");
				String fTrade = resultSet.getString("user_trades");
				String fStatus = ChatServer.getClientUser().containsKey(fId) ? "在线" : "离线";
				friends.add(new FriendsOrGroups(fId, fName, fAvatar, fTrade, fStatus));
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("获取好友信息失败 " + e.getMessage());
		}
		return friends;
	}

	/**
	 * @Title: getUserGroups
	 * @Description: 获取用户群信息
	 */
	public Vector<FriendsOrGroups> getUserGroups(String userId, DataBaseConnection dataCon) {
		Vector<FriendsOrGroups> groups = new Vector<FriendsOrGroups>();

		// 查询群信息
		String sqlString = "select * from view_usergroup where user_id = " + userId;
		ResultSet resultSet = dataCon.getFromDatabase(sqlString);
		try {
			while (resultSet.next()) {
				String gId = resultSet.getString("group_id");
				String gName = resultSet.getString("group_name");
				String gTrades = resultSet.getString("group_trades");
				String gAvatar = resultSet.getString("group_avatar");
				String gStatus = resultSet.getString("user_id");
				groups.add(new FriendsOrGroups(gId, gName, gAvatar, gTrades, gStatus));
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("DataCheck 获取好友群失败 " + e.getMessage());
		}
		return groups;
	}

	/**
	 * @Title: getUserInfo
	 * @Description: 获取用户信息(包括个人资料，群列表及资料，好友列表及资料)
	 */
	public UserInfo getUserInfo(String userId) {
		// 用户个人信息
		String userName = "";
		String userEmail = "";
		String userSex = "";
		String userBirthday = "";
		String userAvatar = "";
		String userTrades = "";
		String userRegistertime = "";
		Vector<FriendsOrGroups> friends;
		Vector<FriendsOrGroups> groups;
		UserInfo userInfo = null;

		try {
			// 创建数据库连接
			DataBaseConnection dataCon = new DataBaseConnection();

			// 查询个人信息
			String sqlString = "select * from dw_user where user_id = " + userId;
			ResultSet resultSet = dataCon.getFromDatabase(sqlString);
			while (resultSet.next()) {
				userName = resultSet.getString("user_name");
				userEmail = resultSet.getString("user_email");
				userSex = resultSet.getString("user_sex");
				userBirthday = resultSet.getString("user_birthday");
				userAvatar = resultSet.getString("user_avatar");
				userTrades = resultSet.getString("user_trades");
				userRegistertime = resultSet.getString("user_registertime");
			}
			resultSet.close();

			// 查询好友列表信息与群列表信息
			friends = getUserFriends(userId, dataCon);
			groups = getUserGroups(userId, dataCon);

			// 关闭数据库连接
			dataCon.close();
			// 创建对象
			userInfo = new User(userId, userName, userEmail, userSex, userBirthday, userAvatar, userTrades,
					userRegistertime, friends, groups);
		} catch (SQLException e) {
			System.out.println("获取用户信息失败：" + e.getMessage());
		}
		return userInfo;
	}

	/**
	 * @Title: getChatRecord
	 * @Description: 获取聊天记录
	 */
	public Vector<String> getChatRecord(String fromId, String toId, String isGroup) {
		Vector<String> all = new Vector<String>();

		// 创建数据库连接对象
		DataBaseConnection dataCon = new DataBaseConnection();
		String sqlString = "";
		if (isGroup.equals("true"))
			sqlString = "select gchat_uid fromid,gchat_gid toid,gchat_message message,gchat_datetime timer from dw_groupchat where gchat_gid = "
					+ toId;
		else
			sqlString = "select uchat_fromid fromid,uchat_toid toid,uchat_message message,uchat_datetime timer from dw_userchat where (uchat_fromid ="
					+ fromId + " and uchat_toid = " + toId + ") or (uchat_fromid = " + toId + " and uchat_toid = "
					+ fromId + ") order by uchat_id";
		ResultSet resultSet = dataCon.getFromDatabase(sqlString);
		try {
			String tmp = "";
			while (resultSet.next()) {
				tmp = resultSet.getString("timer") + "```";
				tmp += resultSet.getString("fromid") + "```";
				tmp += resultSet.getString("toid") + "```";
				tmp += resultSet.getString("message");
				all.add(tmp);
			}

			// 关闭连接
			dataCon.close();
		} catch (SQLException e) {
			System.out.println("获取聊天记录信息失败：" + e.getMessage());
		}
		return all;
	}

	public String getNewUserID() {
		DataBaseConnection con = new DataBaseConnection();
		ResultSet resultSet = con.getFromDatabase("select user_id from dw_user order by user_id DESC limit 1");
		String res = "";
		try {
			while (resultSet.next())
				res = resultSet.getString("user_id");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("log: the largest userID is " + res);
		int nxt = Integer.parseInt(res);
		return String.valueOf(nxt + 1);
	}
	
	public String getNewGroupID() {
		DataBaseConnection con = new DataBaseConnection();
		ResultSet resultSet = con.getFromDatabase("select group_id from dw_group order by group_id DESC limit 1");
		String res = "NULL";
		try {
			if (resultSet.next())
				res = resultSet.getString("group_id");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("log: the largest group_id is " + res);
		int nxt = 9999;
		if (!res.equals("NULL"))	
			nxt = Integer.parseInt(res);
		return String.valueOf(nxt + 1);
	}

	public void addFriend(String fromID, String toID, boolean twoWay) {
		boolean has1 = false, has2 = false;
		try {
			DataBaseConnection con = new DataBaseConnection();
			String sql1 = "select * from dw_useruser where myself = " + fromID + " and myfriend = " + toID;
			String sql2 = "select * from dw_useruser where myfriend = " + toID + " and myself = " + fromID;
			has1 = con.getFromDatabase(sql1).next();
			has2 = con.getFromDatabase(sql2).next();

			if (!has1)
				con.putToDatabase("insert into dw_useruser values(\"" + fromID + "\", \"" + toID + "\")");
			if (twoWay && !has2)
				con.putToDatabase("insert into dw_useruser values(\"" + toID + "\", \"" + fromID + "\")");
			con.close();
		} catch (SQLException e) {
			System.out.println("身份验证信息查询失败:" + e.getMessage());
		}
	}

	public void delFriend(String fromID, String toID, boolean twoWay) {
		DataBaseConnection con = new DataBaseConnection();
		con.putToDatabase("delete dw_useruser where myself = " + fromID + " and myfriend = " + toID);
		if (twoWay)
			con.putToDatabase("delete dw_useruser where myself = " + toID + " and myfriend = " + fromID);
		con.close();
	}

	public int friendRequest(String fromID, String toID) {
		int status = 0;
		boolean isSuccess = false;
		try {
			DataBaseConnection con = new DataBaseConnection();
			String sql = "select * from dw_user where user_id = " + toID;
			isSuccess = con.getFromDatabase(sql).next();
			if (isSuccess) {
				sql = "select * from dw_useruser where myself = " + fromID + " and myfriend =" + toID;
				isSuccess = con.getFromDatabase(sql).next();
				if (isSuccess) {// 已经是好友
					status = 1;
				} else {// 成功添加
					con.putToDatabase("insert into dw_useruser values(\"" + fromID + "\", \"" + toID + "\")");
					status = 2;
				}
			} else {
				// 目标不存在
				status = 0;
			}
		} catch (SQLException e) {
			System.out.println("身份验证信息查询失败: " + e.getMessage());
		}
		return status;
	}

	public int groupRequest(String fromID, String toID) {
		int status = 0;
		boolean isSuccess = false;
		try {
			DataBaseConnection con = new DataBaseConnection();
			String sql = "select * from dw_group where group_id = " + toID;
			isSuccess = con.getFromDatabase(sql).next();
			if (isSuccess) {
				sql = "select * from dw_usergroup where user_id = " + fromID + " and group_id =" + toID;
				isSuccess = con.getFromDatabase(sql).next();
				if (isSuccess) {// 已经是好友
					status = 1;
				} else {// 成功添加
					con.putToDatabase("insert into dw_usergroup values(\"" + fromID + "\", \"" + toID + "\")");
					status = 2;
				}
			} else {
				// 目标不存在
				status = 0;
			}
		} catch (SQLException e) {
			System.out.println("身份验证信息查询失败: " + e.getMessage());
		}
		return status;
	}
}