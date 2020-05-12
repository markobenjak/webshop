import React, { useEffect, useState } from 'react';
import { Layout, Row, Col, Button } from 'antd';

import ProductCard from './ProductCard';

import 'antd/dist/antd.css';
import '../App.css';


export default function Content(props) {

    const [products, setProducts] = useState(null);
    const [isLoadingProducts, setIsLoadingProducts] = useState(false);

    const fetchItems = () =>{
        fetch('http://localhost:8080/api/product/list',{
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
              }
        })
        .then(response => response.json())
        .then(data => setProducts(data))

    }

    useEffect(() => {
        fetchItems();
    }, [])


    return (
        <Layout.Content className="content-width">
            <div className="site-layout-content">
                <Row >
                    {
                        products !== null ? products.map((item, index)=>{
                            return(
                                <Col xs={{ span: 24 }} md={{ span: 6 }} className="col-bottom-spacing">
                                    <ProductCard title={item["name"]} price={item["price_hrk"]} key={item["id"]}/>
                                </Col>
                            )
                        }) : null
                    }
                </Row>

            </div>
        </Layout.Content>
    )
}