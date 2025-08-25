package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import br.com.Isabela01vSilva.schedulo.service.dto.CallbackDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CallbackService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment handleCallback(CallbackDTO callback){

        Appointment appointment = appointmentRepository.findById(callback.getAppointmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        appointment.setStatus(callback.getStatus());
        return appointmentRepository.save(appointment);

    }
}