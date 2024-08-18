import React from "react";
import '../Assets/Styles/HeaderComponentStyles.css'

function HeaderComponent(){
    return(
        <header className="header-container">
            <a href="#">Log-in</a>
            <a href="#">Create account</a>
        </header>
    );
}

export default HeaderComponent;