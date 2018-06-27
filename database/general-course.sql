/*
Navicat MySQL Data Transfer

Source Server         : zxh
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : general-course

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-06-27 22:39:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `columnId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `whetherTop` smallint(2) DEFAULT '0',
  `status` smallint(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for colunm
-- ----------------------------
DROP TABLE IF EXISTS `colunm`;
CREATE TABLE `colunm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `level` smallint(2) DEFAULT '0',
  `parentId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of colunm
-- ----------------------------
INSERT INTO `colunm` VALUES ('100000', '首页', '0', '0');
INSERT INTO `colunm` VALUES ('100001', '课程介绍', '0', '0');
INSERT INTO `colunm` VALUES ('100002', '教学团队', '0', '0');
INSERT INTO `colunm` VALUES ('100003', '课程建设', '0', '0');
INSERT INTO `colunm` VALUES ('100004', '课程资源', '0', '0');
INSERT INTO `colunm` VALUES ('100005', '问题与答疑', '0', '0');
INSERT INTO `colunm` VALUES ('100006', '课程研究', '0', '0');
INSERT INTO `colunm` VALUES ('100007', '课程简介', '1', '100001');
INSERT INTO `colunm` VALUES ('100008', '教学方法', '1', '100001');
INSERT INTO `colunm` VALUES ('100009', '课程动态', '1', '100001');
INSERT INTO `colunm` VALUES ('100010', '教学课件', '1', '100004');
INSERT INTO `colunm` VALUES ('100011', '教学视频', '1', '100004');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `sex` smallint(2) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('100000', 'admin', '0', '100000', '123456', '100000');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) DEFAULT NULL,
  `path` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `replyTime` datetime DEFAULT NULL,
  `status` smallint(2) DEFAULT NULL,
  `moduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `status` smallint(2) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('100000', '网站内容管理', '1');
INSERT INTO `module` VALUES ('100001', '系统设置', '1');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `moduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('100000', '100000', '100000');
INSERT INTO `permission` VALUES ('100001', '100000', '100001');
INSERT INTO `permission` VALUES ('100002', '100000', '100002');
INSERT INTO `permission` VALUES ('100003', '100000', '100003');
INSERT INTO `permission` VALUES ('100004', '100000', '100004');
INSERT INTO `permission` VALUES ('100005', '100000', '100005');
INSERT INTO `permission` VALUES ('100006', '100000', '100006');
INSERT INTO `permission` VALUES ('100007', '100000', '100007');
INSERT INTO `permission` VALUES ('100008', '100000', '100008');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('100000', '超级管理员', '2018-06-27 22:23:06', '所有权限都有的人，相当于Linux下的root用户');

-- ----------------------------
-- Table structure for submodule
-- ----------------------------
DROP TABLE IF EXISTS `submodule`;
CREATE TABLE `submodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `status` smallint(2) DEFAULT '1',
  `parentModuleId` int(11) DEFAULT '100000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of submodule
-- ----------------------------
INSERT INTO `submodule` VALUES ('100000', '留言管理', '1', '100000');
INSERT INTO `submodule` VALUES ('100001', '文章管理', '1', '100000');
INSERT INTO `submodule` VALUES ('100002', '审核管理', '1', '100000');
INSERT INTO `submodule` VALUES ('100003', '栏目管理', '1', '100000');
INSERT INTO `submodule` VALUES ('100004', '模块管理', '1', '100001');
INSERT INTO `submodule` VALUES ('100005', '角色分配', '1', '100001');
INSERT INTO `submodule` VALUES ('100006', '人员管理', '1', '100001');
INSERT INTO `submodule` VALUES ('100007', '角色管理', '1', '100001');
INSERT INTO `submodule` VALUES ('100008', '首页信息管理', '1', '100001');
