// eslint-disable-next-line
import React, { useEffect, useState, useRef } from 'react';
import { useLocation, Link, useNavigate } from 'react-router-dom';

const FinalItinerary = () => {
    const location = useLocation();
    const itineraryData = location.state?.itineraryData;
    const googleMapsLink = itineraryData.googleMapsLink;
    const itineraryWritten = itineraryData.itinerary;
    const navigate = useNavigate();

    return (
        <div className="finalitinerary">
            <div className="topheader">
                <div className="rectangle">
                    <h2>FeatherQuest</h2>
                </div>
            </div>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <h1>Final Itinerary</h1>
            <pre>{itineraryWritten}</pre>
            <h3>Google Maps Link:</h3>
            <Link to={googleMapsLink}>Click Here</Link>
            <pre></pre>
            <button onClick={() => window.history.back()}>CHANGE BIRDS</button>
            <pre></pre>
            <button onClick={() => navigate('/FeatherQuest')}>PLAN A NEW QUEST</button>
        </div>
    );
};

export default FinalItinerary;