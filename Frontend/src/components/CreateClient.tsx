import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

export function CreateClient() {
  const [errorMessage, setErrorMessage] = useState([]);
  const navigation = useNavigate();
  let token = document.cookie.split('; ').find(row => row.startsWith('token='));
  token = token.substring(6);
 function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const name = formData.get('name') as string;
    const surname = formData.get('surname') as string;
    const email = formData.get('email') as string;

    // Simple data validation
    if (!name.trim() || !email.trim() || !surname.trim()) {
      setErrorMessage(['Please fill in all fields']);
      return;
    }
    
   
    fetch('http://localhost:8080/api/clients/save', {
      method: 'POST',
      headers:{
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token ? token : ''}`
      },
      body: JSON.stringify({ name, surname, email }),
    })
      .then(async (response) => {
        if (response.status === 400) {
          const errory = await response.json();
          setErrorMessage(errory);
          console.log(errory);
        }
        return response.json();
      })
      .then((data) => {
        // Optionally handle success, e.g., redirect to login page
        navigation('/clients');
      })
      .catch((error) => {
        
      });
    
    
    // Send your request with username, email, and password
    
    }

  return (
    <>
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Add client</h2>
        {errorMessage && (
    <div style={{ color: 'red', textAlign: 'center' }}>
        {errorMessage.map((item, index) => (
            <p key={index}>{item}</p>
        ))}
    </div>
)}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto">
          <Form.Group controlId="formName">
            <Form.Label>Name</Form.Label>
            <Form.Control type="text" name="name" placeholder="Enter client name" />
          </Form.Group>

          <Form.Group controlId="formSurname">
            <Form.Label>Surname</Form.Label>
            <Form.Control type="text" name="surname" placeholder="Enter client surname" />
          </Form.Group>

          <Form.Group controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" name="email" placeholder="Enter email" />
          </Form.Group>


          <br />
          <div className="text-center">
            <Button variant="primary" type="submit">
              Add
            </Button>
          </div>
        </Form>
      </div>
    </>
  );
}
