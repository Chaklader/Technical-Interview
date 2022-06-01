import MySQLdb
import json

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_test'
json_file_location = 'C:/Programming/SQL Query/bus route/CATS_route.json'

with open(json_file_location) as data_file:    
    data = json.load(data_file)

data = data['features']
bus_stops = {}

for row in data:
	if type(row['properties']['bus_stop']) == int:
		bus_stops[ row['properties']['bus_stop'] ] = str(row['geometry']['coordinates'][0])+' '+ str(row['geometry']['coordinates'][1])

conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 
cur.execute('TRUNCATE TABLE platform')
conn.commit()

location_dictionary = {}

cur.execute("SELECT *, X(coordinate), Y(coordinate) FROM ITPA_database_test.location;")
result_set = cur.fetchall()

for row in result_set:
    location_dictionary[str(row[3])+' '+ str(row[4])] = int(row[0])


for row in bus_stops:

	for row_in in bus_stops_dictionary:

		if row_in == row:

			cur.execute("""INSERT INTO platform (network_id, start_location_id, end_location_id, length , shape)  VALUES ( 
            														%(network_id)s, 
            														%(start_location_id)s,
            														%(end_location_id)s,
            														%(length)s,
            														(GeomFromText('LINESTRING(%(shape)s)')) ) ;""" % 
            														{ 
            														'network_id': 1, 
            														'start_location_id': segment_start_id,
            														'end_location_id': segment_end_id,
            														'length': 10,
            														'shape': row 
            													} )
            conn.commit()








###########################################################################################################################








seg_array = []
temp = []

bus_stops = []
intersections = []

for row in data:
	temp.append(row['geometry']['coordinates'])
	if (row['properties']['intersection'] == 'yes' or type(row['properties']['bus_stop']) == int):

		if row['properties']['intersection'] == 'yes':
			intersections.append(row['geometry']['coordinates'])

		if type(row['properties']['bus_stop']) == int:
			bus_stops.append(row['geometry']['coordinates'])

		if len(temp) >1:
			seg_array.append(temp)
		temp = []
		temp.append(row['geometry']['coordinates'])
        
segment_strings = []
temp_string = ""
for row in seg_array:
	for row_inside in row:
		temp_string = temp_string + str(row_inside[0])+" "+ str(row_inside[1])+ ","
	segment_strings.append(temp_string.strip(','))
	temp_string = ""

# bus stop coordinates as string 
bus_stop_strings = []
temp_bus_strings = ""

for row in bus_stops:
	temp_bus_strings = temp_bus_strings + str(row[0])+" "+ str(row[1])
	bus_stop_strings.append(temp_bus_strings)
	temp_bus_strings = ""

# intersection corodinates as string 
inter_section_strings = []
temp_intersection_strings = ""

for row in intersections:
	temp_intersection_strings = temp_intersection_strings + str(row[0])+" "+ str(row[1])
	inter_section_strings.append(temp_intersection_strings)
	temp_intersection_strings = ""
    
segment_edge_strings = []

for row in segment_strings:
    row = row.split(',')
    segment_edge_strings.append( str(row[0])+','+ str(row[len(row) -1]) )
    
    
conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 
cur.execute('TRUNCATE TABLE segment')
conn.commit()


segment_dictionary = {}

cur.execute("SELECT *, X(coordinate), Y(coordinate) FROM ITPA_database_test.location;")
result_set = cur.fetchall()

for row in result_set:
    segment_dictionary[str(row[3])+' '+ str(row[4])] = int(row[0])

for row in segment_strings:

	    copy_of_row = row
	    copy_of_row = copy_of_row.split(',')
	    copy_of_row_length = len(copy_of_row)
	    segment_start = copy_of_row[0]
	    segment_end = copy_of_row[copy_of_row_length-1]

	    for row_inside in segment_dictionary:
		    if row_inside == segment_start:
			    segment_start_id = segment_dictionary[row_inside]
		    if row_inside == segment_end:
			    segment_end_id = segment_dictionary[row_inside]
		    print row

		    # ur.execute("INSERT INTO song (title) VALUES (%s)", song)
            cur.execute("""INSERT INTO segment (network_id, start_location_id, end_location_id, length , shape)  VALUES ( 
            														%(network_id)s, 
            														%(start_location_id)s,
            														%(end_location_id)s,
            														%(length)s,
            														(GeomFromText('LINESTRING(%(shape)s)')) ) ;""" % 
            														{ 
            														'network_id': 1, 
            														'start_location_id': segment_start_id,
            														'end_location_id': segment_end_id,
            														'length': 10,
            														'shape': row 
            													} )
            conn.commit()
            # cur.execute('INSERT INTO segment (network_id, start_location_id, end_location_id, length , shape) VALUES (%s, %s, %s, %s, GeomFromText('LINESTRING(%s)'))',(1,segment_start_id, segment_end_id, 10, row ) )
	    
conn.close()

