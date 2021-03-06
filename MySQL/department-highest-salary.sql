# Time:  O(n^2)
# Space: O(n)
 
# The Employee table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.
 
# +----+-------+--------+--------------+
# | Id | Name  | Salary | DepartmentId |
# +----+-------+--------+--------------+
# | 1  | Joe   | 70000  | 1            |
# | 2  | Henry | 80000  | 2            |
# | 3  | Sam   | 60000  | 2            |
# | 4  | Max   | 90000  | 1            |
# +----+-------+--------+--------------+


# The Department table holds all departments of the company. 
# +----+----------+
# | Id | Name     |
# +----+----------+
# | 1  | IT       |
# | 2  | Sales    |
# +----+----------+


# Write a SQL query to find employees who have the highest salary in each of the departments. For the above tables, Max has the highest salary in the IT department and Henry has the highest salary in the Sales department. 

# +------------+----------+--------+
# | Department | Employee | Salary |
# +------------+----------+--------+
# | IT         | Max      | 90000  |
# | Sales      | Henry    | 80000  |
# +------------+----------+--------+



# Write your MySQL query statement below
SELECT d.Department AS Department, e.Name AS Employee, d.Salary AS Salary
FROM (SELECT Department.Id AS DepartmentId, Department.Name AS Department, emp.Salary AS Salary
        FROM Department LEFT JOIN 
        (SELECT DepartmentId, MAX(Salary) AS Salary FROM Employee GROUP BY DepartmentId) emp
        ON Department.Id = emp.DepartmentId) d 
      INNER JOIN Employee e 
      ON e.DepartmentId = d.DepartmentId and e.Salary = d.Salary


/*SELECT d.Department AS Department, e.Name AS Employee, d.Salary AS Salary
FROM (SELECT Department.Id AS DepartmentId, Department.Name AS Department, emp.Salary AS Salary
        FROM Department JOIN (SELECT DepartmentId, MAX(Salary) AS Salary FROM Employee GROUP BY DepartmentId) emp
        ON Department.Id = emp.DepartmentId) d 
      JOIN Employee e 
      ON e.DepartmentId = d.DepartmentId and e.Salary = d.Salary*/




# Write your MySQL query statement below
SELECT Department.Name AS Department, Employee.Name AS Employee, Employee.Salary AS Salary
FROM Department LEFT JOIN Employee ON Employee.DepartmentId = Department.Id
WHERE Employee.Salary IN (SELECT MAX(e.Salary) FROM Employee e WHERE e.DepartmentId = Employee.DepartmentId)

/*SELECT Department.Name AS Department, Employee.Name AS Employee, Employee.Salary AS Salary
FROM Department JOIN Employee ON Employee.DepartmentId = Department.Id
WHERE Employee.Salary IN (SELECT MAX(e.Salary) FROM Employee e WHERE e.DepartmentId = Employee.DepartmentId)*/










# SQL FOR CREATING EmployeeDepartment databse 

# drop database EmployeeDepartment; 
CREATE DATABASE EmployeeDepartment;
USE EmployeeDepartment;


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


