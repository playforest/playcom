import React from 'react';
import { Card } from 'antd';
import ErrorBoundary from 'antd/es/alert/ErrorBoundary';

export const Console = () => {
    return (
        <Card 
            size="small" 
            title="Console" 
            style={{ width: '100%', height: '100%' }}
            bodyStyle={{backgroundColor: '#000'}}
            >
            <p>Card content</p>
            <p>Card content</p>
            <p>Card content</p>
        </Card>
    )
}
