/*
1) Write a query to rank order the following table in MySQL by votes, display the rank as one of the columns.
CREATE TABLE votes ( name CHAR(10), votes INT );
INSERT INTO votes VALUES
('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);

author Julio Cesar Pescuite Gonçalves Batista - juliopescuite@gmail.com
version 1.0
since 2016-09-16
*/


-- SELECT 0 INTO @r;
-- SELECT (@r:=@r+1) AS rank,votes FROM votes ORDER BY votes DESC;


SELECT name, votes, RANK() OVER (ORDER BY votes DESC) voting_rank FROM mydb.votes;