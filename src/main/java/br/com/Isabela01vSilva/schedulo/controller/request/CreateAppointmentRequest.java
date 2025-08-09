package br.com.Isabela01vSilva.schedulo.controller.request;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public record CreateAppointmentRequest (LocalDate executionDate,
                                        JsonNode payload,
                                        String appName,
                                        String status){

}
