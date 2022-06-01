# Time:  O(n^2)
# Space: O(n)
# 
# The Employee table holds all employees. Every employee has an Id, and there is also a column for the department Id.
 
# +----+-------+--------+--------------+
# | Id | Name  | Salary | DepartmentId |
# +----+-------+--------+--------------+
# | 1  | Joe   | 70000  | 1            |
# | 2  | Henry | 80000  | 2            |
# | 3  | Sam   | 60000  | 2            |
# | 4  | Max   | 90000  | 1            |
# | 5  | Janet | 69000  | 1            |
# | 6  | Randy | 85000  | 1            |
# +----+-------+--------+--------------+


# The Department table holds all departments of the company.
 
# +----+----------+
# | Id | Name     |
# +----+----------+
# | 1  | IT       |
# | 2  | Sales    |
# +----+----------+


# Write a SQL query to find employees who earn the top three salaries in 
# each of the department. For the above tables, your SQL query should return 
# the following rows.
 
# +------------+----------+--------+
# | Department | Employee | Salary |
# +------------+----------+--------+
# | IT         | Max      | 90000  |
# | IT         | Randy    | 85000  |
# | IT         | Joe      | 70000  |
# | Sales      | Henry    | 80000  |
# | Sales      | Sam      | 60000  |
# +------------+----------+--------+


# Write your MySQL query statement below
SELECT D.Name AS Department, E.Name AS Employee, E.Salary AS Salary 
FROM Employee E INNER JOIN Department D ON E.DepartmentId = D.Id 
WHERE 
(SELECT COUNT(DISTINCT(Salary)) FROM Employee 
  WHERE DepartmentId = E.DepartmentId AND Salary > E.Salary) < 3
ORDER by E.DepartmentId, E.Salary DESC;


# =======================================================================
-- +----+-------+--------+--------------+-------------------------------+
-- | Id | Name  | Sa1ary | DepartmentId | Count employees who earn more |
-- +----+-------+--------+--------------+-------------------------------+
-- |  1 | Joe   |  70000 |            1 |    2                          |
-- |  2 | Henry |  80000 |            2 |    0                          |
-- |  3 | Sam   |  60000 |            2 |    1                          | 
-- |  4 | Max   |  90000 |            1 |    0                          |
-- |  5 | Janet |  69000 |            1 |    3                          |
-- |  6 | Randy |  85000 |            1 |    1                          |
-- +----+-------+--------+--------------+-------------------------------+

/* SELECT 
      D.Name AS Department, 
      E.Name AS Employee, 
      E.Salary AS Salary,
      Count(E2.Salary) as Count_employees_who_earn_more
FROM Employee E 
INNER JOIN Department D 
ON E.DepartmentId = D.Id 
LEFT JOIN Employee E2 ON 
    e2.DepartmentId = E.DepartmentId
    AND E2.Salary > E.Salary


GROUP BY  D.Name , 
      E.Name , 
      E.Salary */











# SQL FOR CREATING EmployeeDepartment databse 

# drop database EmployeeDepartment; 
CREATE DATABASE EmployeeDepartment;
USE  EmployeeDepartment;


CREATE TABLE Department(

    Id                 INT     NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    Name                VARCHAR(50)         NOT NULL
);


CREATE TABLE Employee(

    Id                 INT     NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    Name                VARCHAR(50)         NOT NULL, 
    Salary                INT         NOT NULL, 
    DepartmentId              INT         NOT NULL,
    CONSTRAINT Employee_fk_Department 
        FOREIGN KEY (DepartmentId) REFERENCES  Department(Id) 
);


INSERT INTO Department(`Name`) values('IT');
INSERT INTO Department(`Name`) values('SALES'); 



INSERT INTO Employee(`Name`, `Salary`, `DepartmentId`) values('Joe', 70000, 1); 
INSERT INTO Employee(`Name`, `Salary`, `DepartmentId`) values('Henry', 80000, 2); 
INSERT INTO Employee(`Name`, `Salary`, `DepartmentId`) values('Sam', 60000, 2); 
INSERT INTO Employee(`Name`, `Salary`, `DepartmentId`) values('Max', 90000, 1); 
INSERT INTO Employee(`Name`, `Salary`, `DepartmentId`) values('Janet', 69000, 1); 
INSERT INTO Employee(`Name`, `Salary`, `DepartmentId`) values('Randy', 85000, 1); 



SELECT * FROM Department;
SELECT * FROM Employee;

