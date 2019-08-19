USE gateway;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `hibernate_sequence`(`next_val`) values (9);
insert into `hibernate_sequence`(`next_val`) values (9);
