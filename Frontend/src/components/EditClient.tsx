import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';


// Import statements...

export function EditClient() {
    const { client_id } = useParams();
    const [errorMessage, setErrorMessage] = useState([]);
    const navigation = useNavigate();
    const [client, setClient] = useState(null);
    let token = document.cookie.split('; ').find(row => row.startsWith('token='));
    token = token.substring(6)
    useEffect(() => {
      const fetchClientData = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/clients/${client_id}`,{
            method: 'GET',
            headers:{
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token ? token : ''}`
            }}
          );
          if (!response.ok) {
            throw new Error('Failed to fetch client data');
          }
          const clientData = await response.json();
          setClient(clientData);
        } catch (error) {
          console.error('Error fetching client data:', error);
        }
      };
  
      if (client_id != undefined && client_id != null) {
        fetchClientData();
      }
    }, [client_id]);
  
    const handleSubmit = async (event) => {
      event.preventDefault();
      const formData = new FormData(event.currentTarget);
      const name = formData.get('name') as string;
      const surname = formData.get('surname') as string;
      const email = formData.get('email') as string;
  
      if (!name.trim() || !email.trim() || !surname.trim()) {
        setErrorMessage(['Please fill in all fields']);
        return;
      }
  

      fetch(`http://localhost:8080/api/clients/edit/${client_id}`, {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token ? token : ''}`
        },
        body: JSON.stringify({ id:client_id, name, surname, email }),
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
    };

  
   
  
    return (
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Edit client</h2>
        {errorMessage.length > 0 && (
          <div style={{ color: 'red', textAlign: 'center' }}>
            {errorMessage.map((item, index) => (
              <p key={index}>{item}</p>
            ))}
          </div>
        )}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto">
        <Form.Group controlId="formName">
  <Form.Label>Name</Form.Label>
  <Form.Control
    type="text"
    name="name"
    value={client?.name}
    placeholder="Enter client name"
    onChange={(e) => setClient({ ...client, name: e.target.value })}
  />
</Form.Group>

<Form.Group controlId="formSurname">
  <Form.Label>Surname</Form.Label>
  <Form.Control
    type="text"
    name="surname"
    value={client?.surname}
    placeholder="Enter client surname"
    onChange={(e) => setClient({ ...client, surname: e.target.value })}
  />
</Form.Group>

<Form.Group controlId="formEmail">
  <Form.Label>Email</Form.Label>
  <Form.Control
    type="email"
    name="email"
    value={client?.email}
    placeholder="Enter email"
    onChange={(e) => setClient({ ...client, email: e.target.value })}
  />
</Form.Group>
  
          <br />
          <div className="text-center">
            <Button variant="primary" type="submit">
              Update
            </Button>
          </div>
        </Form>
      </div>
    );
  }
  