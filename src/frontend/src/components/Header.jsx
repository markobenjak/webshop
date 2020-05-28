import React from 'react';
import { LocalizationContext } from '../util/LocalizationContext';
import { BasketContext } from '../util/BasketContext';
import { UserContext } from '../util/UserContext';
import { Layout, Input, Row, Col, Badge, Avatar, Button, Menu, Dropdown } from 'antd';
import { DownOutlined } from '@ant-design/icons';

import {Link, useHistory} from 'react-router-dom';

import { ShoppingCartOutlined, UserOutlined } from '@ant-design/icons';

import LanguageDropdown from './LanguageDropdown';

import 'antd/dist/antd.css';
import '../App.css';

import translations from '../translations/translations.json';


export default function Header(props) {

    const user = React.useContext(UserContext);
    const { locale } = React.useContext(LocalizationContext);
    const { productCount } = React.useContext(BasketContext);

    const history = useHistory();

    const search = value => {
        console.log("Header render");
        props.searchCallback(value);
    }

    console.log(user);

    const route = {
        to: user.value !== null ? "/customer" : "/login"
    }

    const logout = () => {
        user.update(null);
        window.localStorage.clear("user");
        history.push("/");
    }

    const menu = (
        <Menu >
          <Menu.Item key="1" onClick={logout}>
            {translations.logoff[locale]}
          </Menu.Item>
        </Menu>
      );

    return (
        <Layout.Header className="header-margin">
            <Row className="content-width" >
                <Col md={4} xs={6}>
                    <Link to="/">
                        <div className="logo" />
                    </Link>

                </Col>
                <Col md={{ span: 10, offset: 3 }} xs={16}>
                    <Input.Search
                        placeholder={translations.search[locale]}
                        className="search-margin"
                        enterButton
                        onSearch={value => search(value)} />
                </Col>
                <Col md={{span:1, offset:2}}>
                    <LanguageDropdown />
                </Col>
                <Col md={{ span: 1, offset: 1 }}>
                    <Link to="/basket" >
                        <Badge count={productCount} showZero={true}>
                            <Avatar
                                icon={<ShoppingCartOutlined style={{ fontSize: "1.4em", verticalAlign: "middle" }} />}
                                size={32}
                                style={{ background: "none" }} />
                        </Badge>
                    </Link>
                </Col>
                <Col span={1} >
                    <Link {...route} >
                        <Avatar icon={<UserOutlined />} />
                    </Link>
                    
                </Col>
                {
                    user.value && 
                    <Col span={1}>
                        <Dropdown overlay={menu}>
                            <Button>
                                <DownOutlined />
                            </Button>
                        </Dropdown>
                    </Col>
                }
                

            </Row>
        </Layout.Header>
    )
}