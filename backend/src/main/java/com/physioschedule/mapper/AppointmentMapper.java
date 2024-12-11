package com.physioschedule.mapper;

import com.physioschedule.dto.AppointmentResponseDTO;
import com.physioschedule.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentResponseDTO mapToResponseDTO(Appointment appointment) {
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(appointment.getId());
        responseDTO.setDateTime(appointment.getDateTime());
        responseDTO.setStatus(appointment.getStatus());
        return responseDTO;
    }
}
