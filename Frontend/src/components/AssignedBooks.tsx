import React, { useState, useEffect } from 'react';

interface ReservationDto {
    id: number;
    name: string;
    surname: string;
    email: string;
    clientId: number;
    bookTitle: string;
    bookId: number;
}

export default function AssignedBooks() {
    const [reservations, setReservations] = useState<ReservationDto[]>([]);
    const [errorMessage, setErrorMessage] = useState<string>('');
    let token = document.cookie.split('; ').find(row => row.startsWith('token='));
    token = token.substring(6)
    useEffect(() => {
        fetch('http://localhost:8080/api/reservation/reservationList',{
            method:'GET',
            headers:{
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token ? token : ''}`
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                setReservations(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
                setErrorMessage(error.message);
            });
    }, []);

    const handleDelete = (id: number) => {
        const confirmed = window.confirm('Are you sure you want to delete this reservation?');
        
        if (confirmed) {
            fetch(`http://localhost:8080/api/reservation/delete/${id}`, {
                method: 'DELETE',
                headers:{
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token ? token : ''}`
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to delete reservation');
                }
                setReservations(prevReservations => prevReservations.filter(reservation => reservation.id !== id));
            })
            .catch(error => {
                setErrorMessage(error.message);
                console.error('Failed to delete reservation:', error);
            });
        }
    };

    return (
        <div className="card">
            {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
            <table className="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Client Id</th>
                        <th>Book Title</th>
                        <th>Book Id</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {reservations.map(reservation => (
                        <tr key={reservation.id}>
                            <td>{reservation.id}</td>
                            <td>{reservation.name}</td>
                            <td>{reservation.surname}</td>
                            <td>{reservation.email}</td>
                            <td>{reservation.clientId}</td>
                            <td>{reservation.bookTitle}</td>
                            <td>{reservation.bookId}</td>
                            <td>
                                <button className="btn btn-danger" onClick={() => handleDelete(reservation.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
