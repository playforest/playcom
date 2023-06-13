

import React, { useRef, useEffect } from 'react';
import { VariableSizeList as List } from 'react-window';
import { useSelector } from 'react-redux';
import { Card, Row } from 'antd';
import { CodeOutlined } from '@ant-design/icons';

const label = 'Console';

export const Console = () => {
    const data = useSelector((state) => state.serialPort.data);
    const listRef = useRef(null);

    const getItemSize = (index) => {
        return 12;
    }

    useEffect(() => {
        listRef.current?.scrollToItem(data.length - 1);
    }, [data]);

    const Row = ({ index, style, data }) => (
        <div style={style}>
            <p
                style={{ color: 'white', fontFamily: 'menlo', fontSize: '10px', lineHeight: '7px' }}>
                {data[index].data}
            </p>
        </div>
    )

    return (
        <Card
            size="small"
            title={
                <>
                    <CodeOutlined /> {label}
                </>
            }
            style={{ width: '100%', height: '100%' }}
            bodyStyle={{ backgroundColor: '#000' }}
        >

            <List
                ref={listRef}
                height={200}
                itemCount={data.length}
                itemSize={getItemSize}
                width={'100%'}
                itemData={data}
            >
                {Row}
            </List>

        </Card >
    )
}

