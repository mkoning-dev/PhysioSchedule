package physioschedule.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {
    @NotNull
    private String therapistEmail;

    @NotNull
    private String patientEmail;

    @Future
    private LocalDateTime dateTime;

}
