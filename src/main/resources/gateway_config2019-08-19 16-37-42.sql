USE gateway;

CREATE TABLE `gateway_config` (
  `gateway_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` varchar(255) DEFAULT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_new` varchar(255) DEFAULT NULL,
  `key_config` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gateway_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `gateway_config`(`active`,`creation_date_time`,`gateway_id`,`description`,`is_new`,`key_config`,`value`) values ('Y','2019-03-22 13:26:46',1,'Token Authorization Url','Y','gateway.locator.prop.remote.token.services','http://localhost:9090/oauth/check_token');
insert into `gateway_config`(`active`,`creation_date_time`,`gateway_id`,`description`,`is_new`,`key_config`,`value`) values ('Y','2019-03-22 13:26:46',2,'Client id application','Y','gateway.locator.prop.remote.token.clientid','clientapp');
insert into `gateway_config`(`active`,`creation_date_time`,`gateway_id`,`description`,`is_new`,`key_config`,`value`) values ('Y','2019-03-22 13:26:46',3,'Client secret','Y','gateway.locator.prop.remote.token.clientsecret','123456');
