/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-04-25 22:30:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bugs`
-- ----------------------------
DROP TABLE IF EXISTS `bugs`;
CREATE TABLE `bugs` (
  `id` int(11) NOT NULL,
  `priority` tinyint(4) NOT NULL,
  `class` varchar(255) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bugs
-- ----------------------------

-- ----------------------------
-- Table structure for `departments`
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `counts` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departments
-- ----------------------------

-- ----------------------------
-- Table structure for `tasks`
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` tinytext NOT NULL,
  `body` text NOT NULL,
  `creattime` varchar(100) NOT NULL,
  `author` varchar(255) NOT NULL,
  `departmentid` int(11) NOT NULL,
  `executor` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL COMMENT '分为3级，1为特急，2为尽快，3为不急',
  `executeTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tasks
-- ----------------------------
INSERT INTO `tasks` VALUES ('14', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('15', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('16', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('17', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('18', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('19', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('20', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '1', '2016');
INSERT INTO `tasks` VALUES ('21', '1', '123456', '456456456', '2016-03-07', 'Sinro', '1', 'Sinro,Aaron,Rita,', '2', null);
INSERT INTO `tasks` VALUES ('22', '1', '123456', '456456456', '2016-03-07', 'Sinro', '1', 'Sinro,Aaron,Rita,', '2', null);
INSERT INTO `tasks` VALUES ('23', '1', '123132456', '<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><h1>4564564564564</h1></blockquote>', '2016-03-07', 'Sinro', '1', 'Sinro,Aaron,', '3', '2016');
INSERT INTO `tasks` VALUES ('24', '1', '12314', '20161646464', '2016-03-07', 'Sinro', '1', 'Sinro,Aaron,', '2', '2016');
INSERT INTO `tasks` VALUES ('25', '1', '12213143', '13243564354', '2016-03-07', 'Sinro', '1', 'Sinro,Aaron,Rita,', '高', '201616');
INSERT INTO `tasks` VALUES ('26', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('27', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('28', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('29', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('30', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('31', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('32', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');
INSERT INTO `tasks` VALUES ('33', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', 'test', '高', '2016');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(20) NOT NULL,
  `department` varchar(255) NOT NULL,
  `departmentid` int(11) NOT NULL,
  `classN` int(4) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Sinro', 'sinro', '财务部', '1', '1', null);
INSERT INTO `user` VALUES ('2', 'Aaron', 'aaron', '财务部', '1', '1', null);
INSERT INTO `user` VALUES ('3', 'Lucian', 'lucian', '技术部', '2', '1', null);
INSERT INTO `user` VALUES ('4', 'Xingxing', 'xingxing', '技术部', '2', '2', null);
INSERT INTO `user` VALUES ('5', 'Rita', 'rita', '财务部', '1', '2', null);
INSERT INTO `user` VALUES ('37', 'levy', '123456', '技术部', '2', '2', '男');
INSERT INTO `user` VALUES ('39', 'test', 'test', 'test', '8080', '12', 'test');

-- ----------------------------
-- Table structure for `workplan`
-- ----------------------------
DROP TABLE IF EXISTS `workplan`;
CREATE TABLE `workplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` tinytext NOT NULL,
  `body` text NOT NULL,
  `creattime` varchar(100) NOT NULL,
  `author` varchar(255) NOT NULL,
  `departmentid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workplan
-- ----------------------------
INSERT INTO `workplan` VALUES ('7', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('8', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('9', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('10', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('11', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('12', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('13', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('14', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('15', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('16', '1', '12133', '3123213', '2016-03-07', 'Sinro', '1');
INSERT INTO `workplan` VALUES ('17', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('18', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('19', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('20', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('21', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('22', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('23', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');
INSERT INTO `workplan` VALUES ('24', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88');

-- ----------------------------
-- Table structure for `worksummary`
-- ----------------------------
DROP TABLE IF EXISTS `worksummary`;
CREATE TABLE `worksummary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` tinytext NOT NULL,
  `body` text NOT NULL,
  `creattime` varchar(100) NOT NULL,
  `author` varchar(255) NOT NULL,
  `departmentid` int(11) NOT NULL,
  `plan` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of worksummary
-- ----------------------------
INSERT INTO `worksummary` VALUES ('32', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('33', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('34', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('35', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('36', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('37', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('38', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('39', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('40', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('41', '1', '11313', '3131243', '2016-03-07', 'Sinro', '1', '');
INSERT INTO `worksummary` VALUES ('42', '1', '456456', '123131', '2016-03-07', 'Sinro', '1', '');
INSERT INTO `worksummary` VALUES ('43', '1', '456456', '123131', '2016-03-07', 'Sinro', '1', '');
INSERT INTO `worksummary` VALUES ('44', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('45', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('46', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('47', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('48', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('49', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('50', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('51', '1', 'testtitle', 'testbody', '2016', 'testAuthor', '88', '');
INSERT INTO `worksummary` VALUES ('52', '37', '2016-04-25至2016-04-29工作记录', '123456', '2016-04-25', 'levy', '2', '');
INSERT INTO `worksummary` VALUES ('53', '37', '2016-04-25至2016-04-29工作记录', '132456', '2016-04-25', 'levy', '2', '');
INSERT INTO `worksummary` VALUES ('54', '37', '2016-04-25至2016-04-29工作记录', '132456', '2016-04-25', 'levy', '2', '123456');
INSERT INTO `worksummary` VALUES ('55', '37', '2016-04-25至2016-04-29工作记录', '132456', '2016-04-25', 'levy', '2', '123456');
INSERT INTO `worksummary` VALUES ('56', '37', '2016-04-25至2016-04-29工作记录', '132456', '2016-04-25', 'levy', '2', '123456');
INSERT INTO `worksummary` VALUES ('57', '37', '2016-04-25至2016-04-29工作记录', '123', '2016-04-25', 'levy', '2', '123');
INSERT INTO `worksummary` VALUES ('58', '37', '2016-04-25至2016-04-29工作记录', '123\r\n123', '2016-04-25', 'levy', '2', '123\r\n123');
INSERT INTO `worksummary` VALUES ('59', '37', '2016-04-25至2016-04-29工作记录', '123\r\n123', '2016-04-25', 'levy', '2', '123123');
