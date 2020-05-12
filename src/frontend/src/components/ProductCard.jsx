import React from 'react';
import { Layout, Card } from 'antd';

export default function ProductCard(props) {

    const product = {
        title: "Samsung Galaxy",
        price: "499.99 EUR"
    }

    const priceHrk = "HRK " + props.price;

    return (
        <Card
            hoverable
            style={{ width: "240px" }}
            cover={<img alt="example" src="https://www.gizmochina.com/wp-content/uploads/2020/02/Samsung-Galaxy-S20-Plus-500x500.jpg" />}
        >
            <Card.Meta
                title={props.title}
                description={priceHrk}
            />
        </Card>
    )
}