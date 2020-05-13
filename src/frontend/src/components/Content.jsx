import React, { useEffect, useState } from 'react';
import { Layout, Row, Col, Button } from 'antd';

import {
    Switch,
    Route
} from 'react-router-dom';

import Products from './Products';
import ProductPage from './ProductPage';

import 'antd/dist/antd.css';
import '../App.css';


export default function Content(props) {

    const [products, setProducts] = useState(null);
    const [isLoadingProducts, setIsLoadingProducts] = useState(false);

    const fetchItems = () =>{
        fetch('/api/product/list',{
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
              }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            setProducts(data);
        });
    }

    useEffect(() => {
        fetchItems();
    }, [])


    return (
        <Layout.Content className="content-width">
            <div className="site-layout-content">
                <Switch>
                    <Route path="/" exact >
                        <Products products={products} />
                    </Route>
                    <Route path="/product/:id" component={ProductPage} />
                </Switch>
            </div>
        </Layout.Content>
    )
}