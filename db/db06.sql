/*
 Navicat Premium Data Transfer

 Source Server         : 172.17.64.196_MySQL
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : db06

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 01/12/2021 21:58:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `vendor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
  `remain_count` int(0) NULL DEFAULT NULL COMMENT '剩余库存',
  `total_count` int(0) NULL DEFAULT NULL COMMENT '总库存',
  `unit_price` double(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `borrow_price` double(10, 2) NULL DEFAULT NULL COMMENT '借阅一天的价格',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '上架标记,\'0\'已上架,‘1’已下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '高等数学', '诸葛亮', '北京出版社', 44, 44, 30.00, 0.10, b'0');
INSERT INTO `book` VALUES (2, '线性代数', '诸葛亮', '北京出版社', 24, 24, 30.00, 0.10, b'0');
INSERT INTO `book` VALUES (3, '概率论', '诸葛亮', '北京出版社', 5, 5, 30.00, 0.10, b'0');
INSERT INTO `book` VALUES (4, '大学英语', '刘备', '上海出版社', 5, 5, 25.00, 0.10, b'0');
INSERT INTO `book` VALUES (5, '计算机导论', '刘备', '上海出版社', 5, 5, 30.00, 0.10, b'0');
INSERT INTO `book` VALUES (6, 'C语言程序设计', '张飞', '上海出版社', 10, 10, 35.00, 0.10, b'0');
INSERT INTO `book` VALUES (7, '数据结构', '张飞', '上海出版社', 10, 10, 50.00, 0.20, b'0');
INSERT INTO `book` VALUES (8, 'C++程序设计', '关羽', '广西出版社', 5, 5, 40.00, 0.20, b'0');
INSERT INTO `book` VALUES (9, 'C#程序设计', '赵云', '江西出版社', 5, 5, 40.00, 0.20, b'0');
INSERT INTO `book` VALUES (10, 'Java实战', '黄忠', '江西出版社', 10, 10, 40.00, 0.20, b'0');
INSERT INTO `book` VALUES (11, 'Java核心技术卷', '司马懿', '上海出版社', 15, 15, 70.00, 0.30, b'0');
INSERT INTO `book` VALUES (12, '网络通信', '黄忠', '广西出版社', 10, 10, 50.00, 0.20, b'0');
INSERT INTO `book` VALUES (13, '网络安全', '关羽', '广西出版社', 15, 15, 60.00, 0.20, b'0');
INSERT INTO `book` VALUES (14, '编译原理', '曹操', '广东出版社', 10, 10, 60.00, 0.20, b'0');
INSERT INTO `book` VALUES (15, '操作系统', '曹操', '广东出版社', 15, 15, 65.00, 0.20, b'0');
INSERT INTO `book` VALUES (16, 'Linux操作系统', '刘禅', '重庆出版社', 20, 20, 40.00, 0.20, b'0');
INSERT INTO `book` VALUES (17, 'VB程序设计', '刘禅', '重庆出版社', 15, 15, 20.00, 0.10, b'0');
INSERT INTO `book` VALUES (33, 'GoLang实战教程', '司马懿', '广西出版社', 20, 20, 65.00, 0.30, b'0');
INSERT INTO `book` VALUES (34, '计算机组成原理', '司马懿', '广东出版社', 10, 10, 55.00, 0.20, b'0');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `login_id` int(0) NULL DEFAULT NULL COMMENT '借阅者ID',
  `book_id` int(0) NULL DEFAULT NULL COMMENT '图书ID',
  `begin_date` datetime(0) NULL DEFAULT NULL COMMENT '借阅日期',
  `promise_day` int(0) NULL DEFAULT NULL COMMENT '约定借阅天数',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '实际归还日期',
  `cost` double(10, 2) NULL DEFAULT NULL COMMENT '费用',
  `return_status` int(0) NULL DEFAULT NULL COMMENT '归还状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (12, 2, 1, '2021-11-14 05:53:42', 1, '2021-11-14 09:20:49', 0.10, 2);
INSERT INTO `borrow` VALUES (13, 3, 1, '2021-11-14 05:56:11', 10, '2021-11-19 11:18:38', 30.60, 4);
INSERT INTO `borrow` VALUES (14, 3, 2, '2021-11-16 05:56:28', 10, '2021-11-19 11:20:41', 30.40, 4);
INSERT INTO `borrow` VALUES (15, 2, 1, '2021-11-14 07:44:58', 1, '2021-11-14 09:29:21', 0.10, 2);
INSERT INTO `borrow` VALUES (16, 2, 1, '2021-11-14 07:45:52', 1, '2021-11-14 09:29:43', 0.10, 2);
INSERT INTO `borrow` VALUES (17, 2, 1, '2021-11-14 09:32:09', 2, '2021-11-14 10:26:33', 0.10, 2);
INSERT INTO `borrow` VALUES (18, 2, 1, '2021-11-14 10:41:50', 6, '2021-11-14 10:42:41', 0.10, 2);
INSERT INTO `borrow` VALUES (19, 2, 1, '2021-11-14 10:42:56', 5, '2021-11-14 10:43:14', 0.10, 2);
INSERT INTO `borrow` VALUES (20, 2, 1, '2021-11-14 10:44:06', 1, '2021-11-16 10:49:13', 0.50, 3);
INSERT INTO `borrow` VALUES (21, 2, 1, '2021-11-14 10:50:14', 1, '2021-11-16 10:52:09', 0.50, 3);
INSERT INTO `borrow` VALUES (22, 2, 1, '2021-11-16 10:53:54', 1, '2021-11-18 10:54:54', 0.50, 3);
INSERT INTO `borrow` VALUES (23, 2, 1, '2021-11-18 10:55:13', 1, '2021-11-19 10:56:26', 0.30, 3);

-- ----------------------------
-- Table structure for factory_book
-- ----------------------------
DROP TABLE IF EXISTS `factory_book`;
CREATE TABLE `factory_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `unit_price` double(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `factory_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工厂图书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of factory_book
-- ----------------------------
INSERT INTO `factory_book` VALUES (1, '高等数学', '诸葛亮', 20.00, '北京出版社');
INSERT INTO `factory_book` VALUES (2, '线性代数', '诸葛亮', 20.00, '北京出版社');
INSERT INTO `factory_book` VALUES (3, '概率论', '诸葛亮', 20.00, '北京出版社');
INSERT INTO `factory_book` VALUES (4, '大学英语', '刘备', 15.00, '上海出版社');
INSERT INTO `factory_book` VALUES (5, '计算机导论', '刘备', 20.00, '上海出版社');
INSERT INTO `factory_book` VALUES (6, 'C语言程序设计', '张飞', 25.00, '上海出版社');
INSERT INTO `factory_book` VALUES (7, '数据结构', '张飞', 30.00, '上海出版社');
INSERT INTO `factory_book` VALUES (8, 'C++程序设计', '关羽', 20.00, '广西出版社');
INSERT INTO `factory_book` VALUES (9, 'C#程序设计', '赵云', 20.00, '江西出版社');
INSERT INTO `factory_book` VALUES (10, 'Java实战', '黄忠', 20.00, '江西出版社');
INSERT INTO `factory_book` VALUES (11, 'Java核心技术卷', '司马懿', 40.00, '上海出版社');
INSERT INTO `factory_book` VALUES (12, '网络通信', '黄忠', 30.00, '广西出版社');
INSERT INTO `factory_book` VALUES (13, '网络安全', '关羽', 30.00, '广西出版社');
INSERT INTO `factory_book` VALUES (14, '编译原理', '曹操', 30.00, '广东出版社');
INSERT INTO `factory_book` VALUES (15, '操作系统', '曹操', 35.00, '广东出版社');
INSERT INTO `factory_book` VALUES (16, 'Linux操作系统', '刘禅', 20.00, '重庆出版社');
INSERT INTO `factory_book` VALUES (17, 'VB程序设计', '刘禅', 10.00, '重庆出版社');
INSERT INTO `factory_book` VALUES (18, '计算机组成原理', '司马懿', 40.00, '广东出版社');
INSERT INTO `factory_book` VALUES (19, 'GoLang实战教程', '司马懿', 40.00, '广西出版社');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `available` bit(1) NULL DEFAULT NULL COMMENT '账号能否使用，‘1’能用，‘0’不能使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES (1, 'shuchao', '123456', 1, b'1');
INSERT INTO `login` VALUES (2, 'wanger', '123456', 2, b'1');
INSERT INTO `login` VALUES (3, 'zhangsan', '123456', 3, b'1');
INSERT INTO `login` VALUES (4, 'liujunjun', '123456', 4, b'1');
INSERT INTO `login` VALUES (5, 'lisi', '123456', 5, b'1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `menu_type` int(0) NULL DEFAULT NULL COMMENT '菜单类型,\'1\'是目录,‘2’是子菜单,\'3\'是按钮',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标样式',
  `menu_sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '首页', 1, '', '<i class=\"icon-home\"></i>', 1100);
INSERT INTO `menu` VALUES (2, 0, '借阅管理', 1, NULL, '<i class=\"icon-list-alt\"></i>', 1200);
INSERT INTO `menu` VALUES (3, 0, '账号管理', 1, NULL, '<i class=\"icon-cog\"></i>', 2700);
INSERT INTO `menu` VALUES (4, 0, '用户管理', 1, NULL, '<i class=\"icon-user\"></i>', 2300);
INSERT INTO `menu` VALUES (5, 0, '外购管理', 1, NULL, '<i class=\"icon-shopping-cart\"></i>', 2400);
INSERT INTO `menu` VALUES (6, 0, '报表管理', 1, NULL, '<i class=\"icon-bar-chart\"></i>', 2600);
INSERT INTO `menu` VALUES (7, 1, '查阅图书', 2, 'book/showBookList?delFlag=false', NULL, 1110);
INSERT INTO `menu` VALUES (8, 1, '已下架图书', 2, 'book/showBookList?delFlag=true', NULL, 1120);
INSERT INTO `menu` VALUES (9, 2, '借阅信息', 2, 'borrow/showBorrowRecordList', NULL, 1210);
INSERT INTO `menu` VALUES (12, 3, '个人资料', 2, 'user/userInfoShow', NULL, 2710);
INSERT INTO `menu` VALUES (13, 3, '修改密码', 2, 'user/editPasswordShow', NULL, 2720);
INSERT INTO `menu` VALUES (14, 3, '余额充值', 2, 'user/rechargeBalanceShow', NULL, 2730);
INSERT INTO `menu` VALUES (15, 4, '用户信息', 2, 'user/userInfoListShow', NULL, 2310);
INSERT INTO `menu` VALUES (16, 5, '工厂图书', 2, 'factoryBook/showFactoryBookList', NULL, 2410);
INSERT INTO `menu` VALUES (18, 6, '报表记录', 2, 'report/showReportList', NULL, 2620);
INSERT INTO `menu` VALUES (19, 7, '借阅', 3, 'book_borrow(this)', '<i class=\"icon-book\"></i>', 1111);
INSERT INTO `menu` VALUES (20, 7, '编辑', 3, 'book_edit(this)', '<i class=\"icon-pencil\"></i>', 1112);
INSERT INTO `menu` VALUES (21, 7, '下架', 3, 'book_down(this)', '<i class=\"icon-angle-down\"></i>', 1113);
INSERT INTO `menu` VALUES (22, 8, '上架', 3, 'book_up(this)', '<i class=\"icon-angle-up\"></i>', 1114);
INSERT INTO `menu` VALUES (100, 16, '购买', 3, 'purchase_factoryBook(this)', '<i class=\"icon-credit-card\"></i>', 2411);
INSERT INTO `menu` VALUES (101, 0, '流水管理', 1, '', '<i class=\"icon-tint\"></i>', 2500);
INSERT INTO `menu` VALUES (102, 101, '交易流水', 2, 'tradeFlow/showTradeFlowRecordList', NULL, 2510);
INSERT INTO `menu` VALUES (103, 5, '外购记录', 2, 'purchase/showPurchaseRecordList', NULL, 2420);
INSERT INTO `menu` VALUES (104, 9, '归还', 3, 'book_return(this)', '<i class=\"icon-mail-reply\"></i>', 1211);
INSERT INTO `menu` VALUES (105, 9, '延期', 3, 'book_postpone(this)', '<i class=\"icon-calendar\"></i>', 1212);
INSERT INTO `menu` VALUES (106, 9, '报损', 3, 'book_damage(this)', '<i class=\"icon-trash\"></i>', 1213);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `login_id` int(0) NULL DEFAULT NULL COMMENT '购买者ID',
  `book_id` int(0) NULL DEFAULT NULL COMMENT '图书ID',
  `count` int(0) NULL DEFAULT NULL COMMENT '购买数量',
  `unit_price` double(10, 2) NULL DEFAULT NULL COMMENT '购买单价',
  `purchase_date` datetime(0) NULL DEFAULT NULL COMMENT '购买日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '外购记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (1, 1, 33, 15, 40.00, '2021-11-07 11:34:34');
INSERT INTO `purchase` VALUES (2, 1, 33, 5, 40.00, '2021-11-09 11:36:27');
INSERT INTO `purchase` VALUES (3, 4, 34, 10, 40.00, '2021-11-09 14:18:20');
INSERT INTO `purchase` VALUES (4, 1, 1, 5, 20.00, '2021-11-13 13:08:18');
INSERT INTO `purchase` VALUES (5, 1, 1, 5, 20.00, '2021-11-13 13:13:09');
INSERT INTO `purchase` VALUES (6, 1, 1, 5, 20.00, '2021-11-13 13:14:06');
INSERT INTO `purchase` VALUES (7, 1, 1, 5, 20.00, '2021-11-13 13:14:51');
INSERT INTO `purchase` VALUES (8, 1, 2, 5, 20.00, '2021-11-13 13:18:11');
INSERT INTO `purchase` VALUES (9, 1, 2, 5, 20.00, '2021-11-13 13:21:17');
INSERT INTO `purchase` VALUES (10, 1, 1, 10, 20.00, '2021-11-13 17:03:44');
INSERT INTO `purchase` VALUES (11, 1, 1, 10, 20.00, '2021-11-13 17:05:12');
INSERT INTO `purchase` VALUES (12, 1, 2, 10, 20.00, '2021-11-13 17:09:04');
INSERT INTO `purchase` VALUES (13, 1, 35, 10, 10.00, '2021-11-13 17:13:39');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `report_date` date NULL DEFAULT NULL COMMENT '报表日期',
  `month_out` double(10, 2) NULL DEFAULT NULL COMMENT '月支出',
  `month_get` double(10, 2) NULL DEFAULT NULL COMMENT '月收入',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '月度报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, '2021-10-01', 100.00, 150.00);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员');
INSERT INTO `role` VALUES (2, '借阅者');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 1);
INSERT INTO `role_menu` VALUES (2, 1, 2);
INSERT INTO `role_menu` VALUES (3, 1, 3);
INSERT INTO `role_menu` VALUES (4, 1, 4);
INSERT INTO `role_menu` VALUES (5, 1, 5);
INSERT INTO `role_menu` VALUES (6, 1, 6);
INSERT INTO `role_menu` VALUES (7, 2, 1);
INSERT INTO `role_menu` VALUES (8, 2, 2);
INSERT INTO `role_menu` VALUES (9, 2, 3);
INSERT INTO `role_menu` VALUES (10, 1, 7);
INSERT INTO `role_menu` VALUES (11, 1, 8);
INSERT INTO `role_menu` VALUES (12, 1, 9);
INSERT INTO `role_menu` VALUES (15, 1, 12);
INSERT INTO `role_menu` VALUES (16, 1, 13);
INSERT INTO `role_menu` VALUES (17, 1, 15);
INSERT INTO `role_menu` VALUES (18, 1, 16);
INSERT INTO `role_menu` VALUES (20, 1, 18);
INSERT INTO `role_menu` VALUES (21, 2, 7);
INSERT INTO `role_menu` VALUES (22, 2, 9);
INSERT INTO `role_menu` VALUES (25, 2, 12);
INSERT INTO `role_menu` VALUES (26, 2, 13);
INSERT INTO `role_menu` VALUES (27, 2, 14);
INSERT INTO `role_menu` VALUES (28, 1, 20);
INSERT INTO `role_menu` VALUES (29, 1, 21);
INSERT INTO `role_menu` VALUES (30, 1, 22);
INSERT INTO `role_menu` VALUES (31, 2, 19);
INSERT INTO `role_menu` VALUES (100, 1, 100);
INSERT INTO `role_menu` VALUES (101, 1, 101);
INSERT INTO `role_menu` VALUES (102, 1, 102);
INSERT INTO `role_menu` VALUES (103, 1, 103);
INSERT INTO `role_menu` VALUES (104, 2, 104);
INSERT INTO `role_menu` VALUES (105, 2, 105);
INSERT INTO `role_menu` VALUES (106, 2, 106);

-- ----------------------------
-- Table structure for trade_flow
-- ----------------------------
DROP TABLE IF EXISTS `trade_flow`;
CREATE TABLE `trade_flow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `payer` int(0) NULL DEFAULT NULL COMMENT '付款方',
  `payee` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方',
  `trade_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易类型',
  `trade_amount` double(10, 2) NULL DEFAULT NULL COMMENT '交易金额',
  `trade_date` datetime(0) NULL DEFAULT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交易流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trade_flow
-- ----------------------------
INSERT INTO `trade_flow` VALUES (1, 1, '广西出版社', '图书外购', 600.00, '2021-10-07 11:34:41');
INSERT INTO `trade_flow` VALUES (2, 1, '广西出版社', '图书外购', 200.00, '2021-10-09 11:36:49');
INSERT INTO `trade_flow` VALUES (3, 4, '广东出版社', '图书外购', 400.00, '2021-11-09 14:18:20');
INSERT INTO `trade_flow` VALUES (4, 1, '北京出版社', '图书外购', 100.00, '2021-11-13 13:08:18');
INSERT INTO `trade_flow` VALUES (5, 1, '北京出版社', '图书外购', 100.00, '2021-11-13 13:13:09');
INSERT INTO `trade_flow` VALUES (6, 1, '北京出版社', '图书外购', 100.00, '2021-11-13 13:14:06');
INSERT INTO `trade_flow` VALUES (7, 1, '北京出版社', '图书外购', 100.00, '2021-11-13 13:14:51');
INSERT INTO `trade_flow` VALUES (8, 1, '北京出版社', '图书外购', 100.00, '2021-11-13 13:18:11');
INSERT INTO `trade_flow` VALUES (9, 1, '北京出版社', '图书外购', 100.00, '2021-11-13 13:21:17');
INSERT INTO `trade_flow` VALUES (10, 1, '北京出版社', '图书外购', 200.00, '2021-11-13 17:03:44');
INSERT INTO `trade_flow` VALUES (11, 1, '北京出版社', '图书外购', 200.00, '2021-11-13 17:05:12');
INSERT INTO `trade_flow` VALUES (12, 1, '北京出版社', '图书外购', 200.00, '2021-11-13 17:09:04');
INSERT INTO `trade_flow` VALUES (13, 1, '重庆出版社', '图书外购', 100.00, '2021-11-13 17:13:39');
INSERT INTO `trade_flow` VALUES (14, 2, '图书馆', '图书借阅', 0.10, '2021-11-14 09:20:49');
INSERT INTO `trade_flow` VALUES (15, 2, '图书馆', '图书借阅', 0.10, '2021-11-14 09:29:21');
INSERT INTO `trade_flow` VALUES (16, 2, '图书馆', '图书借阅', 0.10, '2021-11-14 09:29:43');
INSERT INTO `trade_flow` VALUES (17, 2, '图书馆', '图书借阅', 0.10, '2021-11-14 10:26:33');
INSERT INTO `trade_flow` VALUES (18, 2, '图书馆', '图书借阅', 0.10, '2021-11-14 10:42:41');
INSERT INTO `trade_flow` VALUES (19, 2, '图书馆', '图书借阅', 0.10, '2021-11-14 10:43:14');
INSERT INTO `trade_flow` VALUES (20, 2, '图书馆', '图书借阅', 0.50, '2021-11-16 10:49:13');
INSERT INTO `trade_flow` VALUES (21, 2, '图书馆', '图书借阅', 0.50, '2021-11-16 10:52:09');
INSERT INTO `trade_flow` VALUES (22, 2, '图书馆', '图书借阅', 0.50, '2021-11-18 10:54:54');
INSERT INTO `trade_flow` VALUES (23, 2, '图书馆', '图书借阅', 0.30, '2021-11-19 10:56:26');
INSERT INTO `trade_flow` VALUES (24, 3, '图书馆', '图书报损', 30.60, '2021-11-19 11:18:38');
INSERT INTO `trade_flow` VALUES (25, 3, '图书馆', '图书报损', 30.40, '2021-11-19 11:20:41');
INSERT INTO `trade_flow` VALUES (26, 2, '图书馆', '用户充值', 1.00, '2021-11-26 12:39:36');
INSERT INTO `trade_flow` VALUES (27, 2, '图书馆', '用户充值', 2.00, '2021-11-26 12:40:14');
INSERT INTO `trade_flow` VALUES (28, 2, '图书馆', '用户充值', 4.00, '2021-11-26 12:42:32');
INSERT INTO `trade_flow` VALUES (29, 2, '图书馆', '用户充值', 8.00, '2021-11-26 12:42:56');
INSERT INTO `trade_flow` VALUES (30, 2, '图书馆', '用户充值', 4.00, '2021-11-26 12:46:10');
INSERT INTO `trade_flow` VALUES (31, 2, '图书馆', '用户充值', 4.00, '2021-11-26 12:46:17');
INSERT INTO `trade_flow` VALUES (32, 2, '图书馆', '用户充值', 6.00, '2021-11-26 12:49:04');
INSERT INTO `trade_flow` VALUES (33, 2, '图书馆', '用户充值', 5.00, '2021-11-26 12:50:13');
INSERT INTO `trade_flow` VALUES (34, 2, '图书馆', '用户充值', 5.00, '2021-11-26 12:50:18');
INSERT INTO `trade_flow` VALUES (35, 2, '图书馆', '用户充值', 1.00, '2021-11-26 12:54:22');
INSERT INTO `trade_flow` VALUES (36, 10, '图书馆', '用户充值', 1.00, '2021-11-28 09:06:28');
INSERT INTO `trade_flow` VALUES (37, 10, '图书馆', '用户充值', 2.00, '2021-11-28 09:06:33');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `balance` double(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '舒超', '13811112222', NULL, 1);
INSERT INTO `user` VALUES (2, '王二', '13822223333', 41.00, 2);
INSERT INTO `user` VALUES (3, '张三', '13833334444', 69.60, 2);
INSERT INTO `user` VALUES (4, '刘俊君', '13844445555', NULL, 1);
INSERT INTO `user` VALUES (5, '李四', '13855556666', 1.00, 2);

SET FOREIGN_KEY_CHECKS = 1;
