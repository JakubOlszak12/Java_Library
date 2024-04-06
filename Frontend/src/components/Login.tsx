import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

export function Login() {
  const [errorMessage, setErrorMessage] = useState('');
  const navigation = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const username = formData.get('username') as string;
    const password = formData.get('password') as string;

    // Simple data validation
    if (!username.trim() || !password.trim()) {
      setErrorMessage('Please enter both username and password');
      return;
    }

    // Send your request with username and password
    fetch('http://localhost:8080/authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        setCookie('token', data.token, 7);
        console.log(data);
        navigation('/');
        window.location.reload();
      })
      .catch((error) => {
        // Handle errors
        setErrorMessage('Invalid username or password');
      });
  }

  function setCookie(name, value, days) {
    const date = new Date();
    date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
    const expires = 'expires=' + date.toUTCString();
    document.cookie = name + '=' + value + ';' + expires + ';path=/';
  }

  return (
    <>
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Log in</h2>
        {errorMessage && <div style={{ color: 'red', textAlign:'center' }}>{errorMessage}</div>}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto">
          <Form.Group controlId="formUsername">
            <Form.Label>Username</Form.Label>
            <Form.Control type="text" name="username" placeholder="Enter your username" />
          </Form.Group>

          <Form.Group controlId="formPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" name="password" placeholder="Enter your password" />
          </Form.Group>
          <br />
          <div className="text-center">
            <Button variant="primary" type="submit">
              Login
            </Button>
          </div>
        </Form>
      </div>
    </>
  );

}
