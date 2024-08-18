import './App.css';
import HomePage from './Pages/HomePage.';
import reserves from '../src/Assets/Img/reserves.jpg'
import date from '../src/Assets/Img/date.jpg'
import place from '../src/Assets/Img/place.jpg'
import HeaderComponent from './Components/HeaderComponent';
import FooterComponent from './Components/FooterComponent';

function App() {
  return (
    <div className="App">
      <HeaderComponent></HeaderComponent>
      <HomePage title="Appointment Scheduling System"
      message="Manage all your appointments and connect with trusted professionals effortlessly."
      direction="row"
      src={reserves}
      alt="Image used for references simulating a reserve in the page"
      ></HomePage>
      <HomePage title="Make your reservations for the available date of your choice"
      message="Easily book your appointments by selecting from available dates and times. Our system ensures a smooth and efficient scheduling experience."
      direction="row-reverse"
      src={date}
      alt="Image used for references simulating a date in the page"
      ></HomePage>
      <HomePage title="Providing you with services near you"
      message="Offering convenient access to local services, making it easier for you to find and book appointments with professionals nearby."
      direction="row"
      src={place}
      alt="Image used for references simulating a person who selecting a place in the page"
      ></HomePage>
      <FooterComponent></FooterComponent>
    </div>
  );
}

export default App;
