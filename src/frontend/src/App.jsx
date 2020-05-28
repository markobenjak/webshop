import React, { useState, useEffect } from 'react';
import { Layout } from 'antd';
import { LocalizationContext } from './util/LocalizationContext';
import { BasketContext } from './util/BasketContext';

import { CookieService } from './services/CookieService';

import {
  BrowserRouter as Router,
  Route,
  withRouter
} from "react-router-dom";

import Header from './components/Header';
import Content from './components/Content';
import Login from './components/Login';

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

  // let basketCountCookie = CookieService.getCookie();
  // useEffect(() => {
  //   if(basketCountCookie === undefined){
  //     basketCountCookie = "0";
  //     CookieService.setCookie("basket_count",0,500);
  //   }
  //   basketCountCookie = parseInt(basketCountCookie);
  // }, []);

  

  const [basketProductCount, setBasketProductCount] = useState(0); //TODO: save basket to cookie/local storage, load from there
  const basketContextVal = {
    productCount: basketProductCount,
    updateCount: setBasketProductCount
  }

  

  return (
    <LocalizationContext.Provider value={contextValue}>
      <BasketContext.Provider value={basketContextVal}>
        <Layout style={{ minHeight: "100vh" }}>
          <Header searchCallback={search} />
          <Content search={searchText} />
          <Layout.Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Layout.Footer>
        </Layout>
      </BasketContext.Provider>
    </LocalizationContext.Provider>
    
  );
}

export default withRouter(App);
