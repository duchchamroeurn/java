CREATE TABLE `users` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `users` (`username`, `password`, `is_active`)
VALUES
	('admin','$2a$10$AY1tqrF4BmrN0Nzj2218i.oPIn0.TOsXhqSF.Tb4TG1q4mY.TCY56',1),
	('user','$2a$10$AY1tqrF4BmrN0Nzj2218i.oPIn0.TOsXhqSF.Tb4TG1q4mY.TCY56',1);

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_roles` (`user_role_id`, `username`, `role`)
VALUES
	(2,'admin','ROLE_ADMIN'),
	(1,'admin','ROLE_USER'),
	(3,'user','ROLE_USER');

CREATE TABLE `persistent_logins` (
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `series` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `products` (
  `product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` double NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `meta_tag_title` tinytext COLLATE utf8_unicode_ci,
  `meta_tag_description` tinytext COLLATE utf8_unicode_ci,
  `meta_tag_keywords` tinytext COLLATE utf8_unicode_ci,
  `location` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_online` tinyint(4) NOT NULL DEFAULT '0',
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `product_images` (
  `product_image_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `src` varchar(1000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sort_order` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;