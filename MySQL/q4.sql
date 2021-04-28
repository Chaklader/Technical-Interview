/*

4) I have a table for bugs from a bug tracking software; let’s call the table “bugs”. The table has four 
columns (id, open_date, close_date, severity). On any given day a bug is open if the open_date is on or before 
that day and close_date is after that day. For example, a bug is open on “2012-01-01”, if it’s created on or before “2012-01-01” 
and closed on or after “2012-01-02”. I want an SQL to show the number of bugs open each day for a range of dates. 
Hint: There are bugs that were never closed.

author Julio Cesar Pescuite Gonçalves Batista - juliopescuite@gmail.com
version 1.0
since 2016-09-16
*/

CREATE TABLE bugs(id INT, open_date DATE, close_date DATE, severity CHAR);

INSERT INTO bugs VALUES(1,STR_TO_DATE('01/01/2012', '%m/%d/%Y'),STR_TO_DATE('02/01/2012', '%m/%d/%Y'),'H');
INSERT INTO bugs VALUES(2,STR_TO_DATE('02/01/2012', '%m/%d/%Y'),STR_TO_DATE('03/01/2012', '%m/%d/%Y'),'M');
INSERT INTO bugs VALUES(3,STR_TO_DATE('02/01/2012', '%m/%d/%Y'),STR_TO_DATE('03/01/2012', '%m/%d/%Y'),'M');
INSERT INTO bugs VALUES(4,STR_TO_DATE('04/01/2012', '%m/%d/%Y'),null,'L');
INSERT INTO bugs VALUES(5,STR_TO_DATE('06/01/2012', '%m/%d/%Y'),null,'L');


SELECT b.open_date,count(b.id) AS totalbugs FROM bugs b WHERE b.open_date BETWEEN '01/01/2012' AND curdate()
GROUP BY b.open_date
ORDER BY b.open_date