export class Port {
    private port: SerialPort;
    private inputStream: ReadableStream<string>;
    private reader: ReadableStreamDefaultReader<string>;
    private baudRate: number;
    private lineSeperator: string;
    private line: Uint8Array;
    private buffer: Uint8Array;
    private deserialisedData: string[] = [];

    constructor(baudRate: number, lineSeperator: string = '\n') {
        this.port = undefined!;
        this.inputStream = undefined!;
        this.reader = undefined!;
        this.baudRate = baudRate;
        this.lineSeperator = lineSeperator;
        this.line = new Uint8Array();
        this.buffer = new Uint8Array();
        this.deserialisedData = undefined!;
    }

    public async connect() {
        this.port = await navigator.serial.requestPort();
        await this.port.open({ baudRate: this.baudRate });

        let decoder = new TextDecoderStream();
        this.inputStream = decoder.readable;
        this.reader = this.inputStream.getReader();

        this.readData();
    }

    public async disconnect() {
        if (this.reader) {
            await this.reader.cancel();
            await this.reader.releaseLock();
            this.reader = undefined!;
        }

        if (this.port.readable) {
            this.port.readable.cancel();
        }

        if (this.port.writable) {
            this.port.writable.getWriter().close();
        }

        await this.port.close().then((result) => {
            console.log(result);
        })

        this.port = undefined!;
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
                    // console.log('value: ', value);
                    this.storeDeserialisedData(value);
                }
            } catch (error) {
                // Handle |error|…
            } finally {
                reader.releaseLock();
            }
        }
    }

    private storeDeserialisedData(chunk: Uint8Array) {
        const asciiDelimiter: number = this.lineSeperator.charCodeAt(0);
        const delimiterIndex = chunk.indexOf(asciiDelimiter);
        const chunkContainsDelimiter: boolean = delimiterIndex === -1 ? false : true;
        // console.log('chunkContainsDelimiter: ', chunkContainsDelimiter)


        if (!chunkContainsDelimiter) {
            this.buffer = new Uint8Array(this.line.length + chunk.length);
            this.buffer.set(this.line);
            this.buffer.set(chunk, this.line.length);

            this.line = new Uint8Array(this.buffer.length);
            this.line.set(this.buffer);

            this.buffer = new Uint8Array(0);
        } else {
            // construct new buffer with the line up to (and including) the delimiter
            this.buffer = new Uint8Array(this.line.length + delimiterIndex + 1);
            this.buffer.set(this.line);
            this.buffer.set(chunk.slice(0, delimiterIndex + 1), this.line.length);

            // update the line with the remainder of the chunk
            this.line = chunk.slice(delimiterIndex + 1);

            // decode buffer into a string
            const decoder = new TextDecoder();
            const string = decoder.decode(this.buffer);
            console.log(string)
            this.deserialisedData.push(string);
        }
    }

    public getPortState() {
        if (this.port) {
            return {
                port: this.port.getInfo(),
                baudRate: this.baudRate,
                lineSeperator: this.lineSeperator,
                data: this.buffer,
                info: this.port.getInfo()
            };
        }
        return null;
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
