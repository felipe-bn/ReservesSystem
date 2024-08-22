import React from "react";
import '../App.css'
function PageButtons(props){
    const defaultColor = '#22223b'
    const defaultColorTxt = 'white'
    const AbstractStyles = {
        backgroundColor: props.color || defaultColor,
        color: props.font || defaultColorTxt,
        width: props.width || 'auto',
        height: props.height || 'auto'
    }
    return(
        <button className="page-button" style={AbstractStyles} onClick={props.onClick}>
            <p>{props.message}</p>
        </button>
    );
}

export default PageButtons;