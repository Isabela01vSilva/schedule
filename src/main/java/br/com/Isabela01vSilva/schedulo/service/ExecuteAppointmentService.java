package br.com.Isabela01vSilva.schedulo.service;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExecuteAppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final SnsPublisherRaw snsPublisher = new SnsPublisherRaw();

    @Value("${schedule.topic.arn}")
    private String topicArn;

    public ExecuteAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Boolean executeSchedules(){
        try{
            List<Appointment> appointments = appointmentRepository.findByExecutionDate(LocalDate.now());
            for (Appointment appointment: appointments){
                sendToTopic(appointment.getPayload().toPrettyString(), appointment.getAppName());
            }
            return true;
        } catch (Exception ex){
            System.out.println("Erro na execução do agendamento");
            return false;
        }
    }

    public void sendToTopic(String payload, String appName){
        snsPublisher.publicar(topicArn, payload, appName);
    }
}
