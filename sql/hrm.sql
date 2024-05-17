
INSERT INTO `applicant` VALUES (1,'asdfasdfasdf@gmail.com','asdf','asdf','97987987','http://localhost:8080/jobs/apply/1',_binary '\0',_binary '\0'),(2,'abc@gmail.com','asdf','asdf','35345','345345',_binary '',_binary '\0'),(3,'arif3hosain@gmail.com','Arif','Hosain','01754282387','http://localhost:8080/jobs/apply/1',_binary '\0',_binary ''),(4,'iqbal@gmail.com','hossain','iqbal','01768765456','http://localhost:8080/jobs/apply/1',_binary '',_binary '\0'),(5,'moon@gmail.com','moon','taha','9837459837495','http://localhost:8080/jobs/apply/1',_binary '\0',_binary '');

INSERT INTO `benefit` VALUES (1,50,'Monthly fee','Mobile Bill'),(2,100,NULL,'Transportation Fee'),(4,50,NULL,'House bill'),(5,90,NULL,'Health Insurance'),(6,50000,NULL,'Retirement Plans'),(7,80,NULL,'Paid Time Off');

INSERT INTO `department` VALUES (2,'Finance'),(3,'Human Resource Management'),(1,'Information Technology');

INSERT INTO `employee` VALUES (1,'arif3hosain@gmail.com','admin','2024-05-10','Hosain','01754282387',2,_binary '\0'),(2,'hr@gmail.com','hr','2024-05-22','hr','0980730495',3,_binary '\0'),(3,'emp@gmail.com','emp1','2024-05-22','emp1','990349823',1,_binary '\0'),(4,'shamim@gmail.com','Shamim','2024-05-21','Chowdhury','01754876543',1,_binary '\0'),(5,'emp2@gmail.com','emp2','2024-05-03','emp2','234234234',1,_binary ''),(6,'emp3@gmail.com','emp3','2024-05-02','emp3','234234234',1,_binary '\0');

INSERT INTO `goal` VALUES (2,_binary '\0','Develop Profile','2024-05-10',1),(3,_binary '\0','Document','2024-05-17',4),(4,_binary '\0','presentation','2024-05-22',4),(5,_binary '\0','lead team','2024-05-11',5);

INSERT INTO `job` VALUES (1,'IT','A job description contains the following components: job title, job purpose, job duties and responsibilities, required qualifications, preferred qualifications, and working conditions.',NULL,50000,'Software Engineer'),(6,'IT','need to code',NULL,5000,'Engineering in Java');

INSERT INTO `leave_requests` VALUES (3,'2024-05-23','2024-05-07',1,2,'asdf'),(4,'2024-05-24','2024-05-01',2,2,'asdf'),(5,'2024-05-11','2024-05-10',0,3,'emni'),(6,'2024-05-11','2024-05-10',0,3,'asdf'),(7,'2024-05-11','2024-05-10',0,3,'asdf'),(8,'2024-05-12','2024-05-11',0,3,'asdf'),(10,'2024-05-17','2024-05-17',1,5,'asdf'),(11,'2024-05-11','2024-05-11',0,1,'ASDF');

INSERT INTO `my_user` VALUES (1,'arif@innoweb.co','admin','Hosain','$2a$10$ZClQlba73/ZtAgVJKBiMvuOFgVApMCo83qnQthilNLWFb91OMKBEK',1),(2,'hr@gmail.com','hr','hr','$2a$10$9pOSFt72N6qIKjOV5VA3seiuGX2xX/bFcV81iuBVF9y6.kUh.LF2q',2),(3,'emp@gmail.com','emp1','emp1','$2a$10$PRFWxQ14uxIMAtR4Cv320uHNlTflLjZKiL7c8yYzB0IIcu5knay6i',3),(5,'emp2@gmail.com','emp2','emp2','$2a$10$yeKxqDjok5UEPm7dBI/oYOVFfEnmkOSa6Hs1lTNITRZRrUBLvi7/.',5),(6,'emp3@gmail.com','emp3','emp3','$2a$10$ql16thlaRYtef0C5ECu03.Hqe61KHurdk3F9sqvO1PchDVc9e2nxu',6);

INSERT INTO `performance_review` VALUES (1,'2024-05-11','asdf','Poor',1);

INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_HR'),(3,'ROLE_ADMIN');

INSERT INTO `users_roles` VALUES (1,3),(2,2),(3,1),(5,1),(6,1),(1,2);

