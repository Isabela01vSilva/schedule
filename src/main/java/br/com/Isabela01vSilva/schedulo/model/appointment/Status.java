package br.com.Isabela01vSilva.schedulo.model.appointment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents the possible statuses of an appointment")
public enum Status {

    @Schema(description = "Appointment created, not yet processed")
    SCHEDULED,

    @Schema(description = "If cancellation is allowed")
    CANCELLED,

    @Schema(description = "When the appointment has already been executed")
    COMPLETED,

    @Schema(description = "Error during processing")
    ERROR,

    @Schema(description = "Currently being processed")
    PROCESSING,

    @Schema(description = "Retry attempt")
    RETRY       // retry attempt
}
