import MySQLdb
import json

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_model'
csv_file_location = "C:/Programming/SQL Query/parking data/all_parking_data.csv"
mmc_json_file_location = 'C:/Programming/SQL Query/parking data/mmc - location-shape parking.json'
bbc_json_file_location = 'C:/Programming/SQL Query/parking data/bbc-location shape parking.json'

parking_site = open(csv_file_location, 'r')
all_data = []
parking_class = []
capacity_matrix = []
parking_location = []

data_mmc = []
data_bbc = []

for row in parking_site:
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

with open(mmc_json_file_location) as data_file_mmc:    
    data_mmc = json.load(data_file_mmc)

data = data_mmc['features']
dictionary = {}

for row in data:
	    name = row['name']
	    
	    for shape_coordinates in row["geometry"]["shape_coordinates"]:
			shape = ''
			for shape_coordinate in shape_coordinates:
			    shape = shape + str(shape_coordinate[0])+' '+  str(shape_coordinate[1]) + ','
			dictionary[name] = shape.strip(',')
	    name = ''

parking_site_matrix = [[0 for x in xrange(7)] for x in xrange(58)]
default_shape = '0 0,10 0,10 10,0 10,0 0'

for u in parking_site_matrix:
	index = parking_site_matrix.index(u)
	parking_site_matrix[index][5] = default_shape

for row in parking_location:
    index = parking_location.index(row)

    parking_site_matrix[index][0] =  1
    parking_site_matrix[index][1] = index+1
    parking_site_matrix[index][2] = index+1
    parking_site_matrix[index][3] = row
    parking_site_matrix[index][4] = row+' '+ 'Parking Place'
    parking_site_matrix[index][6] = 1

for dictionary_value in dictionary:

    for row in parking_site_matrix:
        index = parking_site_matrix.index(row)
        if ( dictionary_value.replace(' ','') == parking_site_matrix[index][3].replace(' ','')):
            parking_site_matrix[index][5] = dictionary[dictionary_value]

 #BBC 

with open(bbc_json_file_location) as data_file_bbc:
	data_bbc = json.load(data_file_bbc)

data_n = data_bbc['features']
dictionary_n = {}

for row in data_n:
	    name = row['name']
	    
	    for shape_coordinates in row["geometry"]["shape_coordinates"]:
			shape = ''
			for shape_coordinate in shape_coordinates:
			    shape = shape + str(shape_coordinate[0])+' '+  str(shape_coordinate[1]) + ','
			# print name+': '+shape + '\n'
			dictionary_n[name] = shape.strip(',')
	    name = ''

for dictionary_value in dictionary_n:

    for row in parking_site_matrix:
        index = parking_site_matrix.index(row)
        if ( dictionary_value.replace(' ','') == parking_site_matrix[index][3].replace(' ','')):
            parking_site_matrix[index][5] = dictionary_n[dictionary_value]


 # end of bbc 

conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 

cur.execute('TRUNCATE TABLE parking_site')
conn.commit()

def parking_sites(network_id, location_id, parking_site_type_id, identifier, description, shape,  total_level ):
	cur.execute("""INSERT INTO parking_site ( network_id,location_id,parking_site_type_id,identifier,description,shape,total_level )  VALUES (  %(network_id)s,
																																				%(location_id)s,
																																				%(parking_site_type_id)s,
																																				'%(identifier)s',
																																				'%(description)s',
																																				(GeomFromText('POLYGON((%(shape)s))')),
																																				%(total_level)s );""" % 
		                                                                                                                                         {
			                                                                                                                                          'network_id': network_id, 
			                                                                                                                                          'location_id': location_id,
			                                                                                                                                          'parking_site_type_id': parking_site_type_id, 
			                                                                                                                                          'identifier': identifier, 
			                                                                                                                                          'description': description,
			                                                                                                                                          'shape': shape,
			                                                                                                                                          'total_level': total_level
		                                                                                                                                         }
	                                                                                                                                         )
	conn.commit()

for row in parking_site_matrix:
	parking_sites( row[0], row[1], row[2], row[3], row[4], row[5], row[6])

conn.close()

