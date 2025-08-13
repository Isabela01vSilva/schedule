package br.com.Isabela01vSilva.schedulo.service.dto;

import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public record AppointmentDTO(Long id,
                             LocalDate executionDate,
                             JsonNode payload,
                             Status status) {
}
