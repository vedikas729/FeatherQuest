import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BirdListMaker from './BirdListMaker';
import ItineraryGetter from './ItineraryGetter';
import FinalItinerary from './FinalItinerary';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/FeatherQuest" element={
          <BirdListMaker onFetchComplete={(data) => console.log(data)}/>} />
        <Route path="/itinerary" element={<ItineraryGetter />} />
        <Route path="/final-itinerary" element={<FinalItinerary />} />
      </Routes>
    </Router>
  );
}

export default App;