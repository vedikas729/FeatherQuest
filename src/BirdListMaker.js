import { useState } from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const BirdListMaker = ({ onFetchComplete }) => {

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
            <div className="topheader">
                <div className="rectangle">
                    <h2>FeatherQuest</h2>
                </div>
            </div>'
            <bodyofpage>
                <table>
                    <tr>
                        <howitworks>
                            <h1>How It Works</h1>
                            <h3>
                                FeatherQuest makes it easy to plan a birding
                                excursion in a simple 3-step process: <br />
                                <ol>
                                    <li>
                                        Type in the address that you want to start your excursion
                                        from (e.g. your home address)
                                    </li>
                                    <li>
                                        Enter what radius (in miles) around that starting address
                                        you would like to travel within
                                    </li>
                                    <li>
                                        Select up to 5 birds you would like to see on your trip
                                    </li>
                                    <li>
                                        Click on the generated Google Maps route and go birding!
                                    </li>
                                </ol>
                                FeatherQuest looks up the eBird database to identify the most
                                likely locations for each of your targets and generates an optimized
                                Google Maps route for you that minimizes driving effort.
                            </h3>
                        </howitworks>                
                    </tr>
                    <tr>
                        <formbox>
                            <form onSubmit={handleSubmit}>
                                <h1>Starting Address</h1>
                                <input
                                    type="text"
                                    required
                                    value={address}
                                    onChange={(e) => setAddress(e.target.value)}
                                />
                                <h1>Radius</h1>
                                <h4>in miles, a number from 0-30</h4>
                                <input
                                    type="text"
                                    required
                                    value={radius}
                                    onChange={(e) => setRadius(e.target.value)}
                                />
                                <button type="submit" disabled={isLoading}>{isLoading ? 'LOADING...' : 'CONTINUE'}</button>
                            </form>
                        </formbox>
                    </tr>
                </table>
            </bodyofpage>

        </div >
    )
}

export default BirdListMaker;