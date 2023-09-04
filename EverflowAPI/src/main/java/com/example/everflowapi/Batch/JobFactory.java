package com.example.everflowapi.Batch;

import com.example.everflowapi.UploadType;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class JobFactory {

    JobLauncher jobLauncher;
    Job job;

    JobExplorer jobExplorer;


    @Autowired
    public JobFactory(JobLauncher jobLauncher, Job job, JobExplorer jobExplorer) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.jobExplorer = jobExplorer;
    }

    public void runJob(File file, UploadType type) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(job, new JobParametersBuilder()
                .addString("fullPathFileName", file.getAbsolutePath())
                        .addString("UploadType", type.toString())
                .toJobParameters());

    }

}
