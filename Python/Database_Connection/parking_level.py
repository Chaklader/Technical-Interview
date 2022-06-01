import MySQLdb
import json

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_model'
csv_file_location = "C:/Programming/SQL Query/parking data/all_parking_data.csv"
parking_level= open(csv_file_location, 'r')
all_data = []
parking_class = []
capacity_matrix = []
parking_location = []


for row in parking_level:
	all_data.append(row)

for row in all_data:

	if (all_data.index(row) == 0):
		first_row = row.split(';')
		parking_class = first_row[1:len(first_row)-2]
	else:
		other_row =  row.split(';')
		parking_location.append(other_row[0])
		other_row = other_row[1:len(other_row)-2]
		capacity_matrix.append(other_row)

conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 

cur.execute('TRUNCATE TABLE parking_level')
conn.commit()

def parking_level(parking_site_id, parking_level_id, parking_area_id, parking_class_id, number_of_parking_spaces ):

	# cur.execute("""INSERT INTO parking_level (parking_site_id, parking_level_id, parking_area_id, parking_class_id, number_of_parking_spaces) VALUES 
	# 		   		( %(parking_site_id)s, %(parking_level_id)s, %(parking_area_id)s, %(parking_class_id)s, %(number_of_parking_spaces)s ) ;""" % 

	# 					{
	# 						'parking_site_id': parking_site_id, 
	# 						'parking_level_id': parking_level_id, 
	# 						'parking_area_id':  parking_area_id, 
	# 						'parking_class_id': parking_class_id, 
	# 						'number_of_parking_spaces': number_of_parking_spaces 
	# 					}
	# 			)

	sql_statement = 'INSERT INTO parking_level (parking_site_id, parking_level_id, parking_area_id, parking_class_id, number_of_parking_spaces) VALUES (%s, %s, %s, %s, %s);' 
	cur.execute( sql_statement , (parking_site_id, parking_level_id, parking_area_id, parking_class_id, number_of_parking_spaces))
	conn.commit() 

	

for row in range(len(parking_location)):

	parking_site_id = row

	for column in range(len(parking_class)):

		parking_class_id = column 
		respective_capacity = int(capacity_matrix[parking_site_id][parking_class_id])
		parking_area = '1B'

		parking_level( parking_site_id+1, 1, parking_area, parking_class_id+1 ,  respective_capacity)
		
		parking_class_id = -1
	parking_site_id = -1

conn.close()


# parking_site_id
# parking_level_id
# parking_area_id
# parking_class_id
# number_of_parking_spaces

