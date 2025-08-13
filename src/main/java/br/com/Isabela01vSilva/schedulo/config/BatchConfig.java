import br.com.Isabela01vSilva.schedulo.model.appointment.Appointment;
import br.com.Isabela01vSilva.schedulo.model.appointment.Status;
import br.com.Isabela01vSilva.schedulo.repository.AppointmentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;


import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Bean
    public Job processarAgendamentosJob(Step processarAgendamentosStep) {
        return jobBuilderFactory.get("processarAgendamentosJob")
                .start(processarAgendamentosStep)
                .build();
    }

    @Bean
    public Step processarAgendamentosStep() {
        return stepBuilderFactory.get("processarAgendamentosStep")
                .<Appointment, Appointment>chunk(10)
                .reader(agendamentoReader())
                .processor(agendamentoProcessor())
                .writer(agendamentoWriter())
                .build();
    }

    @Bean
    public ItemReader<Appointment> agendamentoReader() {
        return new ItemReader<>() {
            private final List<Appointment> agendamentos =
                    appointmentRepository.findByExecutionDateAndStatus(LocalDate.now(), Status.AGENDADO);
            private int index = 0;

            @Override
            public Appointment read() {
                if (index < agendamentos.size()) {
                    return agendamentos.get(index++);
                }
                return null; // fim da lista
            }
        };
    }

    @Bean
    public ItemProcessor<Appointment, Appointment> agendamentoProcessor() {
        return agendamento -> {
            // validação e preparação do agendamento
            agendamento.setStatus(Status.PRONTO);
            return agendamento;
        };
    }

    @Bean
    public ItemWriter<Appointment> agendamentoWriter() {
        return agendamentos -> {
            for (Appointment agendamento : agendamentos) {
                appointmentRepository.save(agendamento);
            }
        };
    }

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processarAgendamentosJob;

    @Scheduled(cron = "0 /*10 * * * *") // a cada 10 minutos
    public void executarJob() throws Exception {
        jobLauncher.run(processarAgendamentosJob, new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());
    }
}
