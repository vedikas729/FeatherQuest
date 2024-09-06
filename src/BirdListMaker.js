import { useState } from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const BirdListMaker = ({onFetchComplete}) => {

    const [address, setAddress] = useState('');
    const [radius, setRadius] = useState('');
    
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const apiKey = process.env.REACT_APP_API_KEY;

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);

        try {
            // eslint-disable-next-line
            const requestBody = {
                address,
                radius
            };

            const axiosInstance = axios.create({
                paramsSerializer: {
                    serialize: (params) => JSON.stringify(params)
                }
            });

            const response = await axiosInstance.post(
                'https://xim382clg7.execute-api.us-east-1.amazonaws.com/featherquest/get-birdlist-coordinates',
                
                requestBody
                , 
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'x-api-key': apiKey // Replace with your actual API key
                    }
                }
            );

            console.log(response.data);
            onFetchComplete(response.data);
            navigate('/itinerary', {
                state: { 
                    birdData: response.data, 
                    radius: radius // Include the radius in the state
                }
            });
        } catch (error) {
            console.error('Error:', error.message);
            // Handle error (you might want to show an error message to the user)
        } finally {
            setIsLoading(false);
        }
    }


    return (
        <div className="birdlistmaker">
            <h1>FeatherQuest</h1>
            <h2>Get Started</h2>
            <form onSubmit={handleSubmit}>
                <label>Address:</label>
                <input
                    type="text"
                    required
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                />
                <label>Radius:</label>
                <input
                    type="text"
                    required
                    value={radius}
                    onChange={(e) => setRadius(e.target.value)}
                />
                <button type="submit" disabled={isLoading}>{isLoading ? 'Loading...' : 'Next'}</button>
            </form>

        </div>
    )
}

export default BirdListMaker;