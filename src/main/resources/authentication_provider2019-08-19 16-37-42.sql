USE gateway;

CREATE TABLE `authentication_provider` (
  `providerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `clientSecret` varchar(255) DEFAULT NULL,
  `provider_name` varchar(32) DEFAULT NULL,
  `type_provider` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`providerId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `authentication_provider`(`providerId`,`active`,`clientId`,`clientSecret`,`provider_name`,`type_provider`,`url`) values (1,'Y','clientauthcode','123456','Manager Oauth','oauth2','http://localhost:8787/oauth/check_token');
