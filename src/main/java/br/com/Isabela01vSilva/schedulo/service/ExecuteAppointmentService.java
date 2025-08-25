package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExecuteAppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final SnsPublisherRaw snsPublisher = new SnsPublisherRaw();

    @Value("${schedule.topic.arn}")
    private String topicArn;

    public ExecuteAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    int index = 0;

    public Boolean executeSchedule(Appointment appointment) {
        try {
            if (appointment.getStatus() == Status.AGENDADO || appointment.getStatus() == Status.RETENTAR)
                snsPublisher.publicar(topicArn, appointment.getPayload().toPrettyString(),
                        appointment.getAppName(), appointment.getId().toString());

        } catch (Exception e) {
            if (index < 10) {
                index++;
                executeSchedule(appointment);
                appointment.setStatus(Status.ERRO);
                throw e;
            }
            System.err.println("Erro ao executar agendamento ID " + appointment.getId() + ": " + e.getMessage());
        }

        appointmentRepository.save(appointment);
        return true;
    }

    public void sendToTopic(String payload, String appName, String id) {
        snsPublisher.publicar(topicArn, payload, appName, id);
    }
}