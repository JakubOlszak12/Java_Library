import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

export function CreateBook() {
  const [errorMessage, setErrorMessage] = useState([]);
  const navigation = useNavigate();
  let token = document.cookie.split('; ').find(row => row.startsWith('token='));
  token = token.substring(6);
  function handleSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.currentTarget);
    const title = formData.get('title') as string;
    const author = formData.get('author') as string;
    const publisher = formData.get('publisher') as string;
    const isbn = formData.get('isbn') as string;
    const description = formData.get('description') as string;
    // Simple data validation
    if (!title.trim() || !author.trim() || !publisher.trim() || !isbn.trim() || !description.trim()) {
      setErrorMessage(['Please fill in all fields']);
      return;
    }

   
    fetch('http://localhost:8080/api/books/save', {
      method: 'POST',
      headers:{
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token ? token : ''}`
      },
      body: JSON.stringify({ id:0,title, author, publisher, isbn, description }),
    })
      .then(async (response) => {
        if (response.status === 400) {
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
        
      });
  }

  return (
    <>
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Add Book</h2>
        {errorMessage && (
          <div style={{ color: 'red', textAlign: 'center' }}>
            {errorMessage.map((item, index) => (
              <p key={index}>{item}</p>
            ))}
          </div>
        )}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto" method='POST'>
          <Form.Group controlId="formTitle">
            <Form.Label>Title</Form.Label>
            <Form.Control type="text" name="title" placeholder="Enter book title" />
          </Form.Group>

          <Form.Group controlId="formAuthor">
            <Form.Label>Author</Form.Label>
            <Form.Control type="text" name="author" placeholder="Enter book author" />
          </Form.Group>

          <Form.Group controlId="formPublisher">
            <Form.Label>Publisher</Form.Label>
            <Form.Control type="text" name="publisher" placeholder="Enter publisher" />
          </Form.Group>

          <Form.Group controlId="formIsbn">
            <Form.Label>ISBN</Form.Label>
            <Form.Control type="text" name="isbn" placeholder="Enter ISBN" />
          </Form.Group>

          <Form.Group controlId="formDescription">
            <Form.Label>Description</Form.Label>
            <Form.Control as="textarea" rows={3} name="description" placeholder="Enter description" />
          </Form.Group>

          <br />
          <div className="text-center">
            <Button variant="primary" type="submit">
              Add Book
            </Button>
          </div>
        </Form>
      </div>
    </>
  );
}
