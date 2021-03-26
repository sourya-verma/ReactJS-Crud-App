import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.css";
const AddForm = (props) => {

    const validateUniversity = function (name) {
        if (name === null || name === "") {

            return false
        }
        else {
            const re = /^([a-zA-Z\s]+)$/;
            return re.test(String(name));
        }

    }

    const validateEmail = function (email) {
        const re = /^([a-zA-Z\d+)(\.[a-zA-Z\d]+)?@([a-zA-Z\d]+).([a-zA-Z]{2,8})(\.[a-zA-Z\d]+)?$/;
        return re.test(String(email).toLowerCase());
    }
    const validateName = function (name) {
        if (name === null || name === "") {
            return false
        }
        else {
            const re = /^([a-zA-Z\s]+)$/;
            return re.test(String(name).toLowerCase());
        }

    }


    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [uname, setUniversity] = useState("");
    async function postData() {

        if (name === '' || uname === '' || email === '') {
            alert('fields can not be blank');
        }
        else {
            if (!validateName(name) || !validateEmail(email) || !validateUniversity(uname)) {
                alert('information is not in correct format')
            }
            else {
                try {
                    await fetch('http://localhost:8080/create/', {
                        method: 'post',
                        headers: {
                            'Accept': 'application/json',
                            'Content-type': 'application/json'
                        },
                        body: JSON.stringify({ "id": 0, "name": `${name}`, "email": `${email}`, "universityName": `${uname}` })

                    });

                }
                catch (e) {
                    console.log(e);
                }
            }


        }
        props.hideMethod();
        props.dataVal();
    }
    useEffect(() => {
    })
    return (
        <div className="modal" id="myModal" title = "myModal">
            <div className="modal-dialog modal-content modal-lg">
                <div className="modal-content">
                    <div className="modal-header bg-success">
                        <h4 className="modal-title">Add New Studend </h4>
                        <button tilte = "close-addform-btn" type="button" className="close" data-dismiss="modal" onClick={() => {
                            props.hideMethod();
                        }}>&times;</button>
                    </div>
                    <div className="modal-body">
                        <div className="form-group">
                            <label htmlFor="name">Name:</label>
                            <input type="text" className="form-control" id="email" placeholder="Enter Name" value={name} onChange={(e) => setName(e.target.value)} />

                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email:</label>
                            <input type="email" className="form-control" id="email" placeholder="Enter Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="uname">University:</label>
                            <input type="text" className="form-control" id="email" placeholder="Enter University Name" value={uname} onChange={(e) => setUniversity(e.target.value)} />
                        </div>
                        <div style={{ float: 'left' }} className="modal-footer">
                            <button onClick={() => {
                                postData();
                            }} type="button" className="btn btn-outline-primary" data-dismiss="modal">submit</button>
                        </div>
                        <div className="modal-footer">
                            <button onClick={() => {
                                props.hideMethod();
                            }} type="button" className="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    );
};
export default AddForm;