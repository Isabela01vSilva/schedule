package br.com.Isabela01vSilva.schedulo.config;

import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import br.com.Isabela01vSilva.schedulo.service.ExecuteAppointmentService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ExecuteAppointmentService executeAppointmentService;

    @Bean
    public Job processarAgendamentosJob(Step processAppointmentsStep) {
        return new JobBuilder("processAppointments", jobRepository)
                .start(processAppointmentsStep)
                .build();
    }

    @Bean
    public Step processAppointmentsStep(PlatformTransactionManager transactionManager, EntityManagerFactory emf) {
        return new StepBuilder("fetchAppointments", jobRepository)
                .<Appointment, Appointment>chunk(10, transactionManager)
                .reader(appointmentReader(emf))
                .processor(appointmentProcessor())
                .writer(appointmentWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Appointment> appointmentReader(EntityManagerFactory emf) {
        JpaPagingItemReader<Appointment> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("SELECT ap FROM Appointment ap WHERE ap.executionDate = :executionDate and status in :status");
        reader.setPageSize(10);
        Map<String, Object> params = new HashMap<>();
        params.put("executionDate", LocalDate.now());
        params.put("status", List.of(Status.SCHEDULED, Status.RETRY));
        reader.setParameterValues(params);
        return reader;
    }

    @Bean
    public ItemProcessor<Appointment, Appointment> appointmentProcessor() {
        return appointment -> {
            try {
                executeAppointmentService.executeSchedule(appointment);
                return appointment;
            } catch (Exception ex) {
                appointment.setStatus(Status.ERROR);
                return appointment;
            }
        };
    }

    @Bean
    public ItemWriter<Appointment> appointmentWriter() {
        return appointments  -> {
            for (Appointment appointment : appointments ) {
                appointment.setStatus(Status.PROCESSING);
                appointmentRepository.save(appointment);
            }
        };
    }
}
