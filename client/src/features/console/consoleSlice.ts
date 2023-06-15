import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    autoscroll: true
}

const consoleSlice = createSlice({
    name: 'console',
    initialState,
    reducers: {
        setAutoscroll: (state, { payload }) => {
            state.autoscroll = payload;
        },
        toggleAutoscroll: (state) => {
            state.autoscroll = state.autoscroll ? false : true;
        }
    }
})

export const { toggleAutoscroll, setAutoscroll } = consoleSlice.actions;
export default consoleSlice.reducer;