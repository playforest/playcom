import { configureStore } from '@reduxjs/toolkit';
import serialPortReducer from './features/serialData/serialPortSlice'
import consoleReducer from './features/console/consoleSlice'

export const store = configureStore({
    reducer: {
        serialPort: serialPortReducer,
        // serialData: serialDataReducer,
        console: consoleReducer
        // chart: chartReducer
    }
})