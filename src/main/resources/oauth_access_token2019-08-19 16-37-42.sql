USE gateway;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `oauth_access_token`(`token_id`,`token`,`authentication_id`,`user_name`,`client_id`,`authentication`,`refresh_token`) values ('ef164e7dbae5e5270c8622ff45959af3',nullnullSystem.Byte[],'aa99c553b111f9e9383df8ebb9cd7bb6','roy','clientapp',nullnullSystem.Byte[],'fe308a19e9d8307123d6f57b513f3e7f');
