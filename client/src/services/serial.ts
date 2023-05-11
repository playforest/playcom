export class Port {
    private port: SerialPort;
    private inputStream: ReadableStream<string>;
    private reader: ReadableStreamDefaultReader<string>;
    private baudRate: number;

    constructor(baudRate: number) {
        this.port = undefined!;
        this.inputStream = undefined!;
        this.reader = undefined!;
        this.baudRate = baudRate;
    }

    public async connect() {
        this.port = await navigator.serial.requestPort();
        await this.port.open({ baudRate: this.baudRate });

        let decoder = new TextDecoderStream();
        this.inputStream = decoder.readable;
        this.reader = this.inputStream.getReader();

        this.readData();
    }

    public async readData() {
        while (this.port.readable) {
            const reader = this.port.readable.getReader();
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
}

export async function availablePorts() {
    const ports = await navigator.serial.getPorts();

    console.log(`available ports: `, ports);
    for (let i = 0; i < ports.length; i++) {
        console.log(ports[i].getInfo());
    }

    return ports;

}
