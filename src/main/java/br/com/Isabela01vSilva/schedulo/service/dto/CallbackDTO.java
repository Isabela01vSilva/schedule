package br.com.Isabela01vSilva.schedulo.service.dto;

import br.com.Isabela01vSilva.schedulo.model.appointment.Status;

public class CallbackDTO {

    private Long appointmentId;
    private String status;

    public CallbackDTO() {
    }

    public CallbackDTO(Long appointmentId, String status) {
        this.appointmentId = appointmentId;
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}