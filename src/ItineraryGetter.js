import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Select from 'react-select';

// eslint-disable-next-line
import { useParams, useLocation, useNavigate } from 'react-router-dom';

const ItineraryGetter = () => {
    const location = useLocation();
    const birdData = location.state?.birdData;
    const radius = location.state?.radius;
    const [options, setOptions] = useState([]);
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [chosenBirds, setChosenBirds] = useState([]);
    const [coordinate, setCoordinate] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const apiKey = process.env.REACT_APP_API_KEY;

    // Function to fetch bird data
    const fetchBirdData = async () => {
        setIsLoading(true);
        setError(null);

        try {
            const requestBody = {
                chosenBirds,
                coordinate,
                radius
            };

            const axiosInstance = axios.create({
                paramsSerializer: {
                    serialize: (params) => JSON.stringify(params)
                }
            });

            const response = await axiosInstance.post(
                'https://xim382clg7.execute-api.us-east-1.amazonaws.com/featherquest/get-itinerary',
                requestBody,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'x-api-key': apiKey
                    }
                }
            );

            console.log(response.data);
            navigate('/final-itinerary', {
                state: { 
                    itineraryData: response.data, 
                }
            });
        } catch (error) {
            console.error('Error:', error.message);
            setError(error.message);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        if (!birdData) {
            setOptions([]);
            return;
        }

        const parsedData = Object.entries(birdData.birdList).map(([key, value]) => ({
            speciesCode: key,
            comName: value,
        }));
        setOptions(parsedData);
        setCoordinate(birdData.coordinate || []);
    }, [birdData]);

    const handleChange = (selectedOptions) => {
        setSelectedOptions(selectedOptions);
        const newChosenSpecies = selectedOptions.map(option => option.speciesCode);
        setChosenBirds(newChosenSpecies);
    };

    const maxLimit = 5; // Maximum number of birds allowed

    return (
        <div className="itinerarygetter">
            <h1>Select Your Birds</h1>
            <h2>You may select up to 5 species.</h2>
            <Select
                isMulti
                name="birdSpecies"
                options={options}
                value={selectedOptions}
                onChange={handleChange}
                placeholder="Select bird species..."
                noResultsText="No results found"
                loadingMessage="Loading..."
                getOptionLabel={(option) => option.comName} // This displays only comName
                getOptionValue={(option) => option.speciesCode} // This sets the value to speciesCode
                isOptionDisabled={(option) => selectedOptions.length >= maxLimit && !selectedOptions.includes(option)}
            />
            {selectedOptions.length > 0 && (
                <div>
                    <h3>Selected Bird Species:</h3>
                    <ul>
                        {selectedOptions.map((option) => (
                            <li key={option.speciesCode}>{option.comName}</li>
                        ))}
                    </ul>
                </div>
            )}

            {/* Add a button to trigger API call */}
            {isLoading ? (
                <button onClick={fetchBirdData} disabled>{isLoading ? 'Loading...' : 'Refresh Data'}</button>
            ) : error ? (
                <p>Error: {error}</p>
            ) : (
                <>
                <button onClick={fetchBirdData}>Plan My Quest</button>
                <p></p>
                <button onClick={() => navigate(-1)}>Restart</button>
                </>
            )}
        </div>
    );
};

export default ItineraryGetter;