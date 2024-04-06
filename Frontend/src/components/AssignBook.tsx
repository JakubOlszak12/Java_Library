import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

export function AssignBook() {
  const [errorMessage, setErrorMessage] = useState([]);
  const navigation = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const client_id = formData.get('client_id') as unknown;
    const book_id = formData.get('book_id') as unknown;

    // Simple data validation
    if (!client_id || !book_id) {
      setErrorMessage(['Please fill in all fields']);
      return;
    }

    fetch('http://localhost:8080/api/reservation/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ client_id, book_id }),
    })
      .then(async (response) => {
        if (response.status === 404) {
            console.log(response);
          const errorResponse = await response.json();
          setErrorMessage(errorResponse);
          console.log(errorResponse);
        }
        return response.json();
      })
      .then((data) => {
        navigation('/');
      })
      .catch((error) => {
        console.error('Failed to create reservation:', error);
        setErrorMessage(['There is no client with such Id']);
      });
  }

  return (
    <>
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Create Reservation</h2>
        {errorMessage && (
          <div style={{ color: 'red', textAlign: 'center' }}>
            {errorMessage.map((item, index) => (
              <p key={index}>{item}</p>
            ))}
          </div>
        )}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto">
          <Form.Group controlId="formClientId">
            <Form.Label>Client ID</Form.Label>
            <Form.Control type="number" name="client_id" placeholder="Enter client ID" />
          </Form.Group>

          <Form.Group controlId="formBookId">
            <Form.Label>Book ID</Form.Label>
            <Form.Control type="number" name="book_id" placeholder="Enter book ID" />
          </Form.Group>

          <br />
          <div className="text-center">
            <Button variant="primary" type="submit">
              Create Reservation
            </Button>
          </div>
        </Form>
      </div>
    </>
  );
}
