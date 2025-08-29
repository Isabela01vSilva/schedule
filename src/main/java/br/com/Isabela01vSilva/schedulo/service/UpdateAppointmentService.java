package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.controller.request.CreateAppointmentRequest;
import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public void update(CreateAppointmentRequest request){

        Appointment appointment = new Appointment();
        appointment.setAppName(request.appName());
        appointment.setExecutionDate(request.executionDate());
        appointment.setPayload(request.payload());
        appointment.setStatus(Status.valueOf(request.status()).name());

        appointmentRepository.save(appointment);
    }

}
