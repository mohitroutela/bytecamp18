#from firebase_admin import db
#ref=db.reference('https://bytecamp18-c017d.firebaseio.com/data/')
import serial
import time
import requests
import json
import pyrebase

config = {
    "apiKey": "AIzaSyD0k6I2bHQyYc4tEgXZu27goaodeRpFA_E",
    "authDomain": "bytecamp18-c017d.firebaseapp.com",
    "databaseURL": "https://bytecamp18-c017d.firebaseio.com",
    "storageBucket": "bytecamp18-c017d.appspot.com"
    
  }

firebase = pyrebase.initialize_app(config)
db = firebase.database()
#firebase_url = 'YOUR_FIREBASE_DB_URL'
#Connect to Serial Port for communication
ser = serial.Serial('/dev/ttyACM0', 115200, timeout=0)
#Setup a loop to send Temperature values at fixed intervals
#in seconds
fixed_interval = 10
while 1:
  try:
    #temperature value obtained from Arduino + LM35 Temp Sensor          
    
    pulse_c = ser.readline().decode().strip()
##    pulserate = pulse_c.split(': ')
    #current time and date
    time_hhmmss = time.strftime('%H:%M:%S')
    date_mmddyyyy = time.strftime('%d/%m/%Y')
    
    if (len(pulse_c) != 0):
      pulserate = int(str(pulse_c))
      if pulserate <100:
        
        #current location name
        #temperature_location = 'Mumbai-Kandivali';
        print(str(pulserate)+ ',' + str(time_hhmmss) + ',' + str(date_mmddyyyy))
	data = {"date": date_mmddyyyy,"heartbeat":str(pulserate),"time":time_hhmmss}
	db.child("users").set(data)
        #user_ref=ref.child('data')
        #user_ref.set({'date:date_mmddyyyy','heartbeat:str(pulserate)','time:time_hhmmss'})
    time.sleep(1)
      #insert record
     # data = {'date':date_mmddyyyy,'time':time_hhmmss,'value':temperature_c}
      #result = requests.post(firebase_url + '/' + temperature_location + '/temperature.json', data=json.dumps(data))
      
      #print 'Record inserted. Result Code = ' + str(result.status_code) + ',' + result.text
      #time.sleep(fixed_interval)
  except IOError:
    print('Error! Something went wrong.')
    
