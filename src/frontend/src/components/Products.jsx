import React, { useEffect, useState } from 'react';
import { Layout, Row, Col, Button } from 'antd';

import ProductCard from './ProductCard';

import 'antd/dist/antd.css';
import '../App.css';

export default function Products({products}) {
    return (
        <Row >
            {
                products !== null ? products.map((item, index) => {
                    return (
                        <Col xs={{ span: 24 }} md={{ span: 6 }} className="col-bottom-spacing">
                            <ProductCard
                                title={item["name"]}
                                price={item["price_hrk"]}
                                id={item["id"]}
                                key={item["id"]}
                            />
                        </Col>
                    )
                }) : null
            }
        </Row>
    )
}