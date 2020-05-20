import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import { Skeleton, Row, Col, Divider, InputNumber, Button } from 'antd';

import BasketView from './BasketView';
import translation from '../translations/translations.json';
import {LocalizationContext } from '../util/LocalizationContext';
import '../App.css';

function Basket(props){
	
	const { locale } = React.useContext(LocalizationContext);
    const [basketDetails, setBasketDetails] = useState(null);
	const [isLoadingBasket, setIsLoadingBasket] = useState(false);
	
	const getOrder = () => {
		setIsLoadingBasket(true);
        fetch(`/api/order/1`)
        .then(res => res.json())
        .then(data => {
            console.log(data);
            setBasketDetails(data);
			setIsLoadingBasket(false);
        });
    }

const description = () =>{
        return(
            <Row>
                <Col md={{span:12}} xs={{span:24}}>
                    <img alt="example" src="https://cdn4.iconfinder.com/data/icons/shopicons/512/SHOPICONS_R-04-512.png" />

					<Button type="primary" size={"large"} block>
			          {translation.buy[locale]}
			        </Button>
					<Divider style={{borderTop:"1px solid #8a8a8a"}}/>
					<Button type="primary" size={"large"} block>
			          {translation.deleteBasket[locale]}
			        </Button>

				</Col>
                <Col md={{span:12}} xs={{span:24}}>
                    <div style={{marginLeft:"45px", padding:"10px",backgroundColor:"#ededed", width:"auto"}}>
    				  <Row gutter={30}>
			            {
			                basketDetails && basketDetails.map((item, index) => {
			                    return (
			                        <Col className="col-bottom-spacing">
			                            <BasketView
			                                title={item["productName"]}
			                                price={item.priceHrk}
			                                description={item["description"]}
											quantity={item["quantity"]}
			                                key={index}
			                            />
			                        </Col>
			                    )
			                })
			            }
			        </Row>
                    </div>
                </Col>
            </Row>
        );
    }

	useEffect(()=>{
	        getOrder();
	    }, [])

    return(
        <div>  
			{ isLoadingBasket === false && basketDetails !== null ? description() : () => (<Skeleton />)}
		</div>
    );
}

export default withRouter(Basket);