/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.58 : Database - android_qingshe
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`android_qingshe` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `android_qingshe`;

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `user_id` int(11) DEFAULT NULL,
  `collect_id` int(3) DEFAULT NULL,
  `kind` int(3) DEFAULT NULL COMMENT '1:服务 0：二手商品'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `collection` */

insert  into `collection`(`user_id`,`collect_id`,`kind`) values (123,1,0),(123,2,0),(123,3,0);

/*Table structure for table `coupon` */

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `user_id` int(11) DEFAULT NULL,
  `amount` double(11,1) DEFAULT NULL COMMENT '优惠券额度',
  `kind` int(3) DEFAULT NULL COMMENT '0:闲置 1：养护 2：通用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coupon` */

insert  into `coupon`(`user_id`,`amount`,`kind`) values (123,11.0,0),(1111,11.0,1),(123,11.0,2);

/*Table structure for table `orderlist` */

DROP TABLE IF EXISTS `orderlist`;

CREATE TABLE `orderlist` (
  `consumer_id` varchar(50) DEFAULT NULL,
  `provider_id` int(11) DEFAULT NULL,
  `state` int(5) DEFAULT NULL COMMENT '1:已完成 0：未完成',
  `kind` int(3) DEFAULT NULL COMMENT ' 0：闲置订单 1:养护订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderlist` */

insert  into `orderlist`(`consumer_id`,`provider_id`,`state`,`kind`) values ('123',0,0,0),('134',0,0,0),('134',2,0,1),('134',8,0,1);

/*Table structure for table `second_good` */

DROP TABLE IF EXISTS `second_good`;

CREATE TABLE `second_good` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `price` double(11,1) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `state` int(5) DEFAULT NULL COMMENT '1:未售完 0：已售完',
  `icon` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `second_good` */

insert  into `second_good`(`id`,`name`,`price`,`desc`,`state`,`icon`) values (0,'LV皮包',11000.0,'M44022',1,'upload/good_1.png'),(1,'AJ1 MID鞋子',969.0,'Jordan官方AIR JORDAN 1 MID AJ1男子运动鞋554724',1,'upload/good_2.png'),(2,'CHERLES信封包',399.0,'粉蜡色女士单肩包',1,'upload/good_3.png'),(3,'小米拉杆箱',299.0,'小米旅行箱 青春版行李箱男女20寸万向轮24寸拉杆箱密码登机箱子',1,'upload/good_4.png'),(4,'GUCCI手包',6700.0,'GUCCI古驰女包2019新款Dionysus超迷你酒神单肩斜挎包476432',1,'upload/good_5.png'),(5,'FENDI挎包',12000.0,'FENDI/芬迪 KAN I系列多色牛皮铆钉锁扣花边女包斜挎单肩包链条包',1,'upload/good_6.png'),(6,'CHANEL邂逅系列香水',1500.0,'【新年礼遇】CHANEL 香奈儿邂逅系列淡香水 粉色柔情 持久留香',1,'upload/good_7.png'),(7,'CHANEL五号香水',1600.0,'【新年礼遇】CHANEL 香奈儿五号之水 经典淡香水五号香水花香调',1,'upload/good_8.png'),(8,'GUCCI单肩皮包',17000.0,'Gucci/古驰女包Dionysus皮革迷你单肩包20 * 15.5 * 5',1,'upload/good_9.png'),(9,'AJ34鞋子',1499.0,'Jordan 官方 AIR JORDAN XXXIV PF AJ34男子篮球鞋 BQ3381',1,'upload/good_10.png'),(10,'AJ1 HIGH REACT鞋子',1299.0,'Jordan 官方AIR JORDAN 1 HIGH REACT AJ１男子运动鞋AR5321',1,'upload/good_11.png');

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) DEFAULT NULL,
  `price` double(11,1) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL COMMENT '服务说明',
  `kind` int(4) DEFAULT NULL COMMENT '服务种类1、包袋 2、鞋靴   3、衣帽  4、配饰  5、维修',
  `icon` varchar(200) DEFAULT NULL COMMENT '服务类型图像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `service` */

insert  into `service`(`id`,`name`,`price`,`desc`,`kind`,`icon`) values (1,'手包清洗养护',338.0,'调配箱包专用清洗剂，亮丽如新',1,'upload/service_1.jpg'),(2,'肩包清洗养护',428.0,'深度滋养护理皮质',1,'upload/service_2.jpg'),(3,'鞋子清洗养护',99.0,'采用的德国精准工艺，定点养护',2,'upload/service_3.jpg'),(4,'钱夹清洗养护',268.0,'源自意大利先进工艺',1,'upload/service_4.jpg'),(5,'拉链维修',38.0,'采购进口耐用拉链',5,'upload/service_5.jpg'),(6,'整体修补',128.0,'整体缝补，清洁',5,'upload/service_6.jpg'),(7,'长靴清洗养护',128.0,'针对长靴，由专业人员进行技术养护',2,'upload/service_7.jpg'),(8,'棉衣清洗养护',99.0,'针对棉衣，由专业人员进行技术养护',3,'upload/service_8.jpg'),(9,'皮衣清洗养护',128.0,'量子力学除皱，精油滋养，深层滋润养护皮衣',3,'upload/service_9.jpg'),(10,'配饰修补',99.0,'由首席设计师完成配饰修补，使其焕然一新',4,'upload/service_10.jpg'),(11,'配饰定制',128.0,'Made In China',4,'upload/service_11.jpg');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(50) NOT NULL COMMENT 'phone',
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `points` int(11) DEFAULT '0' COMMENT '积分',
  `icon` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`password`,`nickname`,`points`,`icon`) values ('110','110','sdfji',0,'upload/1.jpg'),('123','123','haha',22,'upload/2.jpg'),('1234','1234','hanhan',7,'upload/1.jpg'),('134','134','fff',3,'upload/1.jpg'),('13476023987','123','loco',0,'upload/3.jpg'),('1357','1357','locoloco',0,'upload/1.jpg'),('2345','2345','miyuk',1,'upload/1.jpg'),('321','1aaa','h2321',0,'upload/1.jpg'),('4321','4321','422',0,'upload/1.jpg'),('444','444','小艾',2,'upload/1.jpg'),('456','456','tret',1,'upload/1.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
