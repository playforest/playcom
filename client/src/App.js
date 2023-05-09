import { AppHeader, Console, Plot, Settings } from './components';

import React from 'react'
import { Switch, Link, Route } from 'react-router-dom';
import { Layout, Space } from 'antd';
const { Header, Content, Sider, Footer } = Layout;

const contentStyle = {
    // textAlign: 'center',
    minHeight: 400,
    // lineHeight: '120px',
    // color: '#fff',
    backgroundColor: '#FFF',
};
const siderStyle = {
    textAlign: 'center',
    lineHeight: '120px',
    color: '#fff',
    backgroundColor: '#fff',
};
// const footerStyle = {
//     textAlign: 'center',
//     color: '#fff',
//     backgroundColor: '#7dbcea',
// };

const App = () => {
    return (

        <Layout>
            <AppHeader />
            <Layout>

                <Content style={contentStyle}>
                    <Space
                        direction="vertical"
                        style={{ display: 'flex' }}
                        size='large'
                    >
                        <Plot />
                        <Console />
                        <Settings />
                    </Space>
                </Content>
                <Sider style={siderStyle}>Sider</Sider>
            </Layout>
        </Layout>

    )
}

export default App