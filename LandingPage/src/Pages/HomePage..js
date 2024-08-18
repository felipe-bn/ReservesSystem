import React from "react"
import "../Assets/Styles/HomePageStyles.css"
import PhotosComponent from "../Components/PhotosComponent";

function HomePage(props){
    const mainStyle ={
        flexDirection: props.direction || "row"
    }
    return(
        <main className="main" style={mainStyle}>
            <section className="info-container">
                <h1>{props.title}</h1>
                <p>{props.message}</p>
            </section>
            <PhotosComponent src={props.src} alt={props.alt}></PhotosComponent>
        </main>
    )
}

export default HomePage;