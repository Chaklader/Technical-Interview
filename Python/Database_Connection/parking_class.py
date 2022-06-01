import MySQLdb

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_model'
# database_name = 'ITPA_database_test'
csv_file_location = "C:/Programming/SQL Query/parking data/all_parking_data.csv"

parking_file = open(csv_file_location, 'r')
all_data = []
parking_class = []
capacity_matrix = []
parking_location = []

for line in parking_file:
	all_data.append(line)

for value in all_data:
	if (all_data.index(value) == 0):
		t = value.split(';')
		parking_class = t[1:len(t)-2]
	else:
		x = value.split(';')
		parking_location.append(x[0])
		x = x[1:len(x)-2]
		capacity_matrix.append(x)

conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 

cur.execute('TRUNCATE TABLE parking_class')
conn.commit()

parking_class_description = []

for v in parking_class:
	description = (v + 'park_place').lower()
	parking_class_description.append(description)

for row in parking_class :

	cur.execute("""INSERT INTO parking_class (identifier, description) VALUES (%(identifier)s, %(description)s);""", {'identifier': row, 'description': parking_class_description[parking_class.index(row)]} )
	conn.commit()

conn.close()

# mat = [[0 for x in xrange(len(p_class))] for x in xrange(len(parking))]
