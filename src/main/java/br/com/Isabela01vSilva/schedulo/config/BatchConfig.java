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
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private JobRepository repository;

    @Autowired
    private ExecuteAppointmentService executeAppointmentService;

    @Bean
    public Job processarAgendamentosJob(Step processarAgendamentosStep) {
        return new JobBuilder("processarAgendamentos", repository)
                .start(processarAgendamentosStep)
                .build();
    }

    @Bean
    public Step processarAgendamentosStep(PlatformTransactionManager transactionManager, EntityManagerFactory emf) {
        return new StepBuilder("buscarAgendamentos", repository)
                .<Appointment, Appointment>chunk(10, transactionManager)
                .reader(agendamentoReader(emf))
                .processor(agendamentoProcessor())
                .writer(agendamentoWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Appointment> agendamentoReader(EntityManagerFactory emf) {
        JpaPagingItemReader<Appointment> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("SELECT ap FROM Appointment ap WHERE ap.executionDate = :executionDate");
        reader.setPageSize(10);
        Map<String, Object> params = new HashMap<>();
        params.put("executionDate", LocalDate.now());
        reader.setParameterValues(params);
        return reader;
    }

    @Bean
    public ItemProcessor<Appointment, Appointment> agendamentoProcessor() {
        return appointment -> {
            try {
                executeAppointmentService.executeSchedule(appointment);
                return appointment;
            } catch (Exception ex) {
                appointment.setStatus(Status.ERRO);
                return appointment;
            }
        };
    }

    @Bean
    public ItemWriter<Appointment> agendamentoWriter() {
        return agendamentos -> {
            for (Appointment agendamento : agendamentos) {
                //agendamento.setStatus(Status.PROCESSANDO);
                appointmentRepository.save(agendamento);
            }
        };
    }
}
