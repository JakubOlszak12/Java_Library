import React, { useState, useEffect } from 'react';
import { useNavigate, useNavigation } from 'react-router-dom';

interface Client {
    id: number;
    name: string;
    surname: string;
    email: string;
}

export default function ClientManagement() {
    const [clients, setClients] = useState<Client[]>([]);
    const [errorMessage, setErrorMessage] = useState<string>('');
    const navigation = useNavigate();
    let token = document.cookie.split('; ').find(row => row.startsWith('token='));
    token = token.substring(6)
    console.log(token);
    useEffect(() => {
        fetch('http://localhost:8080/api/clients/clientsList',{
            method: 'GET',
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
                setClients(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
                setErrorMessage(error.message);
            });
    }, []);

    const handleDelete = (id: number) => {
        const confirmed = window.confirm('Are you sure you want to delete this client?');
        
        if (confirmed) {
            fetch(`http://localhost:8080/api/clients/delete/${id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token ? token : ''}`
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to delete client because they have assigned book');
                }
                setClients(prevClients => prevClients.filter(client => client.id !== id));
            })
            .catch(error => {
                setErrorMessage(error.message);
                console.error('Failed to delete client:', error);
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
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {clients.map(client => (
                        <tr key={client.id}>
                            <td>{client.id}</td>
                            <td>{client.name}</td>
                            <td>{client.surname}</td>
                            <td>{client.email}</td>
                            <td>
                                <button className='btn btn-primary' onClick={() => navigation(`edit/${client.id}`)}>Edit</button>
                                <button className="btn btn-danger" onClick={() => handleDelete(client.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
