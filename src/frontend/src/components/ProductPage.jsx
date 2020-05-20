import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import { Skeleton, Row, Col, Divider, InputNumber, Button } from 'antd';
import { LocalizationContext } from '../util/LocalizationContext';
import { BasketContext } from '../util/BasketContext';

import translation from '../translations/translations.json';

import '../App.css';

function ProductPage(props) {

    let productId = props.match.params.id;

    const { locale } = React.useContext(LocalizationContext);
    const basketContext = React.useContext(BasketContext);

    const [productDetails, setProductDetails] = useState(null);
    const [productCount, setProductCount] = useState(1);
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

    const addToBasket = () => {
        basketContext.updateCount(basketContext.productCount + productCount);
    }

    const Description = () =>{
        return(
            <Row>
                <Col md={{span:12}} xs={{span:24}}>
                    <img alt="example" src="https://www.gizmochina.com/wp-content/uploads/2020/02/Samsung-Galaxy-S20-Plus-500x500.jpg" />
                </Col>
                <Col md={{span:12}} xs={{span:24}}>
                    <div className="product-page-container">
                        <Row>
                            <Col>
                                <h2>{productDetails.name}</h2>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <p className="paragraph-inline-larger">{translation.price[locale]}</p>
                                <p className="paragraph-inline-larger">{`${productDetails.price_hrk} HRK`}</p>
                            </Col>
                        </Row> 
                        <Divider style={{borderTop:"1px solid #8a8a8a"}}/>
                        <Row>
                            <Col span={24}>
                                <p className="paragraph-inline">{translation.productAvailability[locale]}</p>
                                <Availability isAvailable={productDetails.isAvailable} />
                            </Col>
                        </Row>
                        <Row>
                            <Col span={4}>
                                <InputNumber min={1} defaultValue={1} onChange={setProductCount}/>
                            </Col>
                            <Col span={19} offset={1}>
                                <Button type="primary" block className="add-to-basket" onClick={addToBasket}>
                                    {translation.addToBasket[locale]}
                                </Button>
                            </Col>
                        </Row>
                    </div>
                </Col>
            </Row>
            
        );
    }

    useEffect(()=>{
        getProduct();
    }, [])

    return (
        <div>
            { isLoadingProduct === false && productDetails !== null 
                ? <Description /> 
                : <Skeleton />}
        </div>
    );
}

export default withRouter(ProductPage);