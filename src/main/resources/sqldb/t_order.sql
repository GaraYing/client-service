/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50560
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-08-14 20:06:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderid` varchar(25) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `ordermoney` decimal(10,0) DEFAULT NULL,
  `orderstatus` varchar(10) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2018-08-15 00:00:00', null, '0', null);
INSERT INTO `t_order` VALUES ('2', '2018-08-09 19:30:02', null, '0', null);
INSERT INTO `t_order` VALUES ('3', '2018-08-23 19:30:13', null, '0', null);
