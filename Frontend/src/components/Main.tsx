import React, { useState, useEffect } from 'react';

interface Book {
    id: number;
    title: string;
    author: string;
    publisher: string;
    isbn: string;
    description: number;
}

export default function BasicDemo() {
    const [books, setBooks] = useState<Book[]>([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/books')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Update the state with fetched data
            setBooks(data);
        })
        .catch(error => {
            // Handle errors
            console.error('There was a problem with the fetch operation:', error);
        });
    }, []); // empty dependency array means this effect runs only once, after the initial render

    return (
        <div className="card">
            {books.map(book => (
                <div key={book.id}>
                    <ul>
                        <li>{book.author}</li>
                    </ul>
                </div>
            ))}
        </div>
    );
}
