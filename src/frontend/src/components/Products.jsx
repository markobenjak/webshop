import React  from 'react';
import { Row, Col } from 'antd';

import ProductCard from './ProductCard';

import 'antd/dist/antd.css';
import '../App.css';

export default function Products({products}) {
    return (
        <Row >
            {
                products && products.map((item, index) => {
                    return (
                        <Col xs={{ span: 24 }} md={{ span: 6 }} className="col-bottom-spacing">
                            <ProductCard
                                title={item["name"]}
                                price={item["price_hrk"]}
                                id={item["id"]}
                                key={index}
                            />
                        </Col>
                    )
                })
            }
        </Row>
    )
}