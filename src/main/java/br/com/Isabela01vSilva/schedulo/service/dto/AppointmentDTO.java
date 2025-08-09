package br.com.Isabela01vSilva.schedulo.service.dto;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public record AppointmentDTO(Long id,
                             LocalDate executionDate,
                             JsonNode payload,
                             String status) {
}
