interface Serial {
    requestPort(): Promise<SerialPort>;
    getPorts(): Promise<SerialPort>;
  }
  
  interface Navigator {
    serial: Serial;
  }