package com.physioschedule.dto;

import com.physioschedule.model.Appointment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Appointment.Status status;

}
