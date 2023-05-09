import { React } from 'react';
import { Button, Select, Space } from 'antd';

export const Connect = () => {
    return (
        <Space direction='vertical'>
            <Button size='small'>Select Port</Button>
            <Select
                defaultValue="9600"
                style={{width: 120}}
                size='small'
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