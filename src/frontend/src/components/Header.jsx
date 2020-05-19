import React from 'react';
import { LocalizationContext } from '../util/LocalizationContext';
import { Layout,  Input, Row, Col, Badge, Avatar, Button } from 'antd';

import {Link} from 'react-router-dom';

import { ShoppingCartOutlined, UserOutlined, SearchOutlined } from '@ant-design/icons';

import LanguageDropdown from './LanguageDropdown';

import 'antd/dist/antd.css';
import '../App.css';

import translations from '../translations/translations.json';


export default function Header(props) {

    const { locale } = React.useContext(LocalizationContext);

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
                        placeholder={translations.search[locale]}
                        className="search-margin"
                        enterButton
                        onSearch={value => search(value)} />
                </Col>
                <Col md={{span:1, offset:2}}>
                    {/* Shit's broken yo */}
                    <LanguageDropdown />
                </Col>
                <Col md={{ span: 1, offset: 1 }}>
                    <Link to="/basket" >
                        <Badge count={2}>
                            <Avatar
                                icon={<ShoppingCartOutlined style={{ fontSize: "1.4em", verticalAlign: "middle" }} />}
                                size={32}
                                style={{ background: "none" }} />
                        </Badge>
                    </Link>
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