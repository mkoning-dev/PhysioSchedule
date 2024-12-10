package physioschedule.mapper;

import physioschedule.dto.AppointmentResponseDTO;
import physioschedule.model.Appointment;
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
