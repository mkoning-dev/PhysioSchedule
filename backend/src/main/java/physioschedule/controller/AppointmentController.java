package physioschedule.controller;

import physioschedule.dto.AppointmentRequestDTO;
import physioschedule.dto.AppointmentResponseDTO;
import physioschedule.exception.UserNotFoundException;
import physioschedule.mapper.AppointmentMapper;
import physioschedule.model.Appointment;
import physioschedule.model.User;
import physioschedule.repository.UserRepository;
import physioschedule.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final UserRepository userRepository;
    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(UserRepository userRepository, AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO) {
        User patient = userRepository.findByEmail(requestDTO.getPatientEmail())
                .orElseThrow(() -> new UserNotFoundException("Patient with email " + requestDTO.getPatientEmail() + " not found"));
        User therapist = userRepository.findByEmail(requestDTO.getTherapistEmail())
                .orElseThrow(() -> new UserNotFoundException("Therapist with email " + requestDTO.getTherapistEmail() + " not found"));

        Appointment appointment = appointmentService.createAppointment(therapist, patient, requestDTO.getDateTime());
        AppointmentResponseDTO responseDTO = appointmentMapper.mapToResponseDTO(appointment);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/therapist/{therapistId}")
    public List<AppointmentResponseDTO> getAppointmentsForTherapist(@PathVariable Long therapistId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForTherapist(therapistId);
        return appointments.stream()
                .map(appointmentMapper::mapToResponseDTO)
                .toList();
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentResponseDTO> getAppointmentsForPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(patientId);
        return appointments.stream()
                .map(appointmentMapper::mapToResponseDTO)
                .toList();
    }

    @PatchMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponseDTO> updateAppointmentStatus(
            @PathVariable Long appointmentId,
            @RequestParam Appointment.Status status) {
        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok(appointmentMapper.mapToResponseDTO(updatedAppointment));
    }

}
