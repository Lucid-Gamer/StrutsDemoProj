CREATE TABLE `com_ldgr_dbrd_menu_mst` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(30) NOT NULL,
  `menu_tab_id` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` date DEFAULT curdate(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` date DEFAULT curdate(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_id` (`menu_id`)
) 

=======================================================================================================================================================================

CREATE TABLE `com_ldgr_dbrd_menu_items_mst` (
  `id` int(11) NOT NULL,
  `menu_item_name` varchar(30) DEFAULT NULL,
  `menu_item_name_id` varchar(50) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` date DEFAULT curdate(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` date DEFAULT curdate(),
  PRIMARY KEY (`id`)
) 

=======================================================================================================================================================================


