import React from 'react';
import { Layout, Menu, Input, Row, Col, Badge, Avatar, Card } from 'antd';

import { ShoppingCartOutlined, UserOutlined } from '@ant-design/icons';

import 'antd/dist/antd.css';

import '../App.css';




export default function Header(props){
    return(
        <Layout.Header className="header-margin">
            <Row className="content-width" >
                <Col md={4} xs={6}>
                    <div className="logo" />
                </Col>
                <Col md={{ span: 10, offset: 3 }} xs={16}>
                    <Input.Search
                        placeholder="Search"
                        className="search-margin"
                        enterButton
                        onSearch={value => console.log(value)} />
                </Col>
                <Col md={{ span: 1, offset: 4 }}>
                    <a href="#" >
                        <Badge count={2}>
                            <Avatar icon={<ShoppingCartOutlined style={{ fontSize: "1.4em", verticalAlign: "middle" }} />} size={32} style={{ background: "none" }} />
                        </Badge>
                    </a>
                </Col>
                <Col span={1} offset={1} >
                    <a href="#" >
                        <Avatar icon={<UserOutlined />} />
                    </a>

                </Col>

            </Row>
        </Layout.Header>
    )
}