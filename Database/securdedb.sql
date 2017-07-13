-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: securdedb
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `borrowed_readings`
--

DROP TABLE IF EXISTS `borrowed_readings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrowed_readings` (
  `borrowedreadingid` int(11) NOT NULL AUTO_INCREMENT,
  `readingid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `datereserved` date NOT NULL,
  `dateborrowed` date DEFAULT NULL,
  `duedate` date DEFAULT NULL,
  `datereturned` date DEFAULT NULL,
  PRIMARY KEY (`borrowedreadingid`),
  UNIQUE KEY `borrowedreadingid_UNIQUE` (`borrowedreadingid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowed_readings`
--

LOCK TABLES `borrowed_readings` WRITE;
/*!40000 ALTER TABLE `borrowed_readings` DISABLE KEYS */;
INSERT INTO `borrowed_readings` VALUES (1,1,1,'2017-07-04','0000-00-00','0000-00-00','0000-00-00'),(2,14,2,'2017-07-05','0000-00-00','0000-00-00','0000-00-00'),(3,23,2,'2017-07-01','2017-07-01','2017-07-08','0000-00-00'),(4,34,1,'2017-07-03','2017-07-03','2017-07-10','0000-00-00'),(5,10,1,'2017-07-03','2017-07-03','2017-07-10','0000-00-00'),(6,27,2,'2017-07-01','2017-07-01','2017-07-08','0000-00-00'),(7,40,3,'2017-07-02','2017-07-02','2017-07-09','0000-00-00'),(8,7,2,'2017-07-05','0000-00-00','0000-00-00','0000-00-00'),(9,31,1,'2017-07-04','0000-00-00','0000-00-00','0000-00-00'),(10,17,3,'2017-07-05','0000-00-00','0000-00-00','0000-00-00'),(11,1,2,'2017-06-15','2017-06-15','2017-06-22','2017-06-22'),(12,32,2,'2017-06-25','2017-06-25','2017-07-02','2017-07-02'),(13,12,2,'2017-05-12','2017-05-12','2017-05-19','2017-05-19');
/*!40000 ALTER TABLE `borrowed_readings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`categoryid`),
  UNIQUE KEY `categoryid_UNIQUE` (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Books'),(2,'Thesis'),(3,'Magazines');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readings`
--

DROP TABLE IF EXISTS `readings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readings` (
  `readingid` int(11) NOT NULL AUTO_INCREMENT,
  `readingtitle` longtext NOT NULL,
  `categoryid` int(11) NOT NULL,
  `location` varchar(45) NOT NULL,
  `author` varchar(70) DEFAULT NULL,
  `publisher` varchar(70) DEFAULT NULL,
  `year` varchar(45) NOT NULL,
  `tags` longtext NOT NULL,
  `status` enum('OUT','Reserved','Available') NOT NULL,
  PRIMARY KEY (`readingid`),
  UNIQUE KEY `readingid_UNIQUE` (`readingid`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readings`
--

LOCK TABLES `readings` WRITE;
/*!40000 ALTER TABLE `readings` DISABLE KEYS */;
INSERT INTO `readings` VALUES (1,'Exploring earth science',1,'QE28.3 .R49','Reynolds, Stephen J.','McGraw-Hill Education','2016','Earth, Science','Reserved'),(2,'Philosophy of biology',1,'QH331 .G64','Godfrey-Smith, Peter.','Princeton University Press','2014','Philosophy, Biology, Science','Available'),(3,'Art and fear',1,'BH202 .V5713','Virilio, Paul.','Continuum','2004','Aesthetics, Modern','Available'),(4,'The art & craft of playwriting',1,'PN 1661 H37','Hatcher, Jeffrey.','Story Press','1996','Drama, Playwriting','Available'),(5,'Sociology of mental disorder',1,'RC455 .C6','Cockerham, William C.','Prentice Hall','2003','Mental illness, Social psychiatry','Available'),(6,'Equal treatment for people with mental retardation : having and raising children',1,'KF 480 F54','Field, Martha A.','Harvard University Press','2001','Mental health, Parent and Children, Law, Disabilities','Available'),(7,'Urban myths about learning and education',1,'LB14.7 .D4','De Bruyckere, Pedro.','Academic Press','2015','Education, Myths','Reserved'),(8,'Social network analysis and education : theory, methods & applications',1,'HM741 .C37','Carolan, Brian V.','SAGE','2014','Social Networks, Education, Research','Available'),(9,'Emotions across languages and cultures : diversity and universals',1,'BF531 W54','Wierzbicka, Anna.','Cambridge University Press','1999','Emotions','Available'),(10,'Emotions and memory',1,'BF531 R36','Rapaport, David.','International Universities Press','1950','Emotions, Memory, Psychology, Pathological','OUT'),(11,'Principles of highway engineering and traffic analysis',1,'TE147 .M36','Mannering, Fred L.','John Wiley & Sons','2013','Traffic Engineering, Highway','Available'),(12,'Business driven information systems',1,'HD30.2 .B33','Baltzan, Paige.','McGraw-Hill/Irwin','2009','Management, Information Technology','Available'),(13,'Tax planning for business',1,'CD07353','Stern, W. Rod.','Entrepreneur Press','2008','Taxation, Business Enterprises','Available'),(14,'A concise survey of music philosophy',1,'ML3800 .H713','Hodges, Donald A.','Routledge','2017','Music, Philosophy, Aesthetics','Reserved'),(15,'Strut : mobile social mapping system through message consolidation',2,'TU18523','Clamor, George Anthony A.',NULL,'2010','Social Media, Personal Network, Facebook, Twitter','Available'),(16,'Affective design of eco product labels',2,'CDTG006370','Gutierrez, Alma Maria Jennifer A.',NULL,'2015','Eco-Product, Emotion','Available'),(17,'Real-time multimodal affect recognition in laughter episodes',2,'TG05360','Santos, Jose Miguel.',NULL,'2013','Emotion Recognition','Reserved'),(18,'Intimacy in long distance relationship',2,'TU16761','Gatbonton, Andrea Dominique G.',NULL,'2011','Relationship, Feelings','Available'),(19,'A comparative study on the perspectives on sport psychology services.',2,'TU11497','Adriano, Frances Paula M.',NULL,'2003','Sport Psychology','Available'),(20,'Workplace bullying and quality of life: The moderating role of proactrive coping',2,'CDTG006573','Mangalus, Roger S.',NULL,'2016','Bullying, Psychology','Available'),(21,'Integrating Play in Art Activities to Enhance the Social Skills of Children with Autism',2,'CDTG006617','Tumang, Pamela Cristina C.',NULL,'2016','Autism, Arts, Children','Available'),(22,'The art of Japanese dining.',2,'TU05979','Angeles, Melody.',NULL,'1990','Food, Japanese, Dinners and Dining, Social Life','Available'),(23,'Rapid visual screening of major government hospital buildings in Metro Manila',2,'TU15905','Baluyut, Katrina S.',NULL,'2008','Structural Engineering, Buildings, Earthquake','OUT'),(24,'A study of the spirituality of middle-aged adults',2,'TU13185','Asuncion, Adrian Alwyn Labro.',NULL,'2006','Psychology, Spirituality','Available'),(25,'A portrait of my love.',2,'TU12551','Niro, Jann Michelle.',NULL,'2004','Love, Portrait','Available'),(26,'Animo magazine.',3,'LG221.M35',NULL,'Verde Communications and Marketing Inc.','2003','Alumni','Available'),(27,'Reader\'s lifeline magazine.',3,'LG230.M31',NULL,'Luminaire Visual Art Lab','2000','General Interest','OUT'),(28,'Entrepreneur magazine : bringing your product to market',3,'HF5415.153 .D4','Debelak, Don.','Wiley','1997','Marketing, Management, Entrepreneur','Available'),(29,'Entrepreneur magazine : guide to professional services',3,'HD 62.7 B57','Bisk, Leonard.','Wiley','1997','Industrial Management, Business, Management','Available'),(30,'IEEE signal processing magazine.',3,'shelf 4',NULL,'Institute of Electrical & Electronics Engineers','2002','Signal Processing, Electro-acoustics','Available'),(31,'Philippines graphic magazine.',3,'shelf 8',NULL,'Aliw Pub. House','2012','Philippines, Graphic','Reserved'),(32,'Sunday inquirer magazine.',3,'shelf 10',NULL,'Maximo Soliven','2007','Philippines, Politics, Government, Economy','Available'),(33,'IEEE control systems magazine.',3,'TJ212',NULL,'Institute of Electrical and Electronics Engineers','2001','Automatic Control','Available'),(34,'Adobo magazine.',3,'HF5801',NULL,'Sanserif, Inc.','2011','Advertising, Marketing','OUT'),(35,'Cyberspace game fixing : the reality of its impunity under the Philippine laws ',2,'TG06431','Mercado, Christopher Dann C.',NULL,'2016','Cyberspace, Games, Law','Available'),(36,'Determining music features that have an effect on stress levels based on physiological signals ',2,'TG05371','Uy, Gemilene C.',NULL,'2013','Music, Stress, Affect','Available'),(37,'The contributions of Justice Norberto Romualdez, Sr. to Philippine music ',2,'CDTG003994','Romualdez, Joaquin Gallego.',NULL,'2005','Music, Composers','Available'),(38,'Effects of video games and its genre on prosocial behavior ',2,'TU15813','David, Rey Carlo M.','','2010','Games, Altruism, Interpersonal','Available'),(39,'Telecommunications : an application of three-person zero-sum games. ',2,'TU05753','Alegado, Deborah D.',NULL,'1985','Telecommunication, Game Theory, Linear Programming','Available'),(40,'Game sense : pedagogy for performance, participation and enjoyment ',1,'GV361 .L55','Light, Richard Lawrence','Routledge','2013','Sports, Coaching, Children','OUT'),(41,'Practical financial management',1,'HG4026 .L37','Lasher, William.','Cengage Learning','2017','Finance, Corporations, Enterprises, Business','Available'),(42,'The SAGE guide to educational leadership and management',1,'LB2805 .S15',NULL,'SAGE Reference','2015','School Management, Leadership, Education','Available'),(43,'A game of thrones',1,'PS3563.A7239 S66','Martin, George R. R.','Bantam Books','2011','Series','Available'),(44,'Media and politics in a globalizing world ',1,'P95.8 .R6','Robertson, Alexa','Polity','2015','Mass Media, Politics, Social Sciences, Globalization','Available'),(45,'World politics : the menu for choice',1,'JZ1242 .R87','Kinsella, David Todd.','Cengage Learning','2013','Politics, World, International Relations','Available'),(46,'Documenta magazine ',3,'N6497 .D64','Heike Ander','Taschen','2007','Art, Modern, Exhibitions','Available'),(47,'	Rektikano magazine : the official publication of De La Salle Alumni Association.',3,'LG221.M35','','DLSAA','2012','Alumni, De La Salle','Available'),(48,'Codered magazine. ',3,'shelf 3',NULL,'Salesian Mission Office','2004','General Interest','Available'),(49,'Philippine tax journal : the magazine for lawyers, accountants & businessmen.',3,'KPM2781.2.P44',NULL,NULL,'1970','Taxation, Law and Legislation, Philippines','Available'),(50,'PC magazine Office 2007 solutions',3,'HF5548.4.M525 B34','Ballew, Joli','Wiley Publishing','2007','Business, Computer Programs','Available');
/*!40000 ALTER TABLE `readings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserved_rooms`
--

DROP TABLE IF EXISTS `reserved_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserved_rooms` (
  `reservedroomid` int(11) NOT NULL AUTO_INCREMENT,
  `roomid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `date` date NOT NULL,
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  PRIMARY KEY (`reservedroomid`),
  UNIQUE KEY `reservedroomid_UNIQUE` (`reservedroomid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserved_rooms`
--

LOCK TABLES `reserved_rooms` WRITE;
/*!40000 ALTER TABLE `reserved_rooms` DISABLE KEYS */;
INSERT INTO `reserved_rooms` VALUES (1,1,1,'2017-07-06','2017-07-06 09:00:00','2017-07-06 10:00:00'),(2,4,2,'2017-07-06','2017-07-06 14:00:00','2017-07-06 16:00:00'),(3,2,3,'2017-07-06','2017-07-06 10:00:00','2017-07-06 12:00:00');
/*!40000 ALTER TABLE `reserved_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `readingid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `review` longtext NOT NULL,
  `reviewdate` datetime NOT NULL,
  KEY `userid_idx` (`userid`),
  KEY `readingid_idx` (`readingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,2,'Great Book to Read!','2017-06-25 10:30:00'),(32,2,'Awesome!','2017-07-03 12:45:00'),(12,2,'Very useful!','2017-05-20 13:02:00');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `roomid` int(11) NOT NULL AUTO_INCREMENT,
  `roomname` varchar(45) NOT NULL,
  `status` enum('Reserved','Available') NOT NULL,
  PRIMARY KEY (`roomid`),
  UNIQUE KEY `roomid_UNIQUE` (`roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Room 1','Available'),(2,'Room 2','Available'),(3,'Room 3','Available'),(4,'Room 4','Available'),(5,'Room 5','Available');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `idnum` varchar(45) NOT NULL,
  `usertype` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `emailaddress` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `secretquestion` varchar(45) NOT NULL,
  `secretanswer` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  KEY `usertype_idx` (`usertype`),
  CONSTRAINT `usertype` FOREIGN KEY (`usertype`) REFERENCES `users_type` (`usertypeid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'11427949',1,'Denise Anne','Silva','deniseanne','monkey123!','denise_silva@dlsu.edu.ph','1997-09-30','What is your favorite color?','black'),(2,'11428785',1,'Danica','Sevilla','danicasevilla','tiger@12DS','danica_sevilla@dlsu.edu.ph','1998-04-13','Who is your crush?','Joseph Mapaglaban'),(3,'98026451',2,'Marnel','Peradilla','marnel123','a!bc480','marnel_peradilla@gmail.com','1981-05-12','What is your favorite color?','blue'),(4,'91398412',3,'Juan','Malonzo','juanmalonzo','1jm@@10','juan_malonzo@dlsu.edu.ph','1997-10-27','Who is your crush?','Isa'),(5,'97029106',4,'Maria Angela','Santos','angelasantos','cat23blue#','angela_santos@gmail.com','1991-08-09','What is your favorite food?','Adobo'),(6,'92068711',5,'Lorenzo','Diaz','lorenzodiaz','lo22abc','lorezo_diaz@gmail.com','1987-03-22','What is your favorite food?','Chocolate');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_type`
--

DROP TABLE IF EXISTS `users_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_type` (
  `usertypeid` int(11) NOT NULL AUTO_INCREMENT,
  `nametype` varchar(45) NOT NULL,
  PRIMARY KEY (`usertypeid`),
  UNIQUE KEY `usertypeid_UNIQUE` (`usertypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_type`
--

LOCK TABLES `users_type` WRITE;
/*!40000 ALTER TABLE `users_type` DISABLE KEYS */;
INSERT INTO `users_type` VALUES (1,'Student'),(2,'Faculty'),(3,'Library Manager'),(4,'Library Staff'),(5,'Administrator');
/*!40000 ALTER TABLE `users_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-06  7:15:07
