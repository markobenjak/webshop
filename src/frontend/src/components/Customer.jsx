import React, { useEffect, useState } from 'react';

import translation from '../translations/translations.json';

import '../App.css';
import {LocalizationContext} from "../util/LocalizationContext";
import {Button, Col, Row, Skeleton} from "antd";

export default function Customer(props){
    const customerId = 1;

    const { locale } = React.useContext(LocalizationContext);

    const [customerDetails, setCustomerDetails] = useState(null);
    const [isLoadingCustomer, setIsLoadingCustomer] = useState(false);

    const getCustomer = () => {
        setIsLoadingCustomer(true);

        fetch(`/api/customer/${customerId}`)
            .then(res => res.json())
            .then(data => {
                console.log('Kastomer: ');
                console.log(data);
                setCustomerDetails(data);
                setIsLoadingCustomer(false);
            });
    }

    const profileData = () =>{
        return(
            <Row>
                <Col md={{ span: 4, offset: 4 }}>
                    <img alt="example" width="400" src="https://www.haber.ba/wp-content/uploads/2015/09/Jason-Statham.jpg" />
                </Col>
                <Col md={{ span: 6, offset: 5 }}>
                    <div style={{marginLeft:"10px", marginRight:"10px", padding:"10px",backgroundColor:"#ededed", width:"100%"}}>
                        <Row>
                            <Col>
                                <p style={{display: "inline-block", marginRight:"5px", fontSize:"1.2em"}}>{translation.customerName[locale]}{'\u00A0'}{customerDetails.first_name}</p>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <p style={{display: "inline-block", marginRight:"5px", fontSize:"1.2em"}}>{translation.customerSurname[locale]}{'\u00A0'}{customerDetails.last_name}</p>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <p style={{display: "inline-block", marginRight:"5px", fontSize:"1.2em"}}>Email:{'\u00A0'}{customerDetails.email}</p>
                            </Col>
                        </Row>
                    </div>
                    <Row>
                        <Col md={{ span: 10, offset: 14 }}>
                            <Button type="primary" block className="edit-profile" style={{margin: "10px"}}>
                                {translation.editCustomerData[locale]}
                            </Button>
                        </Col>
                    </Row>
                </Col>
            </Row>
        );
    }

    useEffect(()=>{
        getCustomer();
    }, [])

    return(
        <div>
            { isLoadingCustomer === false && customerDetails !== null ? profileData() : () => (<Skeleton />)}
        </div>
    )
}