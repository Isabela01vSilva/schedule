package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.request.CreateAppointmentRequest;
import br.com.Isabela01vSilva.schedulo.service.CreateAppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appointments")
public class AppointmentController {

    public AppointmentController(CreateAppointmentService service) {
        this.service = service;
    }

    private final CreateAppointmentService service;

    @PostMapping
    public ResponseEntity<Void> createAppointment(@RequestBody CreateAppointmentRequest request){
        service.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
