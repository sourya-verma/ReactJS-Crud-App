import React from 'react'
import './index.css'
import { Container, Typography, AppBar, Toolbar, Button } from '@material-ui/core'

export default function Temp1(props) {
    return (
        <>
         <Button className = 'inactive'>{props.name}</Button>   
        <Button onClick = {(e)=> props.logout(false)} className = 'inactive'>Logout</Button>
        </>
    )
}
