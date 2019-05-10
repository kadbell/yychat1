/*
Navicat MySQL Data Transfer

Source Server         : mysql1
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : yychat

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-05-11 00:38:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `majoruser` varchar(20) DEFAULT NULL,
  `slaveuser` varchar(20) DEFAULT NULL,
  `relationtype` varchar(5) DEFAULT NULL COMMENT '"1"表示好友，“2”表示陌生人',
  KEY `majoruser` (`majoruser`),
  KEY `slaveuser` (`slaveuser`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`majoruser`) REFERENCES `user` (`username`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`slaveuser`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relation
-- ----------------------------
INSERT INTO `relation` VALUES ('yp', '大海', '1');
INSERT INTO `relation` VALUES ('yp', 'yp1', '1');
INSERT INTO `relation` VALUES ('yp', 'yp2', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `username_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yp', '123456');
INSERT INTO `user` VALUES ('2', '大海', '123456');
INSERT INTO `user` VALUES ('3', 'yp1', '123456');
INSERT INTO `user` VALUES ('4', 'yp2', '123456');
