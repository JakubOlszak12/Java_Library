import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';

export function EditBook() {
    const { book_id } = useParams();
    const [errorMessage, setErrorMessage] = useState([]);
    const navigation = useNavigate();
    const [book, setBook] = useState(null);
    let token = document.cookie.split('; ').find(row => row.startsWith('token='));
    token = token.substring(6);
    
    useEffect(() => {
      const fetchBookData = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/books/${book_id}`,{
            method: 'GET',
            headers:{
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token ? token : ''}`
            }
          });
          if (!response.ok) {
            throw new Error('Failed to fetch book data');
          }
          const bookData = await response.json();
          setBook(bookData);
        } catch (error) {
          console.error('Error fetching book data:', error);
        }
      };
  
      if (book_id != undefined && book_id != null) {
        fetchBookData();
      }
    }, [book_id]);
  
    const handleSubmit = async (event) => {
      event.preventDefault();
      const formData = new FormData(event.currentTarget);
      const title = formData.get('title') as string;
      const author = formData.get('author') as string;
      const publisher = formData.get('publisher') as string;
      const isbn = formData.get('isbn') as string;
      const description = formData.get('description') as string;
  
      if (!title.trim() || !author.trim() || !publisher.trim() || !isbn.trim() || !description.trim()) {
        setErrorMessage(['Please fill in all fields']);
        return;
      }
  

      fetch(`http://localhost:8080/api/books/edit/${book_id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token ? token : ''}`
        },
        body: JSON.stringify({ id:book_id, title, author, publisher, isbn, description}),
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
          navigation('/');
        })
        .catch((error) => {
          
        });
    };

    return (
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Edit Book</h2>
        {errorMessage.length > 0 && (
          <div style={{ color: 'red', textAlign: 'center' }}>
            {errorMessage.map((item, index) => (
              <p key={index}>{item}</p>
            ))}
          </div>
        )}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto">
          <Form.Group controlId="formTitle">
            <Form.Label>Title</Form.Label>
            <Form.Control type="text" name="title" value={book?.title} placeholder="Enter book title" onChange={(e) => setBook({ ...book, title: e.target.value })} />
          </Form.Group>

          <Form.Group controlId="formAuthor">
            <Form.Label>Author</Form.Label>
            <Form.Control type="text" name="author" value={book?.author} placeholder="Enter book author" onChange={(e) => setBook({ ...book, author: e.target.value })} />
          </Form.Group>

          <Form.Group controlId="formPublisher">
            <Form.Label>Publisher</Form.Label>
            <Form.Control type="text" name="publisher" value={book?.publisher} placeholder="Enter publisher" onChange={(e) => setBook({ ...book, publisher: e.target.value })} />
          </Form.Group>

          <Form.Group controlId="formIsbn">
            <Form.Label>ISBN</Form.Label>
            <Form.Control type="text" name="isbn" value={book?.isbn} placeholder="Enter ISBN" onChange={(e) => setBook({ ...book, isbn: e.target.value })} />
          </Form.Group>

          <Form.Group controlId="formDescription">
            <Form.Label>Description</Form.Label>
            <Form.Control as="textarea" rows={3} name="description" value={book?.description} placeholder="Enter description" onChange={(e) => setBook({ ...book, description: e.target.value })} />
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

