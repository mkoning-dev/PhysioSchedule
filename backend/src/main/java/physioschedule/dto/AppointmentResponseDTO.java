package physioschedule.dto;

import physioschedule.model.Appointment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Appointment.Status status;

}
