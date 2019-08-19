USE gateway;

CREATE TABLE `s_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` varchar(100) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


insert into `s_permissions`(`id`,`id_user`,`user_role`) values (1,'1','ROLE_SUPERVISOR');
insert into `s_permissions`(`id`,`id_user`,`user_role`) values (2,'1','ROLE_OPERATOR');
insert into `s_permissions`(`id`,`id_user`,`user_role`) values (3,'2','ROLE_OPERATOR');
