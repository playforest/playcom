import { React, useState } from 'react';
import { Button, Select, Space } from 'antd';
import { connect } from '../../services/serial';

export const Connect = () => {
    const [baudRate, setBaudRate] = useState(9600);

    const handleSelectChange = (value) => {
        setBaudRate(Number(value));
    };

    const handleButtonClick = () => {
        connect(baudRate);
    };

    return (
        <Space direction='vertical'>
            <Button 
                size='small'
                onClick={handleButtonClick
                }>Select Port</Button>
            <Select
                defaultValue="9600"
                style={{width: 120}}
                size='small'
                onChange={handleSelectChange}
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