import { React, useState } from 'react';
import { Button, ConfigProvider, Select, Space } from 'antd';
import { Port, availablePorts } from '../../services/serial';


export const Connect = () => {
    const [baudRate, setBaudRate] = useState(9600);
    const [ports, setPorts] = useState([]);
    const [statusMessage, setStatusMessage] = useState('No ports connected');
    const [isPortConnected, setIsPortConnected] = useState(false);

    const handleBaudRateChange = (value) => {
        setBaudRate(Number(value));
    };

    const handleSelectPortClick = async () => {
        const port = new Port(baudRate);
        await port.connect();
        let portInfo = port.getInfo().usbVendorId;

        if (portInfo) {
            setIsPortConnected(true);
            console.log('portinfo: ', portInfo)
            setStatusMessage(
                <div>
                Connected to device: <code>{portInfo}</code>
                </div>
            );
        } else {
            setStatusMessage('Failed to get port info.');
        }

        setPorts(oldPorts => [...oldPorts, port]);
        availablePorts();
    };

    let connectionButton = (
        <ConfigProvider
            theme={{
                token: {
                    colorPrimary: '#00b96b'
                }
            }}
        >
            <Button ghost
                type='primary'
                size='small'
                onClick={handleSelectPortClick}
                style={{}}> Select Port
            </Button>
        </ConfigProvider>
    );

    if (isPortConnected) {
        connectionButton =
        <Button danger ghost
            type='primary'
            size='small'
            onClick={handleSelectPortClick}
            style={{}}> Disconnect
        </Button>
    }


    return (
        <Space direction='verticalasci'>
            <Space style={{ width: '100%' }}>
            {statusMessage}
            {connectionButton}
            </Space>
            <Space style={{ width: '100%' }}>

            <Select
                placeholder='Baud rate: '
                // defaultValue="9600"
                style={{ width: 120 }}
                size='small'
                onChange={handleBaudRateChange}
                options={[
                    { value: '9600', label: '9600' },
                    { value: '19200', label: '19200' },
                    { value: '38400', label: '38400' },
                    { value: '57600', label: '57600' },
                    { value: '115200', label: '115200' },
                ]}
            />
            </Space>
        </Space>
    )
}