package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.response.ErrorSwaggerResponse;
import br.com.Isabela01vSilva.schedulo.service.ReadAppointmentService;
import br.com.Isabela01vSilva.schedulo.service.dto.AppointmentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/appointments/read")
public class ReadAppointmentController {

    private final ReadAppointmentService service;

    public ReadAppointmentController(ReadAppointmentService readAppointmentService) {
        this.service = readAppointmentService;
    }

    @Operation(summary = "Buscar agendamento por ID", description = "Retorna os detalhes de um agendamento existente a partir do seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppointmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorSwaggerResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO dto = service.getAppointmentById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Listar todos os agendamentos", description = "Retorna uma lista com todos os agendamentos cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AppointmentDTO.class, type = "array")))
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> dtos = service.getAllAppointment();
        return ResponseEntity.ok(dtos);
    }
}
