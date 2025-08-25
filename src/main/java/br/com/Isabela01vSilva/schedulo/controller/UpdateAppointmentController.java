package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.request.CreateAppointmentRequest;
import br.com.Isabela01vSilva.schedulo.service.UpdateAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/appointments/update")
public class UpdateAppointmentController {

    @Autowired
    private UpdateAppointmentService service;

    @Operation(summary = "Editar um agendamento", description = "Edita um agendamento existente no sistema.")
    @ApiResponse(responseCode = "204", description = "Agendamento editado com sucesso")
    @PutMapping
    public ResponseEntity<Void> updateAppointment(@RequestBody CreateAppointmentRequest request){
        service.update(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
