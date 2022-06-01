import MySQLdb
import json

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_test'
json_file_location = 'C:/Programming/SQL Query/bus route/CATS_route.json'
_network_id_ = 1

with open(json_file_location) as data_file:    
    data = json.load(data_file)

data = data['features']

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

cur.execute('TRUNCATE TABLE location')
conn.commit()

def location(network_id, location_array ):
	_network_id = network_id
	_location_array = location_array
	for row in _location_array:
		cur.execute("""INSERT INTO location ( network_id, coordinate )  VALUES ( %(network_id)s, (GeomFromText('POINT(%(coordinate)s)')) );""" % { 'network_id': _network_id, 'coordinate': row } )                                                                                                                               
		conn.commit()

location(_network_id_, bus_stop_strings)
location(_network_id_, inter_section_strings)

conn.close()
