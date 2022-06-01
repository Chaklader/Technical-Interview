import MySQLdb
import json
import math

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_test'
json_file_location = 'C:/Programming/SQL Query/python files/CATS_route.json'

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
cur.execute('TRUNCATE TABLE segment')
conn.commit()

def haversine(lon1, lat1, lon2, lat2):
    """
    Calculate the great circle distance between two points 
    on the earth (specified in decimal degrees).
    Source: http://gis.stackexchange.com/a/56589/15183
    """
    # convert decimal degrees to radians 
    lon1, lat1, lon2, lat2 = map(math.radians, [lon1, lat1, lon2, lat2])
    # haversine formula 
    dlon = lon2 - lon1 
    dlat = lat2 - lat1 
    a = math.sin(dlat/2)**2 + math.cos(lat1) * math.cos(lat2) * math.sin(dlon/2)**2
    c = 2 * math.asin(math.sqrt(a)) 
    km = 6367 * c
    distance_in_meter = 1000 * km 
    return distance_in_meter

   
def calculate_length(segment_string):

	segment_length = 0 
	segment_str = segment_string.split(',')
	segment_array_length = len(segment_str)

	for row in segment_str:

		index = segment_str.index(row)

		if index < segment_array_length -1:

			longitude_one = float(segment_str[index].split(' ')[0])
			latitude_one = float(segment_str[index].split(' ')[1])

			longitude_two = float(segment_str[index+1].split(' ')[0])
			latitude_tow = float(segment_str[index+1].split(' ')[1]) 

			segment_length = segment_length + haversine(longitude_one, latitude_one, longitude_two, latitude_tow)

	return segment_length

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
            														'length': calculate_length(row),
            														'shape': row 
            													} )
            conn.commit()
            # cur.execute('INSERT INTO segment (network_id, start_location_id, end_location_id, length , shape) VALUES (%s, %s, %s, %s, GeomFromText('LINESTRING(%s)'))',(1,segment_start_id, segment_end_id, 10, row ) )
	    
conn.close()


# total_length of CATS route = 6344 meter 

