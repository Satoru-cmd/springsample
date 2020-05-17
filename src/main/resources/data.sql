INSERT INTO employee(employee_id, employee_name, age)
VALUES(1,'山田太郎', 30);
INSERT INTO employee(employee_id, employee_name, age)
VALUES(2,'佐藤工事', 40);

/*ユーザーマスターのデータ（アドミン）*/
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('yamada@xxx.co.jp', 'pass', '山田太郎', '1990-01-01', 20, false, 'ROLE_ADMIN');
/*ユーザーマスターのデータ（一般）*/
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('tamura@xxx.co.jp', 'pass', '田村正和', '1998-12-24', 31, false, 'ROLE_GENERAL');