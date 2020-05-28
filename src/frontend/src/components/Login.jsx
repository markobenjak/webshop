import React, { useEffect, useState } from 'react';
import { Layout, Row, Col, Form, Button, Input, Checkbox } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';

import { Link, useHistory } from 'react-router-dom';

import { UserContext } from '../util/UserContext';

import '../App.css';
import translations from '../translations/translations.json';




export default function Login(props){
    const context = React.useContext(UserContext);

    const history = useHistory();

    const formLayout = {
        labelCol: { span: 8 },
        wrapperCol: { span: 16 },
      };

    const parseJwtToken = value => {
        console.log(value);
        let { token } = value
        
        let tokenParts = token.split(".");

        let decoded = atob(tokenParts[1]);

        console.log(context);

        context.update(decoded);

        console.log(context);

        window.localStorage.setItem("user", decoded);

        history.push("/");
    }

    const onFinish = values => {
        fetch('/api/auth/authenticate',{
            body:JSON.stringify(values),
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
            
        })
        .then(res => {
            console.log(res);
            if(res.status === 200)
                res.json().then(data => parseJwtToken(data))
        });
        
    };

    return (
        <Layout.Content >
            <div className="site-layout-content layout-form">
                <Row>
                    <Col span={12}>
                        <img src="https://www.stocksharksnews.com/wp-content/uploads/2020/03/1585426049_A-new-era-for-consumer-tech-StockSharksNewsh-600x394.jpg"  alt="loginImg"/>
                    </Col>
                    <Col span={12}>
                        <Row>
                            <Col>
                                <h3>Login</h3>
                            </Col>
                        </Row>
                        <Row>
                            <Col id="form-login">
                            <Form
                                name="normal_login"
                                className="login-form"
                                initialValues={{ remember: true }}
                                onFinish={onFinish}
                                >
                                <Form.Item
                                    name="username"
                                    rules={[{ required: true, message: 'Please input your Username!' }]}
                                >
                                    <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
                                </Form.Item>
                                <Form.Item
                                    name="password"
                                    rules={[{ required: true, message: 'Please input your Password!' }]}
                                >
                                    <Input
                                    prefix={<LockOutlined className="site-form-item-icon" />}
                                    type="password"
                                    placeholder="Password"
                                    />
                                </Form.Item>
                                <Form.Item>
                                    <Form.Item name="remember" valuePropName="checked" noStyle>
                                    <Checkbox>Remember me</Checkbox>
                                    </Form.Item>

                                    <a className="login-form-forgot" href="">
                                    Forgot password
                                    </a>
                                </Form.Item>

                                <Form.Item>
                                    <Button type="primary" htmlType="submit" className="login-form-button">
                                    Log in
                                    </Button>
                                    Or <a href="">register now!</a>
                                </Form.Item>
                                </Form>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
        </Layout.Content>
    )
}