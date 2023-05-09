import React from 'react';
import { Card } from 'antd';
import { SlidersOutlined } from '@ant-design/icons';

const label = 'Plot';

export const Plot = () => {
    return (
        <Card 
            size="small"
            title={
                <>
                    <SlidersOutlined /> {label}
                </>
            } 
            style={{ width: '100%' }}>

        </Card>
    )
}
