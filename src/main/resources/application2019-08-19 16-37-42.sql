USE gateway;

CREATE TABLE `application` (
  `appId` int(11) NOT NULL AUTO_INCREMENT,
  `applicationName` varchar(255) NOT NULL DEFAULT '',
  `description` varchar(255) NOT NULL DEFAULT '',
  `context` varchar(255) NOT NULL DEFAULT '',
  `photo` varchar(255) NOT NULL DEFAULT '',
  `permitAll` int(11) NOT NULL DEFAULT '0',
  `roleCategoryId` int(11) NOT NULL DEFAULT '0',
  `providerId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;


insert into `application`(`appId`,`applicationName`,`description`,`context`,`photo`,`permitAll`,`roleCategoryId`,`providerId`) values (24,'Autosales','This application is used to help an agent to input customer data (CRM) , make illustration proposal and make SPAJ.','/autosales','autosales.JPG',1,0,0);
insert into `application`(`appId`,`applicationName`,`description`,`context`,`photo`,`permitAll`,`roleCategoryId`,`providerId`) values (25,'Smile Go','SMiLe go! adalah aplikasi untuk melakukan recruitment calon agen baru yang akan bergabung dengan perusahaan Asuransi Jiwa Sinarmas MSIG.
Aplikasi ini juga bertujuan untuk mempercepat proses rekrut agen baru dengan menggunakan aplikasi mobile','/agency','smilego.JPG',1,0,0);
insert into `application`(`appId`,`applicationName`,`description`,`context`,`photo`,`permitAll`,`roleCategoryId`,`providerId`) values (26,'Smile Go Test','Testing for Smile Go','/agencytest','smilego.JPG',1,0,0);
insert into `application`(`appId`,`applicationName`,`description`,`context`,`photo`,`permitAll`,`roleCategoryId`,`providerId`) values (27,'MPolicy Test','MPolicy for Web Testing','/mpolicytest','imagenotavailable.png',1,0,0);
insert into `application`(`appId`,`applicationName`,`description`,`context`,`photo`,`permitAll`,`roleCategoryId`,`providerId`) values (28,'Node JS Chandra','Ini untuk coba-coba si chandr','/nodejs','imagenotavailable.png',0,1,1);
insert into `application`(`appId`,`applicationName`,`description`,`context`,`photo`,`permitAll`,`roleCategoryId`,`providerId`) values (29,'Amazon','Amazon','/amazon','amazon_dkblue_noto_email_v2016_us-main._CB468775337_.png',1,0,0);
