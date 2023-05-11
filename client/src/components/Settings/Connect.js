import { React, useState } from 'react';
import { Button, Select, Space } from 'antd';
import { Port, availablePorts } from '../../services/serial';


export const Connect = () => {
    const [baudRate, setBaudRate] = useState(9600);
    const [ports, setPorts] = useState([]);

    const handleBaudRateChange = (value) => {
        setBaudRate(Number(value));
    };

    const handleSelectPortClick = async () => {
        const port = new Port(baudRate);
        await port.connect();
        setPorts(oldPorts => [...oldPorts, port]);

        availablePorts();
    };

    return (
        <Space direction='vertical'>
            <Button 
                size='small'
                onClick={handleSelectPortClick}> Select Port </Button>
            <Select
                defaultValue="9600"
                style={{width: 120}}
                size='small'
                onChange={handleBaudRateChange}
                options={[
                    { value: '9600', label: '9600'},
                    { value: '19200', label: '19200'},
                    { value: '38400', label: '38400'},
                    { value: '57600', label: '57600'},
                    { value: '115200', label: '115200'},
                ]}
            />
        </Space>
    )
}