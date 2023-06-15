

import React, { useRef, useEffect, useState } from 'react';
import { VariableSizeList as List } from 'react-window';
import { useSelector, useDispatch } from 'react-redux';
import { toggleAutoscroll, setAutoscroll } from '../features/console/consoleSlice';
import { Card, Row, Divider, Space, Button, Tooltip } from 'antd';
import {
    CodeOutlined, EditOutlined, EllipsisOutlined, VerticalAlignBottomOutlined,
    ClockCircleOutlined, UndoOutlined
} from '@ant-design/icons';
const label = 'Console';

export const Console = () => {
    const dispatch = useDispatch();
    const data = useSelector((state) => state.serialPort.data);
    const autoscroll = useSelector((state) => state.console.autoscroll);
    const listRef = useRef(null);
    // const [autoscroll, setAutoscroll] = useState(true);
    const [userScrolled, setUserScrolled] = useState(false);

    let lastVisibleItemIndex = 0;

    const getItemSize = (index) => {
        return 14
    }

    useEffect(() => {
        if (autoscroll && !userScrolled)
            listRef.current?.scrollToItem(data.length - 1);
    }, [data, autoscroll, userScrolled]);

    // useEffect(() => {
    //     const scrollContainer = listRef.current && listRef.current.outerRef.current;

    //     const onScroll = () => {
    //         setUserScrolled(true);
    //         setAutoscroll(false);
    //     }

    //     if (scrollContainer) {
    //         scrollContainer.addEventListener('scroll', onScroll);
    //     }

    //     return () => {
    //         if (scrollContainer) {
    //             scrollContainer.removeEventListener('scroll', onScroll);
    //         }
    //     }
    // }, []);

    const handleScroll = ({ scrollOffset, scrollUpdateWasRequested }) => {
        const autoscrollBuffer = 10;
        if (!scrollUpdateWasRequested && autoscroll) {
            setUserScrolled(true);
            dispatch(setAutoscroll(false));
        }
    }

    // const handleItemsRendered = ({ visibleStartIndex, visibleStopIndex }) => {
    //     const autoscrollBuffer = 10;
    //     if (autoscroll && (visibleStopIndex > (data.length - autoscrollBuffer))) {
    //         dispatch(setAutoscroll(true))
    //     }
    // }

    const Row = ({ index, isScrolling, style, data }) => (
        <div style={style}>
            <p
                style={{ paddingLeft: '10px', color: 'white', fontFamily: 'menlo', fontSize: '10px', lineHeight: '7px' }}>
                {data[index].data}
                {isScrolling ? setUserScrolled(true) : setUserScrolled(false)}
            </p>
        </div>
    )

    const handleToggleAutoscroll = () => {
        dispatch(toggleAutoscroll());
        if (autoscroll) {
            setUserScrolled(false);
        }
    }

    return (
        <Card
            size="small"
            title={
                <>
                    <CodeOutlined /> {label}
                </>
            }
            style={{ width: '100%', height: '100%', }}
            bodyStyle={{ padding: '0px' }}
        >

            <div
                style={{ backgroundColor: '#000', width: '100%' }}>
                <List
                    useIsScrolling
                    ref={listRef}
                    height={200}
                    itemCount={data.length}
                    itemSize={getItemSize}
                    width={'100%'}
                    itemData={data}
                    onScroll={handleScroll}
                // onItemsRendered={handleItemsRendered}
                >
                    {Row}
                </List>
            </div>
            <Divider dashed={true} style={{ margin: '0px' }} />
            <div
                style={{ backgroundColor: '#FFFFFF', padding: '0px' }}>
                {/* Content after the divider */}
                <Space.Compact block size='small' align='end' style={{ justifyContent: 'flex-end' }}>
                    <Tooltip
                        placement='bottomLeft' title="Timestamp" overlayInnerStyle={{ fontSize: '12px' }}
                        overlayStyle={{ width: '80px', maxHeight: '15px' }} style={{ fontSize: '10px' }}>
                        <Button type='text' icon={<ClockCircleOutlined key="timestamp" style={{ fontSize: '10px' }} />} style={{ fontSize: '12px' }}></Button>
                    </Tooltip>
                    <Tooltip
                        placement='bottomLeft' title="Auto-scroll" overlayInnerStyle={{ fontSize: '12px' }}>
                        <Button
                            type='text' onClick={handleToggleAutoscroll} icon={<VerticalAlignBottomOutlined key="autoscroll" style={{ fontSize: '10px' }} />}></Button>
                    </Tooltip>
                    <Tooltip
                        placement='bottomLeft' title='Clear' overlayInnerStyle={{ fontSize: '12px' }}>

                        <Button type='text' icon={<UndoOutlined key="clear" style={{ fontSize: '10px' }} />}></Button>
                    </Tooltip>
                </Space.Compact>
            </div>

        </Card>
    );
}


