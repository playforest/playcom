import { configureStore } from '@reduxjs/toolkit';
import serialPortReducer from './features/serialData/serialPortSlice'

export const store = configureStore({
    reducer: {
        serialPort: serialPortReducer
        // serialData: serialDataReducer,
        // console: consoleReducer,
        // chart: chartReducer
    }
})