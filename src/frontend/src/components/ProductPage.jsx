import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import { Skeleton } from 'antd';

function ProductPage(props) {

    let productId = props.match.params.id;

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

    //Cuz I'm lazy
    const description = () =>{
        return(
            <React.Fragment>
                <p>Name: {productDetails.name}</p>
                <p>Description: {productDetails.description}</p>
                <p>Price: {productDetails.price_hrk}</p>
                <p>Availability: {productDetails.isAvailable.toString()}</p>
            </React.Fragment>
            
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