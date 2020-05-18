import React, { useState, useEffect } from 'react';
import { Layout, Row, Col, Modal, Input } from 'antd';
import { LocalizationContext, Localization } from './components/LocalizationContext';

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
import translations from './translations/translations.json';



function App() {

  const [ searchText, setSearchText] = useState("");

  const search = value => {
    setSearchText(value);
  }

  console.log(translations);

  return (
    <LocalizationContext.Provider value={Localization.currentLanguage}>
      <Layout style={{ minHeight: "100vh" }}>
        <Router>
          <Header searchCallback={search} />
          <Content search={searchText} />
          <Layout.Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Layout.Footer>
        </Router>


      </Layout>
    </LocalizationContext.Provider>
    
  );
}

export default App;
