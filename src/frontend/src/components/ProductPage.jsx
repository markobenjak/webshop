import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import { Skeleton, Row, Col, Divider, InputNumber, Button } from 'antd';
import {LocalizationContext } from '../util/LocalizationContext';

import translation from '../translations/translations.json';

import '../App.css';

function ProductPage(props) {

    let productId = props.match.params.id;

    const { locale } = React.useContext(LocalizationContext);

    const [productDetails, setProductDetails] = useState(null);
    const [isLoadingProduct, setIsLoadingProduct] = useState(false);

    const getProduct = () => {
        setIsLoadingProduct(true);

        fetch(`/api/product/${productId}`)
        .then(res => res.json())
        .then(data => {
            console.log(data);
            setProductDetails(data);
            setIsLoadingProduct(false);
        });
    }

    const Availability = ({isAvailable}) =>{
        return(
            <React.Fragment>
                { isAvailable === true ? 
                <p style={{color:"#00e30f", display:"inline-block"}}>
                    {translation.productAvailable[locale]}
                </p> :
                <p style={{color:"red", display:"inline-block"}}>
                    {translation.productUnavailable[locale]}
                </p>
                }
            </React.Fragment>
            
        )
    }

    //Cuz I'm lazy
    const description = () =>{
        return(
            <Row>
                <Col md={{span:12}} xs={{span:24}}>
                    <img alt="example" src="https://www.gizmochina.com/wp-content/uploads/2020/02/Samsung-Galaxy-S20-Plus-500x500.jpg" />
                </Col>
                <Col md={{span:12}} xs={{span:24}}>
                    <div style={{margin:"10px", padding:"10px",backgroundColor:"#ededed", width:"100%"}}>
                        <Row>
                            <Col>
                                <h2>{productDetails.name}</h2>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <p style={{fontSize:"1.2em"}}>{translation.price[locale]}{'\u00A0'}{productDetails.price_hrk}</p>
                            </Col>
                        </Row> 
                        <Divider style={{borderTop:"1px solid #8a8a8a"}}/>
                        <Row>
                            <Col span={24}>
                                <p style={{display: "inline-block", marginRight:"5px"}}>{translation.productAvailability[locale]}</p>
                                <Availability isAvailable={productDetails.isAvailable} />
                            </Col>
                        </Row>
                        <Row>
                            <Col span={4}>
                                <InputNumber min={1} defaultValue={1}/>
                            </Col>
                            <Col span={19} offset={1}>
                                <Button type="primary" block className="add-to-basket">
                                    {translation.addToBasket[locale]}
                                </Button>
                            </Col>
                        </Row>
                    </div>

                    {/* <React.Fragment>
                        <p>Name: {productDetails.name}</p>
                        <p>Description: {productDetails.description}</p>
                        <p>Price: {productDetails.price_hrk}</p>
                        <p>Availability: {productDetails.isAvailable.toString()}</p>
                    </React.Fragment> */}
                </Col>
            </Row>
            
        );
    }

    useEffect(()=>{
        getProduct();
    }, [])

    return (
        <div>
            { isLoadingProduct === false && productDetails !== null ? description() : () => (<Skeleton />)}
        </div>
    );
}

export default withRouter(ProductPage);