package physioschedule.service;

import physioschedule.model.Appointment;
import physioschedule.model.User;
import physioschedule.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(User therapist, User patient, LocalDateTime dateTime) {
        if (therapist == null || patient == null) {
            throw new IllegalArgumentException("Therapist and Patient must be provided.");
        }

        Appointment appointment = new Appointment();
        appointment.setTherapist(therapist);
        appointment.setPatient(patient);
        appointment.setDateTime(dateTime);
        appointment.setStatus(Appointment.Status.SCHEDULED);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForTherapist(Long therapistId) {
        return appointmentRepository.findByTherapistId(therapistId);
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDateTimeBetween(start, end);
    }

    @Transactional
    public Appointment updateAppointmentStatus(Long appointmentId, Appointment.Status status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found."));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
}
