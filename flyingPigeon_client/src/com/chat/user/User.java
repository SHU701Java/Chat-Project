package com.chat.user;

import java.util.Vector;

import com.chat.config.UserInfo;

/**
 * @ClassName: User
 * @Description: 用户信息对象
 */
public final class User extends UserInfo {

	private static final long serialVersionUID = -2844611810327524136L;
	
	/**
	 * @Title: User
	 * @Description: 初始化用户信息内容
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @param userSex
	 * @param userBirthday
	 * @param userAvatar
	 * @param userTrades
	 * @param userRegistertime
	 * @param friends
	 * @param groups
	 */
	public User(String userId, String userName, String userEmail, String userSex, String userBirthday,
			String userAvatar, String userTrades, String userRegistertime, Vector<FriendsOrGroups> friends,
			Vector<FriendsOrGroups> groups) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userSex = userSex;
		this.userBirthday = userBirthday;
		this.userAvatar = userAvatar;
		this.userTrades = userTrades;
		this.userRegistertime = userRegistertime;
		this.friends = friends;
		this.groups = groups;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserSex() {
		return userSex;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public String getUserTrades() {
		return userTrades;
	}

	public String getUserRegistertime() {
		return userRegistertime;
	}

	public Vector<FriendsOrGroups> getFriends() {
		return friends;
	}

	public Vector<FriendsOrGroups> getGroups() {
		return groups;
	}
}
