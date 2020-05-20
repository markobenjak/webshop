import React from 'react';

import { Card, Divider } from 'antd';

import {LocalizationContext } from '../util/LocalizationContext';
import translation from '../translations/translations.json';

export default function ProductCard(props) {
	console.log(props);
	
	const { locale } = React.useContext(LocalizationContext);

    return (
            <Card
                hoverable
                style={{ width: "240px" }}
            >
                <Card.Meta
                    title={props.title}
					description={props.description}
                />
				<Divider style={{borderTop:"1px solid #8a8a8a"}}/>
				<p>{translation.price[locale]}{props.price}</p>
				<p>{translation.quantity[locale]} {props.quantity}</p>
            </Card>
    )
}