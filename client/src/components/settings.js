import React, { useState } from 'react';
import { Tabs } from 'antd';

const tabs = ['Connect', 'Plot'];

export const Settings = () => {
    return (
        <div>
            <Tabs
                defaultActiveKey='1'
                type='card'
                size='small'
                items={
                    tabs.map((el, i) => {
                        const id = String(i);
                        return {
                            label: el,
                            key: id,
                            children: `Content of card tab ${el}`
                        }
                    })
                }
            />
        </div>
    )
}