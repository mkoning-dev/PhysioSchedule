import React, { useState } from 'react';
import axios from 'axios';

const UserFormPage = () => {
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [role, setRole] = useState('PATIENT'); // Default role is PATIENT
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        // User data to be sent in the POST request
        const userData = {
            email,
            name,
            password,
            phoneNumber,
            role,
        };

        axios.post('http://localhost:8080/users/register', userData)  // Adjust this endpoint as necessary
            .then(response => {
                console.log('User created:', response.data);
                // Optionally clear form fields or navigate to another page
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    };

    return (
        <div>
            <h2>Create User</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Name:
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Email:
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Phone Number:
                    <input
                        type="text"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Role:
                    <select value={role} onChange={(e) => setRole(e.target.value)} required>
                        <option value="PATIENT">Patient</option>
                        <option value="THERAPIST">Therapist</option>
                    </select>
                </label>
                <button type="submit">Create User</button>
            </form>
        </div>
    );
};

export default UserFormPage;
