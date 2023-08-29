# TODO

## Client (React)
- [ ] display connected ports
- [ ] close port button
- [*] setup redux states
    - [*] serial port slice
    - [*] serial data state
    - [*] include timestamp
    - [ ] parser
        - [ ] line seperators
        - [ ] basic comma seperated values
        - [ ] leading metric name version
- [*] console
    - [*] display line
    - [ ] toggle timestamp
    - [*] apply virtual windowing for performance
    - [*] auto scroll checkox
        - [*] if auto-scroll: gracefully turn off once user scrolls upwords
            - [*] store user scroll state globally in redux
    - [ ] clear / reset view
- [ ] plot
    - [ ] display line chart and track dummy data
    - [ ] update data / legend with parsing settings
    - [ ] set visible context (t-N)
    - [ ] inherit line colors from plot settings


## Client (Services)
- [*] Retrieve connected ports
- [*] Develop service for opening serial port connection
- [ ] close port
- [ ] Service for sending data to serial port
- [*] Service for receving data from serial port
- [*] deserialize incoming data stream
- [ ] Display data onto console
- [ ] Error handling

## Backend
- [x] Stream API
- [x] Sync API (S3)
- [x] User API
- [x] User registration
- [ ] Session tokens
    - [ ] Token persistence to Redis
- [ ] Rate limiting
