import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';

function ProductPage(props) {

    let productId = props.match.params.id;

    const [productDetails, setProductDetails] = useState(null);

    const getProduct = () => {
        fetch(`/api/product/${productId}`)
        .then(res => res.json())
        .then(data => {
            console.log(data);
            setProductDetails(data);
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
            { productDetails && description()}
        </div>
    );
}

export default withRouter(ProductPage);