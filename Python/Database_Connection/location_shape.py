import json 

file_location = 'C:/Programming/SQL Query/parking data/mmc - location-shape parking.json'

with open(file_location) as data_file:    
    data = json.load(data_file)

# pprint(data)

data = data['features']

dictionary = {}

for row in data:

	name = ''
	name = name + row['name']

	# location = ''
	# location = location + '%(longitude)s %(latitude)s'%{'longitude':row["geometry"]["location_coordinate"][0], 'latitude': row["geometry"]["location_coordinate"][1]}

	shape = ''
	for m in row["geometry"]["shape_coordinates"]:
		for n in m:
			shape = shape + '%(latitude)s %(latitude)s'%{'latitude':n[0], 'latitude': n[1]} + ','

		dictionary[name] = shape.strip(',')


# print len(data['parking_data'])
# all_garage = data['parking_data']

# for garage in all_garage:
# 	garage_name = all_garage[garage]
# 	s = ''
# 	for location in garage_name:
# 		s = s + str(location['lon'])+' '+ str(location['lat'])+','
# 		# print location['lat'] , location['lon']
# 	print s



# cur.execute('TRUNCATE TABLE location')
# cur.execute('INSERT INTO location (network_id, coordinate ) VALUES ( 1, POINT(0,0))' + ';' + 'INSERT INTO location (network_id, coordinate ) VALUES ( 1, POINT(0,0))' + ';' )
# conn.commit()




 
 
 