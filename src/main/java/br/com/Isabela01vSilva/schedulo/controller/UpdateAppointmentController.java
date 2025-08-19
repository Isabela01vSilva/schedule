package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.request.CreateAppointmentRequest;
import br.com.Isabela01vSilva.schedulo.service.UpdateAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appointments/update")
public class UpdateAppointmentController {

    @Autowired
    private UpdateAppointmentService service;

    @PutMapping
    public ResponseEntity<Void> updateAppointment(@RequestBody CreateAppointmentRequest request){
        service.update(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
