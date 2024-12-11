import React, {useEffect, useState} from 'react';
import axios from 'axios';

const AppointmentListPage = () => {
    const [appointments, setAppointments] = useState([]);

    useEffect(() => {
        // Fetch appointments from backend
        axios.get('http://localhost:8080/appointments/therapist/1') // Adjust therapist ID
            .then(response => setAppointments(response.data))
            .catch(error => console.error('Error fetching appointments:', error));
    }, []);

    return (
        <div>
            <h2>Appointments</h2>
            <ul>
                {appointments.map(appointment => (
                    <li key={appointment.id}>
                        {appointment.dateTime} - {appointment.status}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AppointmentListPage;
