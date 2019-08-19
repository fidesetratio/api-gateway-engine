USE gateway;

CREATE TABLE `s_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `s_users`(`id`,`username`,`password`,`active`) values (1,'roy','spring',1);
insert into `s_users`(`id`,`username`,`password`,`active`) values (2,'adi','123',1);
