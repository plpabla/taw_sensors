# sensors API
API allows to register sensor and log its measurements

# API endpoints
* `sensor` 
  * `GET` list of sensors. Possible filtering tbd
  * `POST` - register a new sensor, return its id. Parameters:
    * `type`
    * `unit`
    * `description`
  * `DELETE` - remove sensor with given `ID`
* `sensor\{id}`
  * `GET` - sensor details
* `measurement`
  * `GET` - list of measurements. Possible filtering tbd
  * `POST` - add measurement
    * `sensor_id`
    * `value`
* `measurement\id` 
  * `GET` - display details
  * `DELETE` - remove
