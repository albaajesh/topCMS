/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.5.19 : Database - topcms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`topcms` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `topcms`;

/*Table structure for table `sys_area_city` */

DROP TABLE IF EXISTS `sys_area_city`;

CREATE TABLE `sys_area_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` varchar(4) NOT NULL,
  `city` varchar(50) NOT NULL,
  `province_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_area_city` */

/*Table structure for table `sys_area_county` */

DROP TABLE IF EXISTS `sys_area_county`;

CREATE TABLE `sys_area_county` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `county_id` varchar(6) NOT NULL,
  `county` varchar(50) NOT NULL,
  `city_id` varchar(4) NOT NULL,
  `county_value` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_area_county` */

/*Table structure for table `sys_area_province` */

DROP TABLE IF EXISTS `sys_area_province`;

CREATE TABLE `sys_area_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province_id` varchar(2) NOT NULL,
  `province` varchar(50) NOT NULL,
  `province_for_short` varchar(20) NOT NULL,
  `province_for_bus_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_area_province` */

/*Table structure for table `sys_attachment` */

DROP TABLE IF EXISTS `sys_attachment`;

CREATE TABLE `sys_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(500) NOT NULL,
  `file_path` varchar(500) NOT NULL,
  `rid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_attachment` */

/*Table structure for table `sys_organization` */

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_code` varchar(100) DEFAULT NULL,
  `unit_name` varchar(50) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `area` char(10) DEFAULT NULL,
  `remark` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_organization` */

/*Table structure for table `sys_parameter` */

DROP TABLE IF EXISTS `sys_parameter`;

CREATE TABLE `sys_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) NOT NULL DEFAULT '',
  `category` varchar(255) DEFAULT NULL,
  `subcategory` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `parent_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `sys_parameter` */

insert  into `sys_parameter`(`id`,`uuid`,`category`,`subcategory`,`name`,`value`,`remark`,`short_name`,`sort`,`parent_id`) values (3,'aaed6f80-f17c-4884-92a8-0ee135186ad0','scheduler','scheduler','','','',NULL,0,0),(4,'8d461f39-1212-480e-93d7-155bde8a880e','scheduler','schedulerType','day','1','',NULL,0,3),(5,'a66d99cb-9e22-43e8-bb03-d616e25fc4ae','scheduler','schedulerType','month','2','',NULL,0,3),(6,'091f48fa-391e-424d-bfa5-675277d359c0','scheduler','schedulerType','year','3','',NULL,0,3),(7,'24418cc5-30d7-40d4-bd9b-662402e6e8b8','dd','dd','dd','dd','',NULL,0,0),(8,'619c0a88-9f5e-41c2-8240-f33809e70160','dd','dd','dd','dd','',NULL,0,0),(9,'d52d818b-4047-4555-84e5-fb0f4690aca3','fv','vcv','ccx','c','cc',NULL,0,0),(10,'5c93b811-ae94-4ac3-b992-f7e3cea7c11a','fv','c','vvv','xxc','xx',NULL,0,9);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL,
  `ckey` varchar(255) NOT NULL,
  `pkey` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `permission_type` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`pid`,`ckey`,`pkey`,`name`,`value`,`permission_type`,`sort`) values (1,0,'bc914d00-6837-492c-8f99-471b2176bdb4','0','setting','menu:setting','2',0),(2,1,'d2274a49-74a6-4d0c-b7a8-0f16d125b4c0','bc914d00-6837-492c-8f99-471b2176bdb4','user','menu:user','2',0),(3,1,'d271d733-4e57-4181-9f5f-730b53b4edc8','bc914d00-6837-492c-8f99-471b2176bdb4','role','menu:role','2',0),(4,1,'92c2b77c-6e77-4eaf-aa43-cfba4c3da583','bc914d00-6837-492c-8f99-471b2176bdb4','permisson','menu:permisson','2',0),(5,1,'c66f2469-ce0e-4e52-9d31-c9940df79ebe','bc914d00-6837-492c-8f99-471b2176bdb4','parameter','menu:parameter','2',0),(6,0,'d1072fe8-e021-4e91-be93-6db9493d898c','0','operation','fun:operation','3',0),(7,6,'e6e6e60b-594d-4bd6-ad74-255a5aa10b49','d1072fe8-e021-4e91-be93-6db9493d898c','add','fun:add','3',0),(8,6,'e6f72db6-57d0-4498-b1a1-a27eb7918510','d1072fe8-e021-4e91-be93-6db9493d898c','edit','fun:edit','3',0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`role_type`,`pid`,`remark`) values (1,'admin','cb6e6022-955e-4978-ae2c-afa392be5ebf',0,'admin'),(2,'access',NULL,0,'access everything'),(3,'dd',NULL,0,'ddd');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`role_id`,`permission`) values (1,'menu:parameter'),(1,'menu:permisson'),(1,'menu:role'),(1,'menu:user'),(1,'menu:setting'),(1,'menu:setting'),(1,'menu:setting'),(2,'menu:role');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(18) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `area` varchar(10) DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  `register_date` varchar(100) DEFAULT NULL,
  `user_status` int(11) DEFAULT '0',
  `theme` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`name`,`password`,`salt`,`area`,`user_type`,`register_date`,`user_status`,`theme`) values (1,'admin','admin','af27c8816506bf1b6705b50866f9cde0a67401cb','eb69619a75232f52','371401',0,'0',0,'bootstrap'),(2,'user','user','fc4b2d83355003aa9649ddcb47a38a4f957636e4','cba97144a760fd39',NULL,0,NULL,0,'bootstrap');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `user_id_index` (`user_id`),
  KEY `role_id_index` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (2,1),(2,2),(1,1),(1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
