# sensors API
API allows to register sensor and log its measurements

# API endpoints
* `register` - register a new sensor, return its id. Rarameters
  * `type`
  * `units`
  * [optional] `description`
* `sensor` - list of sensors. Possible filtering tbd
* `sensor\id` - details of given sensor
* `measurement` - list of measurements. Possible filtering tbd
* `measurement\id` - given measurement
