let port: any;
export async function connectToSerialPort(baudRate: Number) {
    port = await navigator.serial.requestPort();
    await port.open({ baudRate: baudRate})
}

export async function availablePorts() {
    navigator.serial.getPorts().then((ports) => {

        console.log(`available ports: `, ports);
        for (let i=0; i<ports.length; i++) {
            console.log( ports[i].getInfo() );
        }
    })
}