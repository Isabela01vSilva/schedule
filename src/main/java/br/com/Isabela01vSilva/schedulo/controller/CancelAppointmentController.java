package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.response.ErrorSwaggerResponse;
import br.com.Isabela01vSilva.schedulo.controller.response.StatusAppointmentResponse;
import br.com.Isabela01vSilva.schedulo.service.CancelAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appointments/cancel")
public class CancelAppointmentController {

    @Autowired
    private CancelAppointmentService service;

    @Operation(
            summary = "Cancelar agendamento",
            description = "Cancela um agendamento existente pelo ID e retorna o status atualizado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatusAppointmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorSwaggerResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<StatusAppointmentResponse> cancelAppointment(@PathVariable Long id) {
        service.cancelAppoitment(id);
        return ResponseEntity.ok(new StatusAppointmentResponse(id, "CANCELADO", "Agendamento cancelado com sucesso"));
    }
}
