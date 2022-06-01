import MySQLdb
import json
from pprint import pprint

localhost = '131.94.133.207'
user_name = 'itpa1dbuser'
user_password = 'akula4less'
database_name = 'ITPA_database_model'


conn = MySQLdb.connect(host=localhost, port = 3306 , user= user_name, passwd= user_password, db= database_name) 
cur = conn.cursor() 

file_loc = 'C:/Programming/SQL Query/data.json'

with open(file_loc) as data_file:    
    data = json.load(data_file)

# pprint(data)


# print len(data['parking_data'])
all_garage = data['parking_data']

for garage in all_garage:
	garage_name = all_garage[garage]
	s = ''
	for location in garage_name:
		s = s + str(location['lon'])+' '+ str(location['lat'])+','
		# print location['lat'] , location['lon']
	print s



# cur.execute('TRUNCATE TABLE location')
# cur.execute('INSERT INTO location (network_id, coordinate ) VALUES ( 1, POINT(0,0))' + ';' + 'INSERT INTO location (network_id, coordinate ) VALUES ( 1, POINT(0,0))' + ';' )
# conn.commit()




 
 
 