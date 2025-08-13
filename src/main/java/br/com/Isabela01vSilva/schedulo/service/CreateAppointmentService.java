package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.controller.request.CreateAppointmentRequest;
import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public void createAppointment(CreateAppointmentRequest request){

        validateDate(request.executionDate());

        Appointment appointment = new Appointment();
        appointment.setAppName(request.appName());
        appointment.setExecutionDate(request.executionDate());
        appointment.setPayload(request.payload());
        appointment.setStatus(Status.valueOf(request.status()));

        appointmentRepository.save(appointment);
    }

    public void validateDate(LocalDate data){
        LocalDate today = LocalDate.now();

        if (data.isBefore(today)){
            throw new IllegalArgumentException("The appointment data cannot be in the past");
        }
    }
}
