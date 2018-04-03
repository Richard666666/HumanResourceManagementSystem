/*
 Navicat MySQL Data Transfer

 Source Server         : humanresource
 Source Server Type    : MySQL
 Source Server Version : 50558
 Source Host           : localhost:3308
 Source Schema         : human

 Target Server Type    : MySQL
 Target Server Version : 50558
 File Encoding         : 65001

 Date: 06/01/2018 14:22:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `dId` int(11) NOT NULL AUTO_INCREMENT,
  `dName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dDes` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, '网络部', '战略支援');
INSERT INTO `t_dept` VALUES (3, '采购部', '为公司进行物资采购');
INSERT INTO `t_dept` VALUES (4, '政企客户部', '供应商的沟通');
INSERT INTO `t_dept` VALUES (5, '数据部', '收集大数据');
INSERT INTO `t_dept` VALUES (6, '市场部', '开拓市场');
INSERT INTO `t_dept` VALUES (7, '财务部', '为公司提供财务');

-- ----------------------------
-- Table structure for t_download
-- ----------------------------
DROP TABLE IF EXISTS `t_download`;
CREATE TABLE `t_download`  (
  `doId` int(11) NOT NULL AUTO_INCREMENT,
  `doDes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doTitle` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doCreateTime` date NULL DEFAULT NULL,
  `uId` int(11) NULL DEFAULT NULL,
  `filePath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`doId`) USING BTREE,
  INDEX `t_download_user`(`uId`) USING BTREE,
  CONSTRAINT `t_download_user` FOREIGN KEY (`uId`) REFERENCES `t_user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_download
-- ----------------------------
INSERT INTO `t_download` VALUES (22, '亚马逊的河流', '图片一', '2018-01-06', 1, '/upload\\view.jpg');
INSERT INTO `t_download` VALUES (23, '红色金典', '图片二', '2018-01-06', 1, '/upload\\red.jpg');
INSERT INTO `t_download` VALUES (24, '冬天的火', '图片三', '2018-01-06', 1, '/upload\\fire.jpg');

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`  (
  `eId` int(11) NOT NULL AUTO_INCREMENT,
  `eName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eGender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eTelNum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eEmail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jId` int(11) NULL DEFAULT NULL,
  `eStu` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dId` int(11) NULL DEFAULT NULL,
  `eIdCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `eCreateTime` date NULL DEFAULT NULL,
  `eAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eId`) USING BTREE,
  INDEX `t_emp_job`(`jId`) USING BTREE,
  INDEX `t_emp_dept`(`dId`) USING BTREE,
  CONSTRAINT `t_emp_dept` FOREIGN KEY (`dId`) REFERENCES `t_dept` (`dId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_emp_job` FOREIGN KEY (`jId`) REFERENCES `t_job` (`jId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES (1, '小红', '1', '13900000000', 'swpu@qq.com', 2, '本科', 1, '1', '2018-01-10', '南开大学');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
  `jId` int(11) NOT NULL AUTO_INCREMENT,
  `jName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jDes` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`jId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (1, '管理', '分配任务');
INSERT INTO `t_job` VALUES (2, '会计', '管钱');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `nId` int(11) NOT NULL AUTO_INCREMENT,
  `nName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nContent` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nCreateTime` date NULL DEFAULT NULL,
  `uId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`nId`) USING BTREE,
  INDEX `t_notice_user`(`uId`) USING BTREE,
  CONSTRAINT `t_notice_user` FOREIGN KEY (`uId`) REFERENCES `t_user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (1, '公告一', '今天阳关明媚', NULL, NULL);
INSERT INTO `t_notice` VALUES (4, '公告二', '今天是下雨', NULL, NULL);
INSERT INTO `t_notice` VALUES (6, '公告三', '今天是阳光', '2018-01-06', 1);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uPwd` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uLoginName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uCreateTime` date NULL DEFAULT NULL,
  `uState` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`uId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'richard', 'admin', 'admin', NULL, 1);
INSERT INTO `t_user` VALUES (2, 'richard', '112', '112', '2018-01-06', 1);
