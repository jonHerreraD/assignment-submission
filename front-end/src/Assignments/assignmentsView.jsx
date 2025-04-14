import React, { useEffect, useState } from 'react';
import { useLocalState } from '../util/useLocalStorage';
import ajax from '../Services/fetchService';
import { Button, Form, Row, Col, Container, DropdownButton, Dropdown } from 'react-bootstrap';

const assignmentsView = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const assignmentId = window.location.href.split("/assignments/")[1];
    const [assignment, setAssignment] = useState({
        branch: "",
        githubUrl: "",
    });
    
    function updateAssignment(prop, value){
        const newAssignment = {...assignment};
        newAssignment[prop] = value;
        setAssignment(newAssignment);
    }

    function save () {
        ajax(`/api/assignments/${assignmentId}`,jwt,"PUT",assignment).then((assignmentData) => {
            setAssignment(assignmentData);
        })
    }

    useEffect(() => {
        ajax(`/api/assignments/${assignmentId}`,jwt,"GET").then((assignmentData) => {
            if(assignmentData.branch === null)assignment.branch = "";
            if(assignmentData.githubUrl === null)assignment.githubUrl = "";
            setAssignment(assignmentData);
        });
    },[])
    
    return (
        <Container className='mt-5'>
            <Row className='d-flex'>
                <Col className='align-content-center'>
                <h1>Assignment {assignmentId}</h1>
                </Col>
                <Col className='align-contetn-center'>
                <Badge pill bg="info" style={{fontSize: "1em"}}>
                {assignment.status}
                </Badge>
                </Col>
            </Row>
            {assignment ? (
                <>
                    <Form.Group as={Row} className="my-3" controlId="formPlaintextEmail">
                        <Form.Label column sm="3" md="2">
                        Assignment Number
                        </Form.Label>
                        <Col sm="9" md="8" lg="6">
                        <DropdownButton
                            as={ButtonGroup}
                            
                            id="assignmentName"
                            variant={"info"}
                           
                        >
                        {['1','2','3','4','5','6'].map((assignmentNum) => (
                            <Dropdown.Item eventKey={assignmentNum}>
                                {assignmentNum}
                            </Dropdown.Item>
                        ))}
                            
                        </DropdownButton>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="my-3" controlId="formPlaintextEmail">
                        <Form.Label column sm="3" md="2">
                        Github URL:
                        </Form.Label>
                        <Col sm="9" md="8" lg="6">
                        <Form.Control id = 'githubUrl'
                        onChange={(e) => updateAssignment("githubUrl" , e.target.value)} 
                        type='url'
                        value={assignment.githubUrl} 
                        placeholder='https://github.com/username/repo-name' />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                        <Form.Label column sm="3" md="2">
                        Branch:
                        </Form.Label>
                        <Col sm="9" md="8" lg="6">
                        <Form.Control id = 'branch'
                        onChange={(e) => updateAssignment("branch" , e.target.value)} 
                        type='text'
                        value={assignment.branch} 
                        placeholder='branch-name' />
                        </Col>
                    </Form.Group>
                    
                    <h3>Branch: <input type='text' id='branch'onChange={(e) => updateAssignment("branch", e.target.value)} value={assignment.branch}/></h3>
                    <Button size='lg' onClick={() => save()}>Submit Assignment</Button>
                </>
            ) : (
                <></>
            )}
            
        </Container>
    );
};

export default assignmentsView;