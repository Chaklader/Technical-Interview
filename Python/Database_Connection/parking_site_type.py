import MySQLdb

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_model'
csv_file_location = "C:/Programming/SQL Query/parking data/all_parking_data.csv"

parking_site_type = open(csv_file_location, 'r')
all_data = []
parking_class = []
capacity_matrix = []
parking_location = []

for row in parking_site_type:
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

cur.execute('TRUNCATE TABLE parking_site_type')
conn.commit()

for row in parking_location:
	
	parking_site_name = row 
	cur.execute("""INSERT INTO parking_site_type (identifier, description) VALUES (%(identifier)s, %(description)s);""", {'identifier': parking_site_name, 'description': parking_site_name+' '+Parking Place'} )
	conn.commit()

conn.close()

