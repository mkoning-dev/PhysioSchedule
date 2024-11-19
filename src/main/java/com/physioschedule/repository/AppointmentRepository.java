package com.physioschedule.repository;

import com.physioschedule.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByTherapistId(Long therapistId);
    List<Appointment> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}