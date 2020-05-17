import React from 'react';
import { useRecoilState, atom} from 'recoil';
import { Layout,  Input, Row, Col, Badge, Avatar, Button } from 'antd';

import { ShoppingCartOutlined, UserOutlined, SearchOutlined } from '@ant-design/icons';

import 'antd/dist/antd.css';

import '../App.css';

import logo from '../assets/logo-2.svg';


export default function Header(props) {

    const SearchButton = () => {
        return(
            <Button  icon={<SearchOutlined />}>
                Search
            </Button>
        )
    }

    const search = value => {
        console.log("Header render");
        props.searchCallback(value);
    }

    return (
        <Layout.Header className="header-margin">
            <Row className="content-width" >
                <Col md={4} xs={6}>
                    <a href="/">
                        <div className="logo" />
                    </a>

                </Col>
                <Col md={{ span: 10, offset: 3 }} xs={16}>
                    <Input.Search
                        placeholder="Search"
                        className="search-margin"
                        enterButton
                        onSearch={value => search(value)} />
                </Col>
                <Col md={{ span: 1, offset: 4 }}>
                    <a href="#" >
                        <Badge count={2}>
                            <Avatar
                                icon={<ShoppingCartOutlined style={{ fontSize: "1.4em", verticalAlign: "middle" }} />}
                                size={32}
                                style={{ background: "none" }} />
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