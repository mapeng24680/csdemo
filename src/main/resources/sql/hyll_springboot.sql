/*
Navicat MySQL Data Transfer

Source Server         : 172.16.60.12
Source Server Version : 50614
Source Host           : 172.16.60.12:3306
Source Database       : hyll_springboot

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2019-03-08 17:57:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_change
-- ----------------------------
DROP TABLE IF EXISTS `account_change`;
CREATE TABLE `account_change` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `account` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '当前账户金额',
  `enterprise_name` varchar(32) NOT NULL DEFAULT '' COMMENT '企业名称',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '收入佣金',
  `order_msg` varchar(255) NOT NULL DEFAULT '' COMMENT '订单详情',
  `order_num` varchar(16) NOT NULL DEFAULT '' COMMENT '订单编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户流水表';

-- ----------------------------
-- Records of account_change
-- ----------------------------

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `contract_num` varchar(64) NOT NULL DEFAULT '' COMMENT '合同编号',
  `contract_msg` varchar(255) NOT NULL DEFAULT '' COMMENT '合同内容',
  `order_id` int(10) NOT NULL COMMENT '签约订单id',
  `order_num` varchar(32) NOT NULL DEFAULT '' COMMENT '签约订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `enterprise_id` int(11) NOT NULL COMMENT '企业id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '签约状态：0-正常 ，1-过期',
  `create_time` datetime NOT NULL COMMENT '签约时间',
  `overdue_time` datetime NOT NULL COMMENT '过期时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='合同表';

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('1', '1', '1', '5', '1', '1', '1', '0', '2019-03-08 15:35:41', '2019-03-25 15:35:44', '2019-03-18 15:35:47');

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `is_load` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('1', 'testCode', '测试参数', 'testType', 'testValue', '0');
INSERT INTO `dict` VALUES ('33', 'fileTempPath', '上传的临时文件的保存位置', 'fileTempPath', 'E://test//', '0');
INSERT INTO `dict` VALUES ('34', 'SYSTEM_USER', '系统用户', 'USER_TYPE', '1', '1');
INSERT INTO `dict` VALUES ('35', 'MOBILE_USER', '移动端用户', 'USER_TYPE', '2', '1');
INSERT INTO `dict` VALUES ('37', 'OK', '允许', 'DICT_IS_LOAD', '1', '1');
INSERT INTO `dict` VALUES ('38', 'NOT', '不允许', 'DICT_IS_LOAD', '0', '1');
INSERT INTO `dict` VALUES ('39', 'USE', '启用', 'USER_STATE', '1', '1');
INSERT INTO `dict` VALUES ('40', 'UNUSE', '禁用', 'USER_STATE', '0', '1');

-- ----------------------------
-- Table structure for enterprise_published
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_published`;
CREATE TABLE `enterprise_published` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '企业(用户)id',
  `need_msg` text NOT NULL COMMENT '需求信息',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态0：可用 ，1：已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='企业公布信息';

-- ----------------------------
-- Records of enterprise_published
-- ----------------------------
INSERT INTO `enterprise_published` VALUES ('1', '2', '公司需要人才', '0', '2019-03-08 11:49:02', '2019-03-08 11:49:05');

-- ----------------------------
-- Table structure for evaluation
-- ----------------------------
DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `evaluation_user` varchar(625) NOT NULL DEFAULT '' COMMENT '会计评价',
  `evaluation_sender` varchar(625) NOT NULL DEFAULT '' COMMENT '企业(发单)评价',
  `order_id` int(10) NOT NULL COMMENT '订单id',
  `sender_id` int(10) NOT NULL COMMENT '发单企业(也是用户表）id',
  `user_id` int(10) NOT NULL COMMENT '接单用户id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '评价状态0：正常 1：删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='评价表';

-- ----------------------------
-- Records of evaluation
-- ----------------------------
INSERT INTO `evaluation` VALUES ('1', '很差的企业', '很差的人', '1', '2', '1', '0', '2019-03-07 09:53:11', '2019-03-07 09:53:14');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sender_id` int(10) NOT NULL COMMENT '发单用户id',
  `user_id` int(10) DEFAULT NULL COMMENT '接单用户id',
  `order_num` varchar(16) NOT NULL DEFAULT '' COMMENT '订单id编号',
  `order_msg` varchar(225) NOT NULL DEFAULT '' COMMENT '订单备注信息',
  `commission` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '佣金',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '订单状态：1-等待接单 ，2-已接单 ，3-已完成',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '2', '2', '111', '111', '0.00', '2', '2019-03-06 14:38:10', '2019-03-07 17:40:09');
INSERT INTO `order` VALUES ('3', '2', '2', '196646655', '222', '1111.00', '2', '2019-03-07 19:36:19', '2019-03-08 09:29:14');
INSERT INTO `order` VALUES ('4', '2', '2', '146193328', '测试', '100.00', '2', '2019-03-08 09:10:27', '2019-03-08 09:29:22');
INSERT INTO `order` VALUES ('5', '2', '2', '97031191', '测试', '100.00', '2', '2019-03-08 09:10:38', '2019-03-08 09:29:35');
INSERT INTO `order` VALUES ('6', '2', null, '57121963', '122', '23.00', '1', '2019-03-08 09:12:15', '2019-03-08 09:12:15');
INSERT INTO `order` VALUES ('7', '2', null, '122998275', 'sss', '12.00', '1', '2019-03-08 09:14:30', '2019-03-08 09:14:30');
INSERT INTO `order` VALUES ('8', '2', null, '125861395', 'asassa', '12.00', '1', '2019-03-08 09:18:38', '2019-03-08 09:18:38');
INSERT INTO `order` VALUES ('9', '2', null, '106425203', 'ss', '11.00', '1', '2019-03-08 09:22:30', '2019-03-08 09:22:30');
INSERT INTO `order` VALUES ('10', '2', null, '10004641', '11', '11.00', '1', '2019-03-08 09:22:49', '2019-03-08 09:22:49');
INSERT INTO `order` VALUES ('11', '2', null, '21585016', 'wqe', '33.00', '1', '2019-03-08 09:25:37', '2019-03-08 09:25:37');
INSERT INTO `order` VALUES ('12', '2', null, '62245396', 'fdf', '12.00', '1', '2019-03-08 09:26:45', '2019-03-08 09:26:45');

-- ----------------------------
-- Table structure for org_group
-- ----------------------------
DROP TABLE IF EXISTS `org_group`;
CREATE TABLE `org_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `existing_num` bigint(20) DEFAULT NULL,
  `group_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `node` varchar(255) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  `parent_node` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_group
-- ----------------------------
INSERT INTO `org_group` VALUES ('3', null, 'YYB', '运营部', '001002', '20', '001');
INSERT INTO `org_group` VALUES ('9', '0', '0022', '全嘉福', '001', '222', '0');
INSERT INTO `org_group` VALUES ('10', '0', 'CYB', '餐饮部', '001001', '10', '001');
INSERT INTO `org_group` VALUES ('19', '0', 'HQB', '后勤部', '001003', '5', '001');

-- ----------------------------
-- Table structure for role_associate_tree
-- ----------------------------
DROP TABLE IF EXISTS `role_associate_tree`;
CREATE TABLE `role_associate_tree` (
  `role_id` bigint(20) NOT NULL,
  `tree_id` bigint(20) NOT NULL,
  KEY `FKdslec8iii7ggslupebobmpu4` (`tree_id`),
  KEY `FKgkuemjgtln0vlvcokl9pn38f7` (`role_id`),
  CONSTRAINT `FKdslec8iii7ggslupebobmpu4` FOREIGN KEY (`tree_id`) REFERENCES `tree` (`id`),
  CONSTRAINT `FKgkuemjgtln0vlvcokl9pn38f7` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_associate_tree
-- ----------------------------
INSERT INTO `role_associate_tree` VALUES ('1', '1');
INSERT INTO `role_associate_tree` VALUES ('1', '2');
INSERT INTO `role_associate_tree` VALUES ('1', '7');
INSERT INTO `role_associate_tree` VALUES ('1', '8');
INSERT INTO `role_associate_tree` VALUES ('1', '9');
INSERT INTO `role_associate_tree` VALUES ('1', '10');
INSERT INTO `role_associate_tree` VALUES ('1', '17');
INSERT INTO `role_associate_tree` VALUES ('1', '23');
INSERT INTO `role_associate_tree` VALUES ('1', '27');
INSERT INTO `role_associate_tree` VALUES ('1', '28');
INSERT INTO `role_associate_tree` VALUES ('1', '29');
INSERT INTO `role_associate_tree` VALUES ('3', '17');
INSERT INTO `role_associate_tree` VALUES ('3', '23');
INSERT INTO `role_associate_tree` VALUES ('1', '31');
INSERT INTO `role_associate_tree` VALUES ('5', '31');
INSERT INTO `role_associate_tree` VALUES ('5', '17');
INSERT INTO `role_associate_tree` VALUES ('5', '23');

-- ----------------------------
-- Table structure for tree
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  `tree_order` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES ('1', '#', 'fa fa-fw fa-cogs', '系统管理', '0', '1', '#', '1');
INSERT INTO `tree` VALUES ('2', null, 'fa fa-fw fa-tree', '菜单管理', '1', '1', 'treeList', '1');
INSERT INTO `tree` VALUES ('7', 'groupManager', 'fa fa-fw fa-group', '组织架构', '1', '2', 'groupList', '1');
INSERT INTO `tree` VALUES ('8', 'userRoleManager', 'fa fa-fw fa-user-secret', '角色管理', '1', '3', 'userRoleList', '1');
INSERT INTO `tree` VALUES ('9', 'userManager', 'fa fa-fw fa-user', '用户维护', '1', '4', 'userList', '1');
INSERT INTO `tree` VALUES ('10', 'dictManager', 'fa fa-fw fa-book', '字典维护', '1', '5', 'dictList', '1');
INSERT INTO `tree` VALUES ('17', 'sysManager', 'fa fa-fw fa-desktop', '订单维护', '0', '2', '#', '1');
INSERT INTO `tree` VALUES ('23', 'foodTypeManager', 'fa fa-fw fa-tree', '订单信息', '17', '1', 'foodTypeList', '1');
INSERT INTO `tree` VALUES ('27', 'userManagerTop', 'fa fa-user', '用户维护', '0', '3', '#', '1');
INSERT INTO `tree` VALUES ('28', 'mobileUserManager', 'fa fa-user-circle', '客户信息查询', '27', '1', 'mobileUser', '1');
INSERT INTO `tree` VALUES ('29', 'scoreDetailManager', 'fa fa-user-circle', '用户信息修改', '27', '2', 'scoreDetailList', '1');
INSERT INTO `tree` VALUES ('31', 'EnterpriseInfoManager', 'fa fa-fw fa-book', '企业信息发布', '17', '3', 'EnterpriseInfo', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgcws90nffc311tac0vg2xy6cw` (`group_id`),
  CONSTRAINT `FKgcws90nffc311tac0vg2xy6cw` FOREIGN KEY (`group_id`) REFERENCES `org_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hyll', '140b6ce18716153fba3bf98a52722bd5', 'linzf', '福建省福州市晋安区xxxx超大大厦', '餐饮小兵', '10', null, '福州市', '晋安区', '福建省', 'xxxx超大大厦', '1', '1', '2017-12-07 11:17:19');
INSERT INTO `user` VALUES ('2', 'hyll144', '140b6ce18716153fba3bf98a52722bd5', '福建xxxxx', '北京市北京市市辖区东城区阿道夫的辐射大', '餐饮小兵', '10', null, '北京市市辖区', '东城区', '北京市', '阿道夫的辐射大', '1', '1', '2017-12-01 17:00:56');
INSERT INTO `user` VALUES ('9', 'linzhefeng23', '140b6ce18716153fba3bf98a52722bd5', 'xxxx', 'xxxxx188号', '餐饮主管', '10', null, '福州市', '晋安区', '福建省', 'xxxxxx', '1', '1', null);

-- ----------------------------
-- Table structure for user_associate_role
-- ----------------------------
DROP TABLE IF EXISTS `user_associate_role`;
CREATE TABLE `user_associate_role` (
  `user_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK4kspd00l9tn3oi6swv1cjf7vh` (`role_id`),
  KEY `FKl7kmw86eevxmoxwxu55poq7bm` (`user_id`),
  CONSTRAINT `FK4kspd00l9tn3oi6swv1cjf7vh` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKl7kmw86eevxmoxwxu55poq7bm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_associate_role
-- ----------------------------
INSERT INTO `user_associate_role` VALUES ('2', '5');
INSERT INTO `user_associate_role` VALUES ('1', '1');
INSERT INTO `user_associate_role` VALUES ('9', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', 'ROLE_ADMIN', '系统管理员');
INSERT INTO `user_role` VALUES ('2', 'ROLE_USER', '普通用户');
INSERT INTO `user_role` VALUES ('3', 'ROLE_DINER', '会计');
INSERT INTO `user_role` VALUES ('5', 'ROLE_DEPT', '企业用户');
