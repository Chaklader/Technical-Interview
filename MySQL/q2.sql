/*
2) Write a function to capitalize the first letter of a word in a given string;
Example: initcap(UNITED states Of AmERIca ) = United States Of America

author Julio Cesar Pescuite Gonçalves Batista - juliopescuite@gmail.com
version 1.0
since 2016-09-16
*/



DELIMITER ||  
  
CREATE FUNCTION `initcap`( str VARCHAR(128) ) RETURNS varchar(128) CHARSET latin1  

BEGIN  

  DECLARE mychar CHAR(1);  
  DECLARE mysub VARCHAR(128);  
  DECLARE counter INT DEFAULT 1;  
  DECLARE bool INT DEFAULT 1;  
  DECLARE punct CHAR(17) DEFAULT ' ()[]{},.-_!@;:?/';  
  SET mysub = LCASE( str );  


  WHILE counter < LENGTH( str ) DO  
     BEGIN  
       SET mychar = SUBSTRING( mysub, counter, 1 );  
       IF LOCATE( mychar, punct ) > 0 THEN  
        SET bool = 1;  
      ELSEIF bool=1 THEN  
        BEGIN  
          IF mychar >= 'a' AND mychar <= 'z' THEN  
             BEGIN  
               SET mysub = CONCAT(LEFT(mysub,counter-1),UCASE(mychar),SUBSTRING(mysub,counter+1));  
               SET bool = 0;  
             END;  
           ELSEIF mychar >= '0' AND mychar <= '9' THEN  
            SET bool = 0;  
          END IF;  
        END;  
      END IF;  
      SET counter = counter+1;  
    END;  
  END WHILE;  
  RETURN mysub;  
END ||  
  
DELIMITER ;  


select initcap('UNITED states Of AmERIca')


