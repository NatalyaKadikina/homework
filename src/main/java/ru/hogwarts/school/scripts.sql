select * from student;
/*Получить всех студентов, возраст которых находится между 10 и 20 (можно подставить любые числа, главное, чтобы нижняя граница была меньше верхней).*/
select *
FROM student
where age between 20 AND 30;
/*Получить всех студентов, но отобразить только список их имен.*/
SELECT student.name
FROM student;
/*Получить всех студентов, у которых в имени присутствует буква «О» (или любая другая).*/
SELECT*
FROM student
WHERE name LIKE '%o%';
/*Получить всех студентов, у которых возраст меньше идентификатора.*/
SELECT *
FROM student
WHERE age<student.id;
/*Получить всех студентов упорядоченных по возрасту.*/
SELECT *
FROM student
ORDER BY age;

