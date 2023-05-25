# TODO

## Client (React)
- [ ] display connected ports
- [ ] close port button
- [ ] setup redux states
    - [ ] serial port slice
    - [ ] serial data state

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