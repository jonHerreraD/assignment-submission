import React, { useEffect, useState } from 'react';
import ajax from '../Services/fetchService';
import { Button, Card } from 'react-bootstrap';




const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [assignments, setAssignments] = useState(null);

    useEffect(() => {
        ajax("api/assignments",jwt,"GET").then((assignmentsData) => {
            setAssignments(assignmentsData);
        });
    },[jwt]);

    function createAssignment(){
        ajax("api/assignments",jwt,"POST").then((assignment) => {
            window.location.href = `/assignments/${assignment.id}`;
        });
    }

    return (
        <div style={{ margin: "2em" }}>
            <div className='mb-5'>
            <Button onClick={() => createAssignment()}>Submit New Assignment</Button>
            </div>
            
    {assignments ? (
      <div className='d-grid gap-5'
      style={{gridTemplateColumns: "repeat(auto-fit, 18rem)"}}>
        {assignments.map((assignment) => (
          <Card key={assignment.id} style={{ width: '18rem', height:'18rem' }}>
            <Card.Body className='d-flex flex-column justify-content-around'>
              <Card.Title>Assignment #{assignment.id}</Card.Title>
              <Card.Subtitle className="mb-2 text-muted">{assignment.status}</Card.Subtitle>
              <Card.Text>
                <p>
                  <b>GitHub URL</b>: {assignment.githubUrl}
                </p>
                <p>
                  <b>Branch</b>: {assignment.branch}
                </p>
              </Card.Text>
              <Button
              onClick={() => {
                window.location.href = `/assignments/${assignment.id}`;
              }}
              >
                Edit
              </Button>
            </Card.Body>
          </Card>
        ))}
        
      </div>
    ) : (
      <></>
    )}
  </div>
           
    );
};

export default Dashboard;