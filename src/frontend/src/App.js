import React from 'react';
import { Layout, Row, Col, Modal, Input } from 'antd';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import Header from './components/Header';
import Content from './components/Content';

import 'antd/dist/antd.css';
import './App.css';




function App() {



  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Router>
        <Header />
        <Content />
        <Layout.Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Layout.Footer>
      </Router>


    </Layout>
  );
}

export default App;
