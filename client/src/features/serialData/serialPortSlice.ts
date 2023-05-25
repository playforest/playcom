import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

interface SerialPortState {
    isConnected: boolean;
    port: null | string;
    baudRate: null | number;
    data: string[];
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
        saveConnectedPort: (state, action) => {
            console.log(action)
        },
        setConnectedStatus: (state, { payload }) => {
            state.isConnected = payload;
        },
        pushData: (state, { payload }: { payload: string }) => {
            state.data.push(payload);
        }
    }
})

export const { saveConnectedPort, setConnectedStatus } = serialPortSlice.actions;
export default serialPortSlice.reducer;