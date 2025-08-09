package br.com.Isabela01vSilva.schedulo.controller;

import br.com.Isabela01vSilva.schedulo.controller.response.StatusAppointmentResponse;
import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
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
    private AppointmentRepository repository;

    @PutMapping("/{id}")
    public ResponseEntity<StatusAppointmentResponse> cancelAppointment(@PathVariable Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        appointment.setStatus("CANCELADO");
        repository.save(appointment);

        return ResponseEntity.ok(new StatusAppointmentResponse(
                appointment.getId(),
                appointment.getStatus(),
                "Agendamento cancelado com sucesso"));
    }
}
