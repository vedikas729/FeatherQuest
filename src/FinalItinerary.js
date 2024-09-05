// eslint-disable-next-line
import React, { useEffect, useState, useRef } from 'react';
import { useLocation, Link } from 'react-router-dom';

const FinalItinerary = () => {
    const location = useLocation();
    const itineraryData = location.state?.itineraryData;
    const googleMapsLink = itineraryData.googleMapsLink;
    const itineraryWritten = itineraryData.itinerary;

    return (
        <div className="finalitinerary">
            <h1>Final Itinerary</h1>
            <pre>{itineraryWritten}</pre>
            <h3>Google Maps Link:</h3>
            <Link to={googleMapsLink}>Click Here</Link>
            <pre></pre>
            <button onClick={() => window.history.back()}>Back to Itinerary</button>
        </div>
    );
};

export default FinalItinerary;