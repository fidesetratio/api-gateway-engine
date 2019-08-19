USE gateway;

CREATE TABLE `roles_categories` (
  `roleCategoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_description` varchar(255) DEFAULT NULL,
  `category_name` varchar(32) DEFAULT NULL,
  `creationDateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`roleCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `roles_categories`(`roleCategoryId`,`category_description`,`category_name`,`creationDateTime`) values (1,'This is only simple Role User','ROLE_USER','2019-08-19 16:06:26');
