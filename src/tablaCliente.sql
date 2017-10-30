CREATE TABLE `cliente` (  `id` int(11) NOT NULL AUTO_INCREMENT, 
`dni` varchar(9) NOT NULL, 
 `name` varchar(50) NOT NULL,  
`phone` int(11) NOT NULL, 
 `country` varchar(50) NOT NULL, 
 `years` int(11) NOT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;