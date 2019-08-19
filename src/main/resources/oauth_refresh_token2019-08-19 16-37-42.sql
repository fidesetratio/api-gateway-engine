USE gateway;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `oauth_refresh_token`(`token_id`,`token`,`authentication`) values ('fe308a19e9d8307123d6f57b513f3e7f',nullnullSystem.Byte[],nullnullSystem.Byte[]);
