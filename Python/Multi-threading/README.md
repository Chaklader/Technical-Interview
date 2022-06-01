# Top level functions:
* Scheduler()
* SensorThread()
* ActuatorThread()

# Available low level functions
## Actuators
* heat1.on()
* heat1.off()
* heat2.on()
* heat2.off()
* hum1.on()
* hum1.off()
* hum2.on()
* hum2.off()
 
## Sensors
* sensors.temp(1).read() #assume fahrenheit
* sensors.temp(2).read()
* sensors.temp(3).read()
* sensors.temp(4).read()
* sensors.hum(1).read() #assume percentage 1-100%
* sensors.hum(2).read()

# Example pseudo code for system:
Two different people wrote some pseudo, they both have some insights. We can discuss any questions on the interview. The project is very flexible so I feel it is best to let you digest the idea and come up with the best implementation. For someone experienced I estimate this will take 4 hours.
## Pseudo 1
~~~~
Scheduler():
  SensorThread():
    Polling rate 1 s (*)
    Log sensor data to a single csv file
  
  ActuatorThread():
    Average last N samples (*)
    Check boundaries to see if we need to do anything
      If LOW_T(*) < TEMP < HIGH_T(*): System normal
      If TEMP < LOW_T: Turn on heating elements
      If TEMP > HIGH_T: Turn off heating elements
      If TEMP > HIGH_CRITICAL: Turn off heating elements
      If TEMP < LOW_CRITICAL: Turn on heating elements ignoring time settings
  (*) These settings as well as all the relevant ones must be able to be set from a csv file
    Actuators can be controlled in the following ways
      PWM for 10 s (*)
      Full-on for 10 s
      Full-off for 10 s
~~~~

## Pseudo 2

~~~~
Start system
Start sensor and actuator threads
Read sensors until the end of time...

Start ActuatorThread()
    Determine time of day (i.e. 8am - 5pm)
    Load actuator settings based on the time of day (at least four modifiable time
    slots, i.e morning, day, evening, night)
    Overwrite settings based on overwrite csv file
        (Same as settings file in most cases, used in case user is taking direct control of
        some of the settings)

    Average last 10 samples to determine next action
    IF time = day AND temp < 110:
        Turn on heating elements
        Some heating elements are normaly only available at some times of the day,
        unless overwriten.
    ELIF ...:
    ELIF ...:
    ELIF ...:
    ELIF ...:
    ELSE:
        All good. No worries.
~~~~

# Will be tested on:
Archlinux
Raspberry Pi 3 running Jessie Rapsbian
