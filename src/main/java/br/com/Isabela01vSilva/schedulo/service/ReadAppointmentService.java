package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import br.com.Isabela01vSilva.schedulo.service.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadAppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Agendamento não encontrado com id " + id));

        return new AppointmentDTO(
                appointment.getId(),
                appointment.getExecutionDate(),
                appointment.getPayload(),
                appointment.getStatus()
        );
    }

    public List<AppointmentDTO> getAllAppointment() {
        return repository.findAll()
                .stream()
                .map(appointment -> new AppointmentDTO(
                        appointment.getId(),
                        appointment.getExecutionDate(),
                        appointment.getPayload(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
