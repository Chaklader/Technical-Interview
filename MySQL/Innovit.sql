

/*
	1. change the id column to the oldId 
	2. add a new column named newId that provides the sequential ranks 
	based on the oldId 
*/

ALTER TABLE itemIds CHANGE COLUMN id oldId int;
ALTER TABLE itemIds add newId int;

DROP TABLE IF EXISTS test;
CREATE TEMPORARY TABLE test ( oldId int, newId int);


INSERT INTO test ( oldId, newId )
	SELECT oldId,
	  (SELECT count(*) + 1 FROM itemIds item
	  WHERE item.oldId < itemIds.oldId) as newId
	FROM itemIds;

UPDATE itemIds INNER join test ON itemIds.oldId = test.oldId SET itemIds.newId = test.newId;