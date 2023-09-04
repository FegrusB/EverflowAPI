package com.example.everflowapi.Batch;

import com.example.everflowapi.models.Spid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Collection<StepExecution> steps = jobExecution.getStepExecutions();

            jdbcTemplate.query("SELECT * FROM spids WHERE ",
                    (rs, row) -> new Spid(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getFloat(6),
                            rs.getBoolean(7),
                            rs.getInt(8),
                            rs.getString(9),
                            rs.getInt(10),
                            rs.getString(11),
                            rs.getInt(12))
            ).forEach(person -> log.info("Found <{{}}> in the database.", person));
        }
    }
}