import React, { useState } from 'react';
import { Layout } from 'antd';
import { LocalizationContext } from './util/LocalizationContext';
import { BasketContext } from './util/BasketContext';

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
  
  const contextValue = {
    languages: languages, 
    changeLanguage: setCurrentLanguage, 
    locale: currentLanguage
  }


  const [basketProductCount, setBasketProductCount] = useState(0); //TODO: save basket to cookie/local storage, load from there
  const basketContextVal = {
    productCount: basketProductCount,
    updateCount: setBasketProductCount
  }

  return (
    <LocalizationContext.Provider value={contextValue}>
      <BasketContext.Provider value={basketContextVal}>
        <Layout style={{ minHeight: "100vh" }}>
          <Router>
            <Header searchCallback={search} />
            <Content search={searchText} />
            <Layout.Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Layout.Footer>
          </Router>
        </Layout>
      </BasketContext.Provider>
    </LocalizationContext.Provider>
    
  );
}

export default App;
