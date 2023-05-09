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
    // backgroundColor: '#108ee9',
};
// const siderStyle = {
//     textAlign: 'center',
//     lineHeight: '120px',
//     color: '#fff',
//     backgroundColor: '#fff',
// };
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

                <Content>
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
                {/* <Sider>Sider</Sider> */}
            </Layout>
            <Footer>Footer</Footer>
        </Layout>

    )
}

export default App