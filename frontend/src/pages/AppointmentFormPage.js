import React, {useState} from 'react';
import axios from 'axios';

const AppointmentFormPage = () => {
    const [dateTime, setDateTime] = useState('');
    const [patientEmail, setPatientEmail] = useState('');
    const [therapistEmail, setTherapistEmail] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        const appointmentData = {
            dateTime,
            patientEmail,
            therapistEmail,
        };

        axios.post('http://localhost:8080/appointments', appointmentData)
            .then(response => {
                console.log('Appointment created:', response.data);
            })
            .catch(error => {
                console.error('Error creating appointment:', error);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Patient Email:
                <input
                    type="email"
                    value={patientEmail}
                    onChange={(e) => setPatientEmail(e.target.value)}
                    required
                />
            </label>
            <label>
                Therapist Email:
                <input
                    type="email"
                    value={therapistEmail}
                    onChange={(e) => setTherapistEmail(e.target.value)}
                    required
                />
            </label>
            <label>
                Appointment Date and Time:
                <input
                    type="datetime-local"
                    value={dateTime}
                    onChange={(e) => setDateTime(e.target.value)}
                    required
                />
            </label>
            <button type="submit">Create Appointment</button>
        </form>
    );
};

export default AppointmentFormPage;
