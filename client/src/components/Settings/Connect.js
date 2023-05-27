import { store } from '../../store'
import { React, useState, useEffect } from 'react';
import { Button, ConfigProvider, Select, Space } from 'antd';
import { Port, availablePorts } from '../../services/serial';
import { setConnectedStatus, saveConnectedPort, pushData } from '../../features/serialData/serialPortSlice';
import { useSelector, useDispatch } from 'react-redux';

export const Connect = () => {

    const dispatch = useDispatch();
    const { isConnected } = useSelector((state) => state.serialPort);
    const [baudRate, setBaudRate] = useState(9600);
    const [ports, setPorts] = useState([]);
    const [statusMessage, setStatusMessage] = useState('No ports connected');
    const [isPortConnected, setIsPortConnected] = useState(false);


    const handleBaudRateChange = (value) => {
        setBaudRate(Number(value));
    };

    const handleSelectPortClick = async () => {
        const port = new Port(baudRate, '\n', (data) => {
            dispatch(pushData(data));
        });

        await port.connect();
        let portInfo = port.getPortState().info.usbVendorId;

        if (portInfo) {
            dispatch(saveConnectedPort(port.getPortState()))
            dispatch(setConnectedStatus(true))
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

    const handleDisconnectPortClick = async () => {
        await ports[ports.length - 1].disconnect();
        dispatch(setConnectedStatus(false));
    }

    let connectionButton;
    if (isConnected) {
        connectionButton =
            <Button danger ghost
                type='primary'
                size='small'
                onClick={handleDisconnectPortClick}
                style={{}}> Disconnect
            </Button>
    } else {
        connectionButton =
            (
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