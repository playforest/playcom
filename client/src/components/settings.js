import React, { useState } from 'react';
import { Tabs } from 'antd';
import { ApiOutlined, StockOutlined } from '@ant-design/icons';
import { Connect } from './Settings/Connect';

const tabs = [
    {
        label: 'Connect',
        icon: ApiOutlined
    },
    {
        label: 'Plot',
        icon: StockOutlined
    }
]

// const tabs = [`${<ApiOutlined/>} Connect`, 'Plot'];
const label = <ApiOutlined /> + ' Connect';

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
                        const Icon = el['icon'];
                        return {
                            label: (
                                <span>
                                    <Icon />
                                    {el['label']}
                                </span>
                            ),
                            key: id,
                            children: (
                                <Connect />
                            )
                        }
                    })
                }
            />
        </div>
    )
}