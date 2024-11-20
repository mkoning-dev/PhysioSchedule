package com.physioschedule.controller;

import com.physioschedule.dto.AppointmentRequestDTO;
import com.physioschedule.dto.AppointmentResponseDTO;
import com.physioschedule.exception.UserNotFoundException;
import com.physioschedule.model.Appointment;
import com.physioschedule.model.User;
import com.physioschedule.repository.UserRepository;
import com.physioschedule.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final UserRepository userRepository;
    private final AppointmentService appointmentService;

    public AppointmentController(UserRepository userRepository, AppointmentService appointmentService) {
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO) {
        User patient = userRepository.findByEmail(requestDTO.getPatientEmail())
                .orElseThrow(() -> new UserNotFoundException("Patient with email " + requestDTO.getPatientEmail() + " not found"));
        User therapist = userRepository.findByEmail(requestDTO.getTherapistEmail())
                .orElseThrow(() -> new UserNotFoundException("Therapist with email " + requestDTO.getTherapistEmail() + " not found"));

        Appointment appointment = appointmentService.createAppointment(therapist, patient, requestDTO.getDateTime());

        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(appointment.getId());
        responseDTO.setDateTime(appointment.getDateTime());
        responseDTO.setStatus(appointment.getStatus());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
