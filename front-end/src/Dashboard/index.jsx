import React from 'react';




const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    return (
        <div>
           <h1>Hello World</h1>
           <div>JWT value is {jwt}</div> 
        </div>
    );
};

export default Dashboard;