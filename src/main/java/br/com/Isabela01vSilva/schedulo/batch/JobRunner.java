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
    private Job processAppointmentsJob;

    @Scheduled(fixedDelay = 10000) // every 10 seconds
    public void executeJob() throws Exception {
        jobLauncher.run(processAppointmentsJob, new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());
    }
}
