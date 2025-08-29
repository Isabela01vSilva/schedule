package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CancelAppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public void cancelAppoitment(Long id){
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Agendamento não encontrado com id " + id));

        appointment.setStatus(Status.CANCELLED.name());
        repository.save(appointment);
    }

}
