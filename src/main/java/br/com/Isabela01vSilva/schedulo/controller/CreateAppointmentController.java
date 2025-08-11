package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.request.CreateAppointmentRequest;
import br.com.Isabela01vSilva.schedulo.service.CreateAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appointments")
public class CreateAppointmentController {

    public CreateAppointmentController(CreateAppointmentService service) {
        this.service = service;
    }

    private final CreateAppointmentService service;

    @Operation(summary = "Cria um agendamento", description = "Recebe os dados e registra um novo agendamento no sistema.")
    @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso")
    @PostMapping
    public ResponseEntity<Void> createAppointment(@RequestBody CreateAppointmentRequest request){
        service.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
