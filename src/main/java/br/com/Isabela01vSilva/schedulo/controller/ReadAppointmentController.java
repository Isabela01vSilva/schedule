package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.service.ReadAppointmentService;
import br.com.Isabela01vSilva.schedulo.service.dto.AppointmentDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO dto = service.getAppointmentById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<AppointmentDTO> dtos = service.getAllAppointment();
        return ResponseEntity.ok(dtos);
    }
}
