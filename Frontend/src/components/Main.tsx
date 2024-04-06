import React, { useState, useEffect } from 'react';

interface Book {
    id: number;
    title: string;
    author: string;
    publisher: string;
    isbn: string;
    description: string;
    quantity: number;
    currentQuantity: number;
}

export default function Main() {
    const [books, setBooks] = useState<Book[]>([]);
    const [errorMessage, setErrorMessage] = useState<string>('');
    const isAuthenticated = !!document.cookie.split('; ').find(row => row.startsWith('token='));
        
    useEffect(() => {
        fetch('http://localhost:8080/api/books/booksList')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                setBooks(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }, []);

    const handleDelete = (id: number) => {
        // Display a confirmation dialog
        const confirmed = window.confirm('Are you sure you want to delete this book?');
        
        // Proceed with deletion if confirmed
        if (confirmed) {
            fetch(`http://localhost:8080/api/books/delete/${id}`, {
                method: 'POST'
            })
            .then(response => {
                if (response.status === 500) {
                    throw new Error('The book is reserved and cant be deleted');
                }
                // Remove the deleted book from the state
                setBooks(prevBooks => prevBooks.filter(book => book.id !== id));
            })
            .catch(error => {
                // Display an alert if an error occurs
                setErrorMessage(error.message);
                console.error('Failed to delete book:', error);
            });
        }
    };

    const handleEdit = (id: number) =>{

    }
    

    return (
        <div className="card">
            {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
            <table className="table">
                {/* Table header */}
                <thead>
                    <tr>
                    {isAuthenticated &&(
                                <>
                                    <th>Id</th>
                                </>
                            )}
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>ISBN</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Current Quantity</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                {/* Table body */}
                <tbody>
                    {books.map(book => (
                        <tr key={book.id}>
                            {isAuthenticated &&(
                                <>
                                    <td>{book.id}</td>
                                </>
                            )}
                            <td>{book.title}</td>
                            <td>{book.author}</td>
                            <td>{book.publisher}</td>
                            <td>{book.isbn}</td>
                            <td>{book.description}</td>
                            <td>{book.quantity}</td>
                            <td>{book.currentQuantity}</td>
                            <td>
                                {/* Conditionally render delete and edit buttons */}
                                {isAuthenticated && (
                                    <>
                                        <button className="btn btn-danger mr-2" onClick={() => handleDelete(book.id)}>Delete</button>
                                        <button className="btn btn-primary" onClick={() => handleEdit(book.id)}>Edit</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
    
}


