import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import UserFormPage from './pages/UserFormPage';
import AppointmentListPage from './pages/AppointmentListPage';
import AppointmentFormPage from './pages/AppointmentFormPage';

const App = () => {
    return (
        <Router>
            <div>
                <h1>PhysioSchedule</h1>
                <Routes>
                    <Route path="/" element={<AppointmentListPage/>}/>
                    <Route path="/appointments/new" element={<AppointmentFormPage/>}/>
                    <Route path="/users/register" element={<UserFormPage/>}/>
                </Routes>
            </div>
        </Router>
    );
};

export default App;
