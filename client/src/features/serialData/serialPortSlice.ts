import { createSlice } from "@reduxjs/toolkit";

interface DataItem {
    timestamp: number;
    data: string;
}

interface SerialPortState {
    isConnected: boolean;
    port: null | string;
    baudRate: null | number;
    data: DataItem[];
    bufferSize: null | number;
    lineSeperator: null | string;
    clientUpdateRate: number;
}

const initialState = {
    isConnected: false,
    port: null,
    baudRate: null,
    data: [] as string[],
    bufferSize: null,
    lineSeperator: '\n',
    clientUpdateRate: 100
}

const serialPortSlice = createSlice({
    name: 'serialPort',
    initialState,
    reducers: {
        saveConnectedPort: (state, { payload }) => {
            Object.assign(state, payload);
        },
        setConnectedStatus: (state, { payload }) => {
            state.isConnected = payload;
        },
        setBaudRate: (state, { payload }) => {
            state.baudRate = payload;
        },
        setLineSeperator: (state, { payload }) => {
            state.lineSeperator = payload;
        },
        setClientUpdateRate: (state, { payload }) => {
            state.clientUpdateRate = payload;
        },
        setBufferSize: (state, { payload }) => {
            state.bufferSize = payload;
        },
        pushData: (state, { payload }: { payload: string }) => {
            console.log('pushData: ')
            console.log(payload)
            state.data.push(payload);
        }
    }
})

export const { saveConnectedPort, setConnectedStatus, setBaudRate, setLineSeperator, setClientUpdateRate, setBufferSize, pushData } = serialPortSlice.actions;
export default serialPortSlice.reducer;