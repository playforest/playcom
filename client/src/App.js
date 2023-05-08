import { AppHeader, Console, Plot } from './components';

import React from 'react'
import { Switch, Link, Route } from 'react-router-dom';
import { Layout, Space } from 'antd';
const { Header, Content, Sider, Footer } = Layout;

const contentStyle = {
    // textAlign: 'center',
    minHeight: 400,
    // lineHeight: '120px',
    // color: '#fff',
    // backgroundColor: '#108ee9',
};
const siderStyle = {
    textAlign: 'center',
    lineHeight: '120px',
    color: '#fff',
    backgroundColor: '#fff',
};
const footerStyle = {
    textAlign: 'center',
    color: '#fff',
    backgroundColor: '#7dbcea',
};

const App = () => {
    return (
        // <Space
        //     direction="vertical"
        //     style={{
        //         width: '100%',
        //     }}
        //     size={[0, 0]}
        // >
            <Layout>
                <AppHeader />
                <Layout>

                    <Content>
                        <Plot />
                        <Console />
                    </Content>
                    <Sider style={siderStyle}>Sider</Sider>
                </Layout>
                {/* <Footer style={footerStyle}>Footer</Footer> */}
            </Layout>
        // </Space>
    )
}

export default App