CREATE DATABASE  IF NOT EXISTS `chat_db`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chat_db`
--

-- --------------------------------------------------------

--
-- 表的结构 `dw_group`
--

CREATE TABLE `dw_group` (
  `group_id` int(10) NOT NULL,
  `group_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `group_master` int(10) NOT NULL,
  `group_trades` text COLLATE utf8_unicode_ci NOT NULL,
  `group_registertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `group_avatar` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dw_group`
--

INSERT INTO `dw_group` (`group_id`, `group_name`, `group_master`, `group_trades`, `group_registertime`, `group_avatar`) VALUES
(10001, '701', 10001, '这是第一个测试群，都说了是第一个啦~', '2021-02-20 09:14:41', 'http://tva1.sinaimg.cn/crop.0.0.480.480.180/ab72caa0jw8ewkyk6s3ijj20dc0dcabd.jpg'),
(10002, 'java交流群', 10002, '我的个性签名去哪里了', '2021-02-20 09:14:49', 'http://tva1.sinaimg.cn/crop.0.0.512.512.50/0069Brjhjw8f9us1mbv2kj30e80e8gm1.jpg');

-- --------------------------------------------------------

--
-- 表的结构 `dw_groupchat`
--

CREATE TABLE `dw_groupchat` (
  `gchat_id` int(10) NOT NULL,
  `gchat_uid` int(10) NOT NULL,
  `gchat_gid` int(10) NOT NULL,
  `gchat_message` text COLLATE utf8_unicode_ci NOT NULL,
  `gchat_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dw_groupchat`
--

INSERT INTO `dw_groupchat` (`gchat_id`, `gchat_uid`, `gchat_gid`, `gchat_message`, `gchat_datetime`) VALUES
(1, 10003, 10001, '大家好', '2021-02-20 22:12:46');

-- --------------------------------------------------------

--
-- 表的结构 `dw_user`
--

CREATE TABLE `dw_user` (
  `user_id` int(10) NOT NULL,
  `user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_password` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `user_sex` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'M',
  `user_birthday` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `user_avatar` text COLLATE utf8_unicode_ci NOT NULL,
  `user_trades` text COLLATE utf8_unicode_ci NOT NULL,
  `user_registertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dw_user`
--

INSERT INTO `dw_user` (`user_id`, `user_name`, `user_password`, `user_email`, `user_sex`, `user_birthday`, `user_avatar`, `user_trades`, `user_registertime`) VALUES
(10001, 'lyy', 'e10adc3949ba59abbe56e057f20f883e', 'test@qq.com', 'M', '', 'http://static.dreamwings.cn/wp-content/uploads/2016/06/10102wq.jpg', 'im0lyy', '2021-02-21 12:25:12'),
(10002, 'lwj', 'e10adc3949ba59abbe56e057f20f883e', 'i@ireson.cn', 'M', '', 'http://static.dreamwings.cn/wp-content/uploads/2016/05/236657.jpg', '这是一个个性签名。This is a character signature.', '2021-02-21 12:25:12'),
(10003, 'zqf', 'e10adc3949ba59abbe56e057f20f883e', 'qian', 'M', '', 'http://static.dreamwings.cn/wp-content/uploads/2016/05/color.png', '哈哈哈哈', '2021-02-21 12:25:12'),
(10004, 'wgx', 'e10adc3949ba59abbe56e057f20f883e', '12223', 'M', '', 'http://static.dreamwings.cn/wp-content/uploads/2016/09/u150953173-3.jpg', '', '2021-02-21 12:25:12'),
(10005, 'syx', 'e10adc3949ba59abbe56e057f20f883e', 'CSD', 'M', '', 'http://static.dreamwings.cn/wp-content/uploads/2016/07/FAFAFA.jpg', '', '2021-02-21 12:25:12');


-- --------------------------------------------------------

--
-- 表的结构 `dw_userchat`
--

CREATE TABLE `dw_userchat` (
  `uchat_id` int(10) NOT NULL,
  `uchat_fromid` int(10) NOT NULL,
  `uchat_toid` int(10) NOT NULL,
  `uchat_message` text COLLATE utf8_unicode_ci NOT NULL,
  `uchat_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dw_userchat`
--

INSERT INTO `dw_userchat` (`uchat_id`, `uchat_fromid`, `uchat_toid`, `uchat_message`, `uchat_datetime`) VALUES
(1, 10002, 10001, 'aaaaa', '2021-02-26 17:08:54');

-- --------------------------------------------------------

--
-- 表的结构 `dw_usergroup`
--

CREATE TABLE `dw_usergroup` (
  `user_id` int(10) NOT NULL,
  `group_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dw_usergroup`
--

INSERT INTO `dw_usergroup` (`user_id`, `group_id`) VALUES
(10001, 10001),
(10001, 10002),
(10002, 10001),
(10002, 10002),
(10003, 10001);

-- --------------------------------------------------------

--
-- 表的结构 `dw_useruser`
--

CREATE TABLE `dw_useruser` (
  `myself` int(10) NOT NULL,
  `myfriend` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `dw_useruser`
--

INSERT INTO `dw_useruser` (`myself`, `myfriend`) VALUES
(10001, 10002),
(10001, 10003),
(10001, 10004),
(10001, 10005),
(10002, 10001),
(10002, 10003),
(10002, 10004),
(10003, 10001),
(10003, 10004),
(10003, 10005),
(10004, 10001),
(10004, 10005),
(10005, 10001);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_usergroup`
-- (See below for the actual view)
--
CREATE TABLE `view_usergroup` (
`group_id` int(10)
,`group_name` varchar(20)
,`group_avatar` text
,`group_trades` text
,`user_id` int(10)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_useruser`
-- (See below for the actual view)
--
CREATE TABLE `view_useruser` (
`myself` int(10)
,`myfriend` int(10)
,`user_name` varchar(20)
,`user_avatar` text
,`user_trades` text
);

-- --------------------------------------------------------

--
-- 视图结构 `view_usergroup`
--
DROP TABLE IF EXISTS `view_usergroup`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_usergroup`  AS  (select `dw_group`.`group_id` AS `group_id`,`dw_group`.`group_name` AS `group_name`,`dw_group`.`group_avatar` AS `group_avatar`,`dw_group`.`group_trades` AS `group_trades`,`dw_usergroup`.`user_id` AS `user_id` from (`dw_group` join `dw_usergroup`) where (`dw_group`.`group_id` = `dw_usergroup`.`group_id`)) ;

-- --------------------------------------------------------

--
-- 视图结构 `view_useruser`
--
DROP TABLE IF EXISTS `view_useruser`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_useruser`  AS  (select `dw_useruser`.`myself` AS `myself`,`dw_useruser`.`myfriend` AS `myfriend`,`dw_user`.`user_name` AS `user_name`,`dw_user`.`user_avatar` AS `user_avatar`,`dw_user`.`user_trades` AS `user_trades` from (`dw_user` join `dw_useruser`) where (`dw_user`.`user_id` = `dw_useruser`.`myfriend`) order by `dw_useruser`.`myself`) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dw_group`
--
ALTER TABLE `dw_group`
  ADD PRIMARY KEY (`group_id`),
  ADD KEY `group_master` (`group_master`);

--
-- Indexes for table `dw_groupchat`
--
ALTER TABLE `dw_groupchat`
  ADD PRIMARY KEY (`gchat_id`),
  ADD KEY `gchat_uid` (`gchat_uid`),
  ADD KEY `gchat_gid` (`gchat_gid`);

--
-- Indexes for table `dw_user`
--
ALTER TABLE `dw_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_email` (`user_email`);

--
-- Indexes for table `dw_userchat`
--
ALTER TABLE `dw_userchat`
  ADD PRIMARY KEY (`uchat_id`),
  ADD KEY `uchat_fromid` (`uchat_fromid`),
  ADD KEY `uchat_toid` (`uchat_toid`);

--
-- Indexes for table `dw_usergroup`
--
ALTER TABLE `dw_usergroup`
  ADD PRIMARY KEY (`user_id`,`group_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `group_id` (`group_id`);

--
-- Indexes for table `dw_useruser`
--
ALTER TABLE `dw_useruser`
  ADD PRIMARY KEY (`myself`,`myfriend`),
  ADD KEY `myself` (`myself`),
  ADD KEY `myfriend` (`myfriend`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `dw_group`
--
ALTER TABLE `dw_group`
  MODIFY `group_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10003;

--
-- 使用表AUTO_INCREMENT `dw_groupchat`
--
ALTER TABLE `dw_groupchat`
  MODIFY `gchat_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- 使用表AUTO_INCREMENT `dw_user`
--
ALTER TABLE `dw_user`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10020;

--
-- 使用表AUTO_INCREMENT `dw_userchat`
--
ALTER TABLE `dw_userchat`
  MODIFY `uchat_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- 限制导出的表
--

--
-- 限制表 `dw_group`
--
ALTER TABLE `dw_group`
  ADD CONSTRAINT `dw_group_ibfk_1` FOREIGN KEY (`group_master`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE;

--
-- 限制表 `dw_groupchat`
--
ALTER TABLE `dw_groupchat`
  ADD CONSTRAINT `dw_groupchat_ibfk_1` FOREIGN KEY (`gchat_uid`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dw_groupchat_ibfk_2` FOREIGN KEY (`gchat_gid`) REFERENCES `dw_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `dw_userchat`
--
ALTER TABLE `dw_userchat`
  ADD CONSTRAINT `dw_userchat_ibfk_1` FOREIGN KEY (`uchat_fromid`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dw_userchat_ibfk_2` FOREIGN KEY (`uchat_toid`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `dw_usergroup`
--
ALTER TABLE `dw_usergroup`
  ADD CONSTRAINT `dw_usergroup_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `dw_usergroup_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `dw_group` (`group_id`) ON DELETE CASCADE;

--
-- 限制表 `dw_useruser`
--
ALTER TABLE `dw_useruser`
  ADD CONSTRAINT `dw_useruser_ibfk_1` FOREIGN KEY (`myself`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dw_useruser_ibfk_2` FOREIGN KEY (`myfriend`) REFERENCES `dw_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
