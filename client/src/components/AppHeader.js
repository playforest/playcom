import React from 'react';

import { Layout, Space } from 'antd';
import styles from './AppHeader.module.css';

const { Header } = Layout;

export const AppHeader = () => {
    return (
        <Header className={styles.header} style={{
            position: 'sticky',
            top: 0,
            zIndex: 1,
            width: '100%',
            paddingLeft: 0,
            paddingRight: 0
        }} >
            <div style={{ display: 'flex', alignItems: 'flex-start' }}>
                playcom {">"}
            </div>
        </Header>
    )
}