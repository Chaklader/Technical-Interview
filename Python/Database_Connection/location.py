import MySQLdb
import json

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_test'
csv_file_location = "C:/Programming/SQL Query/parking data/all_parking_data.csv"
json_file_location = 'C:/Programming/SQL Query/parking data/mmc - location-shape parking.json'

location = open(csv_file_location, 'r')
all_data = []
parking_class = []
capacity_matrix = []
parking_location = []

for row in location:
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


with open(json_file_location) as data_file:    
    data = json.load(data_file)

data = data['features']

dictionary = {}

for row in data:
    name = ''
    name = name + row['name']
    location = ''
    location = location + '%(longitude)s %(latitude)s'%{'longitude':row["geometry"]["location_coordinate"][0], 'latitude': row["geometry"]["location_coordinate"][1]}
    dictionary[name] = location.strip(',') 

location_matrix = [[0 for x in xrange(2)] for x in xrange(58)]

for row in location_matrix:
	index = location_matrix.index(row)
	location_matrix[index][1] =  '0 0'

for row in parking_location:

	    index = parking_location.index(row)
	    location_matrix[index][0] = 1
	    
	    for dictionary_row in dictionary:
		    if (dictionary_row.replace(' ','')== row.replace(' ','')):
			    location_matrix[index][1] = dictionary[dictionary_row]
		  

conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 

cur.execute('TRUNCATE TABLE location')
conn.commit()

for row in location_matrix:

	location_string = row[1]

	# cur.execute("""INSERT INTO location ( network_id, coordinate )  VALUES ( %(network_id)s, POINT(%(lon)s, %(lat)s) );""" % { 'network_id': 1, 'lon': longitude, 'lat': latitude } )
	cur.execute("""INSERT INTO location ( network_id, coordinate )  VALUES ( %(network_id)s, (GeomFromText('POINT(%(coordinate)s)')) );""" % { 'network_id': 1, 'coordinate': location_string } )

                                                                                                                                    
	conn.commit()

conn.close()
