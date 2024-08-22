import react from "react";
import '../App.css'
import LoginFormComponent from "../Components/LoginFormComponent";
import background from '../Assets/background.jpg'
function LoginPage(){
    return(
        <main className="main" id="login-page">
            <LoginFormComponent></LoginFormComponent>
            <img src={background} className="login-img"></img>
        </main>
    )
}
export default LoginPage;