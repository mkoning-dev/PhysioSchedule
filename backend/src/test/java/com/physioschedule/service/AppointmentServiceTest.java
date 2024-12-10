package com.physioschedule.service;

import physioschedule.model.Appointment;
import physioschedule.model.User;
import physioschedule.repository.AppointmentRepository;
import com.physioschedule.testfactory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import physioschedule.service.AppointmentService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment_Success() {
        User patient = UserFactory.createDefaultPatient();
        User therapist = UserFactory.createDefaultTherapist();
        LocalDateTime dateTime = LocalDateTime.now().plusHours(1);

        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(i -> i.getArgument(0));

        Appointment newAppointment = appointmentService.createAppointment(therapist, patient, dateTime);

        assertNotNull(newAppointment);
        assertEquals(Appointment.Status.SCHEDULED, newAppointment.getStatus());
        assertEquals(User.Role.THERAPIST, newAppointment.getTherapist().getRole());
        assertEquals(User.Role.PATIENT, newAppointment.getPatient().getRole());
        assertEquals(dateTime, newAppointment.getDateTime());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }
}
