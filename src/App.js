import React, { useState } from 'react'
import Error from './Error'
import { Route, Switch } from "react-router-dom";
import Student from './Student';
import University from './University';
import Main from './Main';
import './index.css'
import LoginPage from './components/LoginPage'
import SignupPage from './components/SignupPage'
const App = () => {
    const [view, setView] = useState(false)
    const [userName, setName] = useState("")
    const handleLogin = () => setView(true)
    const handleLogout = () => setView(false)
    const changeName = (a) => setName(a)
    

    return (
        <>
            <Main name = {userName} in = {view} logout = {handleLogout}/>
            <Switch>

                <Route exact path="/" component={() => <LoginPage auth={handleLogin} nameMethod={changeName}/>} />
                <Route exact path="/signup" component={() => <SignupPage />} />
                <Route exact path="/login" component={() => <LoginPage auth={handleLogin} nameMethod={changeName} />} />
                <Route exact path="/student/" component={() => <Student auth={view} />} />
                <Route exact path="/university/" component={() => <University auth={view} />} />
                <Route component={Error} />
            </Switch>

        </>

    )
}
export default App;