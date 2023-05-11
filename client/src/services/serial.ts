let port: any;
let inputStream : any;
let reader: any;


export async function connectToSerialPort(baudRate: Number) {
    port = await navigator.serial.requestPort();
    await port.open({ baudRate: baudRate});

    let decoder = new TextDecoderStream();
    inputStream = decoder.readable;
    reader = inputStream.getReader();

    readData();
}

export async function availablePorts() {
    navigator.serial.getPorts().then((ports) => {

        console.log(`available ports: `, ports);
        for (let i=0; i<ports.length; i++) {
            console.log( ports[i].getInfo() );
        }
    })
}

export async function readData() {
    while (port.readable) {
        const reader = port.readable.getReader();
        try {
          while (true) {
            const { value, done } = await reader.read();
            if (done) {
                console.log('reader canceled.')
              // |reader| has been canceled.
              break;
            }
            // Do something with |value|…
            console.log('value: ', value);
          }
        } catch (error) {
          // Handle |error|…
        } finally {
          reader.releaseLock();
        }
      }
}