import React, { useEffect, useState } from 'react';
import ajax from '../Services/fetchService';
import { Card } from 'react-bootstrap';




const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [assignments, setAssignments] = useState(null);

    useEffect(() => {
        ajax("api/assignments",jwt,"GET").then((assignmentsData) => {
            setAssignments(assignmentsData);
        });
    },[]);

    function createAssignment(){
        ajax("api/assignments",jwt,"POST").then((assignment) => {
            window.location.href = `/assignments/${assignment.id}`;
        });
    }

    return (
        <div style={{margin: "2em"}}>
           {assignments ? assignments.map(assignment =>
           <div key={assignment.id}>
                <Card style={{ width: '18rem' }}>
                    <Card.Body>
                        <Card.Title>Card Title</Card.Title>
                        <Card.Subtitle className="mb-2 text-muted">Card Subtitle</Card.Subtitle>
                        <Card.Text>
                            Some quick example text to build on the card title and make up the
                            bulk of the card's content.
                        </Card.Text>
                        <Card.Link href="#">Card Link</Card.Link>
                        <Card.Link href="#">Another Link</Card.Link>
                    </Card.Body>
                </Card>
            </div>
           ):(
           <></>
           )} 
           <button onClick={() => createAssignment()}>Submit New Assignment</button>
        </div>
    );
};

export default Dashboard;