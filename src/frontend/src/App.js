import React, { useState } from 'react';
import { Layout } from 'antd';
import { LocalizationContext } from './util/LocalizationContext';

import {
  BrowserRouter as Router,
} from "react-router-dom";

import Header from './components/Header';
import Content from './components/Content';

import 'antd/dist/antd.css';
import './App.css';

const languages = ["hr","en"];

function App() {

  const [ searchText, setSearchText] = useState("");
  const search = value => {
    setSearchText(value);
  }

  const [ currentLanguage, setCurrentLanguage ] = useState("en");
  const setLanguage = value => {
    setCurrentLanguage(value);
  }
  const contextValue = {
    languages: languages, 
    changeLanguage: setLanguage, 
    locale: currentLanguage
  }

  return (
    <LocalizationContext.Provider value={contextValue}>
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
