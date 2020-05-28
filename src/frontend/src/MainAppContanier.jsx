import React, {useEffect, useState} from 'react';
import {
    BrowserRouter as Router,
    Route,
    Switch
} from 'react-router-dom';

import Login from './components/Login';
import App from './App';

import { UserContext } from './util/UserContext';

export default function MainAppContainer(props){

    const [user, setUser] = useState(null);

    // useEffect(() => {
    //     let userLocal = JSON.parse(window.localStorage.getItem("user"));
    //     setUser({...userLocal});
    // }, [])

    const userCtxValue = {
        value: user && JSON.parse(window.localStorage.getItem("user")),
        update: value => setUser({...value})
    };

    return(
        <UserContext.Provider value={userCtxValue}>
            <Router>
                <Switch>
                    <Route path="/login" component={Login} />
                    <Route path="/" component={App} />
                </Switch>
            </Router>
        </UserContext.Provider>
    )
}