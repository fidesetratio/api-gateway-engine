USE gateway;

CREATE TABLE `roles` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `FK5gs1esew9aj28627pj40dvmyn` (`category_id`),
  CONSTRAINT `FK5gs1esew9aj28627pj40dvmyn` FOREIGN KEY (`category_id`) REFERENCES `roles_categories` (`roleCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `roles`(`roleId`,`role_name`,`category_id`) values (1,'ROLE_USER',1);
