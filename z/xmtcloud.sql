/*
Navicat MySQL Data Transfer

Source Server         : 115.29.175.225
Source Server Version : 50525
Source Host           : 115.29.175.225:3306
Source Database       : xmtcloud

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-08-16 22:08:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for td_activity_process
-- ----------------------------
DROP TABLE IF EXISTS `td_activity_process`;
CREATE TABLE `td_activity_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `form` varchar(255) DEFAULT NULL,
  `guest` varchar(255) DEFAULT NULL,
  `sortNum` int(11) DEFAULT NULL,
  `times` varchar(255) DEFAULT NULL,
  `activityid` int(11) DEFAULT NULL,
  `activity_process_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_activity_process
-- ----------------------------

-- ----------------------------
-- Table structure for td_activity_type
-- ----------------------------
DROP TABLE IF EXISTS `td_activity_type`;
CREATE TABLE `td_activity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_activity_type
-- ----------------------------

-- ----------------------------
-- Table structure for td_advert
-- ----------------------------
DROP TABLE IF EXISTS `td_advert`;
CREATE TABLE `td_advert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of td_advert
-- ----------------------------
INSERT INTO `td_advert` VALUES ('4', '新东方', 'upload/adverts/20140810/1407649432080.jpg', '1', 'www.baidu.com');
INSERT INTO `td_advert` VALUES ('5', '华尔街英语', 'upload/adverts/20140810/1407651427300.jpg', '2', 'www.huaerjie.com');

-- ----------------------------
-- Table structure for td_article
-- ----------------------------
DROP TABLE IF EXISTS `td_article`;
CREATE TABLE `td_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assistCount` int(11) DEFAULT NULL,
  `audio` varchar(255) DEFAULT NULL,
  `auditState` int(11) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `auditUserId` int(11) DEFAULT NULL,
  `collectCount` int(11) DEFAULT NULL,
  `commentCount` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `delMark` int(11) DEFAULT NULL,
  `fileHtml` varchar(255) DEFAULT NULL,
  `followCount` int(11) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `shareCount` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `viewCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of td_article
-- ----------------------------
INSERT INTO `td_article` VALUES ('1', null, null, '1', '2014-08-11 21:45:37', '1', '123', '432', '这是第一篇文章。。。这篇文章只有几个字。。。', '2014-08-11 09:45:37', '0', null, '67', null, '544', null, '1', null, '第一篇文章', '2', '1', '56');
INSERT INTO `td_article` VALUES ('2', null, null, '1', '2014-08-12 21:53:19', '1', '32', '121', '不知道写什么。。。', '2014-08-12 21:53:19', '0', null, '65', null, '34', null, '1', null, '第二篇文章', '2', '2', '43');
INSERT INTO `td_article` VALUES ('3', null, null, '1', '2014-08-12 21:54:16', '1', '42', '543', '无与伦比。。。', '2014-08-12 21:54:16', '0', null, '76', null, '89', null, '2', null, '第三篇文章', '2', '1', '87');
INSERT INTO `td_article` VALUES ('4', null, null, '0', '2014-08-12 22:00:50', '1', '43', '543', '好难想。。。', '2014-08-12 22:00:50', '0', null, '65', null, '32', null, '0', null, '第四篇文章', '1', '1', '43');
INSERT INTO `td_article` VALUES ('5', null, null, '1', '2014-08-12 22:02:04', '1', '564', '12', '又来了。。。', '2014-08-12 22:02:04', '0', null, '65', null, '12', null, '0', null, '第五篇文章', '3', '1', '543');
INSERT INTO `td_article` VALUES ('6', null, null, '1', '2014-08-15 11:43:07', '1', null, null, '<p>测试文章一测试文章一测试文章一<span style=\"color: rgb(255, 0, 0);\">测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一</span>测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一测<span style=\"font-size: 12px; font-family: 楷体,楷体_GB2312,SimKai;\">试文章一测试文章一测试文章一测试文章一测试文章一测</span>试文章一测试文章一测试文章一测试文章一测试文章一测试<span style=\"font-family: 隶书,SimLi;\">文章一测试文</span>章一测试文章一测试文章一测试文章一测试文章一测试文章一测试文章一</p>', '2014-08-15 11:43:07', '0', null, null, null, null, '锌媒体', '0', '测试文章一测试文章一', '测试文章一', '2', '1', null);

-- ----------------------------
-- Table structure for td_article_lable
-- ----------------------------
DROP TABLE IF EXISTS `td_article_lable`;
CREATE TABLE `td_article_lable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL,
  `lable_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jgwtm7k0k3s3c9qj92v0ev186` (`article_id`),
  KEY `FK_ak4tg339p7hao31888drkiyxi` (`lable_id`),
  CONSTRAINT `FK_ak4tg339p7hao31888drkiyxi` FOREIGN KEY (`lable_id`) REFERENCES `td_lable` (`id`),
  CONSTRAINT `FK_jgwtm7k0k3s3c9qj92v0ev186` FOREIGN KEY (`article_id`) REFERENCES `td_article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of td_article_lable
-- ----------------------------
INSERT INTO `td_article_lable` VALUES ('1', '1', '1');
INSERT INTO `td_article_lable` VALUES ('2', '1', '2');
INSERT INTO `td_article_lable` VALUES ('3', '1', '3');
INSERT INTO `td_article_lable` VALUES ('4', '1', '4');
INSERT INTO `td_article_lable` VALUES ('5', '1', '5');
INSERT INTO `td_article_lable` VALUES ('6', '2', '5');
INSERT INTO `td_article_lable` VALUES ('7', '3', '2');
INSERT INTO `td_article_lable` VALUES ('8', '6', '2');
INSERT INTO `td_article_lable` VALUES ('9', '6', '3');
INSERT INTO `td_article_lable` VALUES ('10', '6', '14');

-- ----------------------------
-- Table structure for td_lable
-- ----------------------------
DROP TABLE IF EXISTS `td_lable`;
CREATE TABLE `td_lable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of td_lable
-- ----------------------------
INSERT INTO `td_lable` VALUES ('1', 'IT');
INSERT INTO `td_lable` VALUES ('2', '时政');
INSERT INTO `td_lable` VALUES ('3', '头条');
INSERT INTO `td_lable` VALUES ('4', '娱乐');
INSERT INTO `td_lable` VALUES ('5', '体育');
INSERT INTO `td_lable` VALUES ('6', '财经');
INSERT INTO `td_lable` VALUES ('7', '社会');
INSERT INTO `td_lable` VALUES ('8', '历史');
INSERT INTO `td_lable` VALUES ('9', '舆论');
INSERT INTO `td_lable` VALUES ('10', '八卦');
INSERT INTO `td_lable` VALUES ('11', '军事');
INSERT INTO `td_lable` VALUES ('12', '科技');
INSERT INTO `td_lable` VALUES ('13', '咨询');
INSERT INTO `td_lable` VALUES ('14', '国内');
INSERT INTO `td_lable` VALUES ('15', '国外');

-- ----------------------------
-- Table structure for td_user
-- ----------------------------
DROP TABLE IF EXISTS `td_user`;
CREATE TABLE `td_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthDate` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `lastLoginTime` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `registerTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `telPhone` varchar(255) DEFAULT NULL,
  `tweet` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `register` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_user
-- ----------------------------
INSERT INTO `td_user` VALUES ('1', 'Andrew', '北京西城', '1990-01-01', '联创信息科技', 'asdfgdsf@163.com', '男', null, '此人乃江湖一等一剑客，向来独来独往，从不与人接触，也从来没有人见过他的庐山真面目；相传，只要有人见到了他的脸，就绝对见不到第二天的太阳。他手中的那把长剑，也是上古传下来的宝物，削铁如泥，灵气逼人；这把剑，耳鼻咽喉乃是上古仙人为封印妖猴所所铸，剑灵便是那上古灭世妖猴集结怨气所凝，此剑可以感知方圆百里的杀气。威力相当可怕，也只有此剑客能震住这把剑，如若换作旁人，怕是早已被剑灵所嗜。', null, '小王同学', null, null, null, '0', '13512345678', null, null, null);

-- ----------------------------
-- Table structure for tu_manage_dept
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_dept`;
CREATE TABLE `tu_manage_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empNums` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_dept
-- ----------------------------
INSERT INTO `tu_manage_dept` VALUES ('1', '0', '管理部', null);
INSERT INTO `tu_manage_dept` VALUES ('2', '0', '系统运维部', null);
INSERT INTO `tu_manage_dept` VALUES ('3', '0', '锌媒体管理部', null);

-- ----------------------------
-- Table structure for tu_manage_emp
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_emp`;
CREATE TABLE `tu_manage_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthDate` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `empNo` varchar(255) DEFAULT NULL,
  `empState` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `idCard` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `marryState` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `telPhone` varchar(255) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `delMark` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qova9nmrljiamcsbfd4le7mg7` (`deptid`),
  CONSTRAINT `FK_qova9nmrljiamcsbfd4le7mg7` FOREIGN KEY (`deptid`) REFERENCES `tu_manage_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_emp
-- ----------------------------
INSERT INTO `tu_manage_emp` VALUES ('1', '1985-07-14', null, '13505142105@139.com', '4501', '1', '男', '1000000011', null, null, '赵文军', '13505142105', '13505142105', '1', '0');
INSERT INTO `tu_manage_emp` VALUES ('2', '1985-08-06', null, '13701460515@139.com', '1001', '1', '女', '1000000012', null, null, '钛媒体', '13701460515', '13701460515', '2', '0');
INSERT INTO `tu_manage_emp` VALUES ('17', '2014-08-05', '初中', 'adfgdfg@163.com', '2008142110', '1', '男', '420322198912156016', 'upload/emps/f499abd1677b47bc9032d7a4efeb1eb3.jpg', '1', '小王', '010-12345612', '13512345678', '1', '0');
INSERT INTO `tu_manage_emp` VALUES ('18', '2014-08-07', '初中', 'adfgdfg@163.com', '2008142110', '1', '女', '420322198912156016', 'upload/emps/97469d46beb84c459f98b93ddf4a27c5.jpg', '1', '赵', '010-12345612', '13612345678', '1', '0');

-- ----------------------------
-- Table structure for tu_manage_privilege
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_privilege`;
CREATE TABLE `tu_manage_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderNum` int(11) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_privilege
-- ----------------------------
INSERT INTO `tu_manage_privilege` VALUES ('1', '/resources/images/manage/leftico01.png', null, '系统管理', '1', null);
INSERT INTO `tu_manage_privilege` VALUES ('2', '/resources/images/manage/leftico02.png', null, '文章管理', '3', null);
INSERT INTO `tu_manage_privilege` VALUES ('3', '/resources/images/manage/leftico03.png', null, '活动管理', '4', null);
INSERT INTO `tu_manage_privilege` VALUES ('4', '/resources/images/manage/leftico04.png', null, '系统设定', '5', null);
INSERT INTO `tu_manage_privilege` VALUES ('5', null, '/manage/emp/list', '员工管理', '1', '1');
INSERT INTO `tu_manage_privilege` VALUES ('6', null, '/cms/article/list\r\n', '文章管理', '1', '2');
INSERT INTO `tu_manage_privilege` VALUES ('7', null, null, '标签设定', '1', '4');
INSERT INTO `tu_manage_privilege` VALUES ('8', null, null, '黑名单管理', '2', '4');
INSERT INTO `tu_manage_privilege` VALUES ('9', null, null, '广告管理', '3', '4');
INSERT INTO `tu_manage_privilege` VALUES ('10', '/resources/images/manage/leftico02.png', null, '用户管理', '2', null);
INSERT INTO `tu_manage_privilege` VALUES ('11', null, null, '用户管理', '1', '10');

-- ----------------------------
-- Table structure for tu_manage_priv_role
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_priv_role`;
CREATE TABLE `tu_manage_priv_role` (
  `role_id` int(11) NOT NULL,
  `priv_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`priv_id`),
  KEY `FK_j7an58xv50fo1oagrt5wt80bx` (`priv_id`),
  KEY `FK_8lp6megwlwv4gkqwc5ijb1wfa` (`role_id`),
  CONSTRAINT `FK_8lp6megwlwv4gkqwc5ijb1wfa` FOREIGN KEY (`role_id`) REFERENCES `tu_manage_role` (`id`),
  CONSTRAINT `FK_j7an58xv50fo1oagrt5wt80bx` FOREIGN KEY (`priv_id`) REFERENCES `tu_manage_privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_priv_role
-- ----------------------------
INSERT INTO `tu_manage_priv_role` VALUES ('1', '1');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '2');
INSERT INTO `tu_manage_priv_role` VALUES ('2', '2');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '3');
INSERT INTO `tu_manage_priv_role` VALUES ('2', '3');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '4');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '5');
INSERT INTO `tu_manage_priv_role` VALUES ('2', '5');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '6');
INSERT INTO `tu_manage_priv_role` VALUES ('2', '6');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '7');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '8');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '9');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '10');
INSERT INTO `tu_manage_priv_role` VALUES ('1', '11');

-- ----------------------------
-- Table structure for tu_manage_role
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_role`;
CREATE TABLE `tu_manage_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_role
-- ----------------------------
INSERT INTO `tu_manage_role` VALUES ('1', null, '系统管理员');
INSERT INTO `tu_manage_role` VALUES ('2', null, '锌媒体管理员');

-- ----------------------------
-- Table structure for tu_manage_user
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_user`;
CREATE TABLE `tu_manage_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `lastLoginTime` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a8um4wyewq34rud2qqnbx7g5y` (`emp_id`),
  CONSTRAINT `FK_a8um4wyewq34rud2qqnbx7g5y` FOREIGN KEY (`emp_id`) REFERENCES `tu_manage_emp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_user
-- ----------------------------
INSERT INTO `tu_manage_user` VALUES ('1', 'admin', '', '96E79218965EB72C92A549DD5A330112', '1', '1');
INSERT INTO `tu_manage_user` VALUES ('2', 'xmt', null, '96E79218965EB72C92A549DD5A330112', null, '2');

-- ----------------------------
-- Table structure for tu_manage_user_priv
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_user_priv`;
CREATE TABLE `tu_manage_user_priv` (
  `user_id` int(11) NOT NULL,
  `priv_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`priv_id`),
  KEY `FK_7xauprdgsu1yodut1po7hlom8` (`priv_id`),
  KEY `FK_6r685os30k01t7q62bpidw9ay` (`user_id`),
  CONSTRAINT `FK_6r685os30k01t7q62bpidw9ay` FOREIGN KEY (`user_id`) REFERENCES `tu_manage_user` (`id`),
  CONSTRAINT `FK_7xauprdgsu1yodut1po7hlom8` FOREIGN KEY (`priv_id`) REFERENCES `tu_manage_privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_user_priv
-- ----------------------------

-- ----------------------------
-- Table structure for tu_manage_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tu_manage_user_role`;
CREATE TABLE `tu_manage_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_qq4y20a2xdyx4e4opfd4228xi` (`role_id`),
  KEY `FK_29nbuwmobalajqag5q0vkh4h9` (`user_id`),
  CONSTRAINT `FK_29nbuwmobalajqag5q0vkh4h9` FOREIGN KEY (`user_id`) REFERENCES `tu_manage_user` (`id`),
  CONSTRAINT `FK_qq4y20a2xdyx4e4opfd4228xi` FOREIGN KEY (`role_id`) REFERENCES `tu_manage_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tu_manage_user_role
-- ----------------------------
INSERT INTO `tu_manage_user_role` VALUES ('1', '1');
INSERT INTO `tu_manage_user_role` VALUES ('2', '2');
