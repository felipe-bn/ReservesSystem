import React from "react";
import "../Assets/Styles/PhotosComponentStyles.css"
function PhotosComponent(props){
    return(
        <div className="photo-container">
            <img src={props.src} alt={props.alt} className="image"></img>
        </div>
    );
}

export default PhotosComponent;