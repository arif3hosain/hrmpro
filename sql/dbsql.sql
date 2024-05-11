-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: hrm
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `resume_url` varchar(500) NOT NULL,
  `deny` bit(1) DEFAULT NULL,
  `hired` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6iduje2h6ggdlnmevw2mvolfx` (`email`),
  UNIQUE KEY `UK_s4gkylihid0qmetrspjnnvy4h` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` VALUES (1,'asdfasdfasdf@gmail.com','asdf','asdf','97987987','http://localhost:8080/jobs/apply/1',_binary '\0',_binary '\0'),(2,'abc@gmail.com','asdf','asdf','35345','345345',_binary '',_binary '\0'),(3,'arif3hosain@gmail.com','Arif','Hosain','01754282387','http://localhost:8080/jobs/apply/1',_binary '\0',_binary ''),(4,'iqbal@gmail.com','hossain','iqbal','01768765456','http://localhost:8080/jobs/apply/1',_binary '',_binary '\0'),(5,'moon@gmail.com','moon','taha','9837459837495','http://localhost:8080/jobs/apply/1',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `benefit`
--

DROP TABLE IF EXISTS `benefit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benefit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benefit`
--

LOCK TABLES `benefit` WRITE;
/*!40000 ALTER TABLE `benefit` DISABLE KEYS */;
INSERT INTO `benefit` VALUES (1,50,'Monthly fee','Mobile Bill'),(2,100,NULL,'Transportation Fee'),(4,50,NULL,'House bill'),(5,90,NULL,'Health Insurance'),(6,50000,NULL,'Retirement Plans'),(7,80,NULL,'Paid Time Off');
/*!40000 ALTER TABLE `benefit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compensation`
--

DROP TABLE IF EXISTS `compensation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compensation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `allowances` double DEFAULT NULL,
  `base_salary` double DEFAULT NULL,
  `bonus` double DEFAULT NULL,
  `commission` double DEFAULT NULL,
  `overtime_pay` double DEFAULT NULL,
  `employee` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3qcuo4986ldgxxftvw2ns4bmb` (`employee`),
  CONSTRAINT `FK3qcuo4986ldgxxftvw2ns4bmb` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compensation`
--

LOCK TABLES `compensation` WRITE;
/*!40000 ALTER TABLE `compensation` DISABLE KEYS */;
INSERT INTO `compensation` VALUES (2,5,55,55,55,5,1);
/*!40000 ALTER TABLE `compensation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1t68827l97cwyxo9r1u6t4p7d` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,'Finance'),(3,'Human Resource Management'),(1,'Information Technology');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `join_date` date NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `department` bigint DEFAULT NULL,
  `department_head` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkxx4wtsgsdt16iix2pso0k126` (`department`),
  CONSTRAINT `FKkxx4wtsgsdt16iix2pso0k126` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'arif3hosain@gmail.com','admin','2024-05-10','Hosain','01754282387',2,_binary '\0'),(2,'hr@gmail.com','hr','2024-05-22','hr','0980730495',3,_binary '\0'),(3,'emp@gmail.com','emp1','2024-05-22','emp1','990349823',1,_binary '\0'),(4,'shamim@gmail.com','Shamim','2024-05-21','Chowdhury','01754876543',1,_binary '\0'),(5,'emp2@gmail.com','emp2','2024-05-03','emp2','234234234',1,_binary ''),(6,'emp3@gmail.com','emp3','2024-05-02','emp3','234234234',1,_binary '\0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal`
--

DROP TABLE IF EXISTS `goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completed` bit(1) DEFAULT NULL,
  `goal_description` varchar(500) DEFAULT NULL,
  `target_date` date DEFAULT NULL,
  `employee` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ffmcv7rmmnnmdl21ihhti6s` (`employee`),
  CONSTRAINT `FK2ffmcv7rmmnnmdl21ihhti6s` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal`
--

LOCK TABLES `goal` WRITE;
/*!40000 ALTER TABLE `goal` DISABLE KEYS */;
INSERT INTO `goal` VALUES (2,_binary '\0','Develop Profile','2024-05-10',1),(3,_binary '\0','Document','2024-05-17',4),(4,_binary '\0','presentation','2024-05-22',4),(5,_binary '\0','lead team','2024-05-11',5);
/*!40000 ALTER TABLE `goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  `description` varchar(5000) NOT NULL,
  `posted_date` date DEFAULT NULL,
  `salary` double NOT NULL,
  `title` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'IT','A job description contains the following components: job title, job purpose, job duties and responsibilities, required qualifications, preferred qualifications, and working conditions.',NULL,50000,'Software Engineer'),(6,'IT','need to code',NULL,5000,'Engineering in Java');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_applicant`
--

DROP TABLE IF EXISTS `job_applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_applicant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `applicant_id` bigint DEFAULT NULL,
  `job_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3njcctaabjvtwyucpc45wsolb` (`applicant_id`),
  KEY `FKgjecqbj3prcqp7bq8ri1spk7w` (`job_id`),
  CONSTRAINT `FK3njcctaabjvtwyucpc45wsolb` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`),
  CONSTRAINT `FKgjecqbj3prcqp7bq8ri1spk7w` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_applicant`
--

LOCK TABLES `job_applicant` WRITE;
/*!40000 ALTER TABLE `job_applicant` DISABLE KEYS */;
INSERT INTO `job_applicant` VALUES (1,2,1),(2,3,1),(3,4,1),(4,5,1);
/*!40000 ALTER TABLE `job_applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_requests`
--

DROP TABLE IF EXISTS `leave_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_requests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `employee` bigint DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqn7wxi2pgfsc7el9ip8jk4v6k` (`employee`),
  CONSTRAINT `FKqn7wxi2pgfsc7el9ip8jk4v6k` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`),
  CONSTRAINT `leave_requests_chk_1` CHECK ((`status` between 0 and 2))
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_requests`
--

LOCK TABLES `leave_requests` WRITE;
/*!40000 ALTER TABLE `leave_requests` DISABLE KEYS */;
INSERT INTO `leave_requests` VALUES (3,'2024-05-23','2024-05-07',1,2,'asdf'),(4,'2024-05-24','2024-05-01',2,2,'asdf'),(5,'2024-05-11','2024-05-10',0,3,'emni'),(6,'2024-05-11','2024-05-10',0,3,'asdf'),(7,'2024-05-11','2024-05-10',0,3,'asdf'),(8,'2024-05-12','2024-05-11',0,3,'asdf'),(10,'2024-05-17','2024-05-17',1,5,'asdf'),(11,'2024-05-11','2024-05-11',0,1,'ASDF');
/*!40000 ALTER TABLE `leave_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_user`
--

DROP TABLE IF EXISTS `my_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKoojv0tfv8mcxgq60bbibie6cm` (`email`),
  UNIQUE KEY `UK_d78yd3tjn3n8amc18i09jn622` (`employee_id`),
  CONSTRAINT `FKdvqg5x6kk5t44auktancetnt2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_user`
--

LOCK TABLES `my_user` WRITE;
/*!40000 ALTER TABLE `my_user` DISABLE KEYS */;
INSERT INTO `my_user` VALUES (1,'arif@innoweb.co','admin','Hosain','$2a$10$ZClQlba73/ZtAgVJKBiMvuOFgVApMCo83qnQthilNLWFb91OMKBEK',1),(2,'hr@gmail.com','hr','hr','$2a$10$9pOSFt72N6qIKjOV5VA3seiuGX2xX/bFcV81iuBVF9y6.kUh.LF2q',2),(3,'emp@gmail.com','emp1','emp1','$2a$10$PRFWxQ14uxIMAtR4Cv320uHNlTflLjZKiL7c8yYzB0IIcu5knay6i',3),(5,'emp2@gmail.com','emp2','emp2','$2a$10$yeKxqDjok5UEPm7dBI/oYOVFfEnmkOSa6Hs1lTNITRZRrUBLvi7/.',5),(6,'emp3@gmail.com','emp3','emp3','$2a$10$ql16thlaRYtef0C5ECu03.Hqe61KHurdk3F9sqvO1PchDVc9e2nxu',6);
/*!40000 ALTER TABLE `my_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `deductions` double DEFAULT NULL,
  `gross_salary` double NOT NULL,
  `net_salary` double NOT NULL,
  `pay_period_end_date` date NOT NULL,
  `pay_period_start_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_review`
--

DROP TABLE IF EXISTS `performance_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance_review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `review_date` date DEFAULT NULL,
  `review_text` varchar(255) DEFAULT NULL,
  `performance` enum('Better','Excellent','Extraordinary','Fair','Good','Poor') DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9159yuocyhexftv11wmay20cg` (`employee_id`),
  CONSTRAINT `FK9159yuocyhexftv11wmay20cg` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_review`
--

LOCK TABLES `performance_review` WRITE;
/*!40000 ALTER TABLE `performance_review` DISABLE KEYS */;
INSERT INTO `performance_review` VALUES (1,'2024-05-11','asdf','Poor',1);
/*!40000 ALTER TABLE `performance_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_HR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKn5shxud2ny89xummw7ge6oj7o` (`user_id`),
  CONSTRAINT `FKn5shxud2ny89xummw7ge6oj7o` FOREIGN KEY (`user_id`) REFERENCES `my_user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,3),(2,2),(3,1),(5,1),(6,1),(1,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-11 14:27:18
