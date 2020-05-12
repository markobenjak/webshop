import React from 'react';
import { Layout, Row, Col } from 'antd';

import Header from './components/Header';
import Content from './components/Content';

import 'antd/dist/antd.css';
import './App.css';




function App() {
  return (
    <Layout style={{minHeight:"100vh"}}>
      <Header/>
      <Content />
      <Layout.Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Layout.Footer>
    </Layout>
  );
}

export default App;
