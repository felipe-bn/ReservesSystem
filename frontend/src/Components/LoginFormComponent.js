import React, { useState } from "react";
import '../Styles/LoginFormComponent.css';
import PageButtons from '../Components/PageButtons';
import AuthenticationService from "../Services/AuthenticationService"

function LoginFormComponent() {
    const [error, setError] = useState('');
    const [formData, setFormData] = useState({
        username: "",
        password: "",
    });
    const handleSubmit = async (e) => {
        e.preventDefault();
        const user = { username: formData.username, password: formData.password} = formData;

        try {
            const token = await AuthenticationService.login(user);
            localStorage.setItem('token', token);

            alert("Bienvenido");
        } catch (err) {
            console.error('Error:', err.response ? err.response.data : err.message);
            setError('Error de autenticación');
            alert(err.response?.data || 'Error desconocido');
        }
    };
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    return (
        <div className="login-container">
            <form onSubmit={handleSubmit}>
                <h2>Welcome</h2>
                <input 
                    placeholder="Enter your Username" 
                    className="form-input"
                    type="text"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                />
                <input 
                    placeholder="Enter your password" 
                    className="form-input"
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                />
                <PageButtons message="Log In" width="80%" height="40px" />
                <PageButtons message="Sign In" width="80%" height="40px" />
            </form>
        </div>
    );
}

export default LoginFormComponent;
