/*
3) Write a procedure in MySQL to split a column into rows using a delimiter.
CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag');
For (2), example rows would look like >> “3, white”, “3, Snow” …

author Julio Cesar Pescuite Gonçalves Batista - juliopescuite@gmail.com
version 1.0
since 2016-09-16
*/


CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag');


DELIMITER $$

DROP PROCEDURE IF EXISTS expand_rows $$
CREATE PROCEDURE expand_rows(bound VARCHAR(255))

  BEGIN

    DECLARE id INT DEFAULT 0;
    DECLARE value TEXT;
    DECLARE occurences INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE splitted_value VARCHAR(255);
    DECLARE done INT DEFAULT 0;
    DECLARE cur1 CURSOR FOR SELECT sometbl.id, sometbl.name
                                         FROM sometbl
                                         WHERE sometbl.id != '';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    DROP TEMPORARY TABLE IF EXISTS table2;
    CREATE TEMPORARY TABLE table2(
    `id` VARCHAR(255) NULL,
    `value` VARCHAR(255) NOT NULL
    ) ENGINE=Memory;

    OPEN cur1;
      read_loop: LOOP
        FETCH cur1 INTO id, value;
        IF done THEN
          LEAVE read_loop;
        END IF;

        SET occurences = (SELECT LENGTH(value)
                                 - LENGTH(REPLACE(value, bound, ''))
                                 +1);
        SET i=1;
        WHILE i <= occurences DO
          SET splitted_value =
          (SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(value, bound, i),
          LENGTH(SUBSTRING_INDEX(value, bound, i - 1)) + 1), bound, ''));

          INSERT INTO table2 VALUES (id, splitted_value);
          SET i = i + 1;

        END WHILE;
      END LOOP;

      SELECT * FROM table2;
    CLOSE cur1;
  END; $$  
CALL expand_rows('|');
