import React from 'react'
// import Student from './Student'
// import University from './University'
import { NavLink } from "react-router-dom";
import { Container, Typography, AppBar, Toolbar, Button } from '@material-ui/core'
import './index.css'
import Temp from './Temp'
import Temp1 from './Temp1'
import Temp2 from './Temp2'
export default function Main(props) {
    return (

        <Container >
            <AppBar>
                <Toolbar>
                    <Typography variant="h6" style = {{flexGrow:1}}>Home</Typography>
                    {props.in === true ? <Temp2 />:null}
{/* 
                    {props.in === false ? <Button color='inherit'><NavLink className="inactive" activeClassName="active" exact to = "/signup">Signup</NavLink></Button> : null}
                    {props.in === false ? <Button color='inherit'><NavLink className="inactive" activeClassName="active" exact to = "/login">Login</NavLink></Button> : null} */}

                    {props.in === false ? <Temp />:<Temp1 logout = {props.logout} name = {props.name}></Temp1>}
                    
                </Toolbar>

            </AppBar>
        </Container>




    )
}
