package com.chat.config;

import java.io.Serializable;
import java.util.Vector;

/**
 * @ClassName: UserInfo
 * @Description: 主要是为服务端与客户端之间传输用户个人信息以及好友列表、群列表而创建的可序列化类(实现了 java.io.Serializable 接口)
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 4146085358128616967L;

	protected String userId;
	protected String userName;
	protected String userEmail;
	protected String userSex;
	protected String userBirthday;
	protected String userAvatar;
	protected String userTrades;
	protected String userRegistertime;
	protected Vector<FriendsOrGroups> friends = new Vector<FriendsOrGroups>();
	protected Vector<FriendsOrGroups> groups = new Vector<FriendsOrGroups>();

	/**
	 * @ClassName: FriendsOrGroups
	 * @Description:对群或者一个好友所记录的信息是一个FriendsOrGroups对象，对象中包含它的id,name,avatar,trades,status.
	 */
	public static class FriendsOrGroups implements Serializable {
		/**  */
		private static final long serialVersionUID = -1855195980029629286L;

		private String id;
		private String name;
		private String avatar;
		private String trades;
		private String status;

		/**
		 * @Title: FriendsOrGroups
		 * @Description: 创建一个群信息对象或好友信息对象
		 * @param: id
		 * @param: name
		 * @param: avatar
		 * @param: trades
		 * @param: status
		 */
		public FriendsOrGroups(String id, String name, String avatar, String trades, String status) {
			this.id = id;
			this.name = name;
			this.avatar = avatar;
			this.trades = trades;
			this.status = status;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getAvatar() {
			return avatar;
		}

		public String getTrades() {
			return trades;
		}

		public String getStatus() {
			return status;
		}
	}
}
