import React from 'react';
import { Card } from 'antd';
import { CodeOutlined } from '@ant-design/icons';

const label = 'Console';

export const Console = () => {
    return (
        <Card 
            size="small" 
            title={
                <>
                  <CodeOutlined /> {label}
                </>
            }
            style={{ width: '100%', height: '100%' }}
            bodyStyle={{backgroundColor: '#000'}}
            >
            <p>Card content</p>
            <p>Card content</p>
            <p>Card content</p>
        </Card>
    )
}
