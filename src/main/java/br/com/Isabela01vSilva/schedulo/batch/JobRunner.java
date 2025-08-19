package br.com.Isabela01vSilva.schedulo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobRunner {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processarAgendamentosJob;

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(fixedDelay = 10000) // a cada 10 segundos
    public void executarJob() throws Exception {
        jobLauncher.run(processarAgendamentosJob, new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());
    }
}
