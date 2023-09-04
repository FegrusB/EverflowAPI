package com.example.everflowapi.Batch;

import com.example.everflowapi.models.Spid;
import com.example.everflowapi.repositories.SpidRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.database.support.DataFieldMaxValueIncrementerFactory;
import org.springframework.batch.item.database.support.DefaultDataFieldMaxValueIncrementerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private SpidRepository SpidRepository;

    /*
    @Bean
    public ResourcelessTransactionManager batchTransactionManager(){
        ResourcelessTransactionManager transactionManager = new ResourcelessTransactionManager();
        return transactionManager;
    }

    @Bean
    protected JobRepository jobRepository(ResourcelessTransactionManager batchTransactionManager) throws Exception{
        JobRepositoryFactoryBean jobRepository = new JobRepositoryFactoryBean();
        jobRepository.setIncrementerFactory(new DefaultDataFieldMaxValueIncrementerFactory(SpidRepository));
        jobRepository.setTransactionManager(batchTransactionManager);
        return (JobRepository)jobRepository.getObject();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

    */

    // tag::readerwriterprocessor[]
    @Bean
    @StepScope
    public FlatFileItemReader<Spid> reader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) {
        return new FlatFileItemReaderBuilder<Spid>()
                .name("SPIDItemReader")
                .resource( new FileSystemResource(pathToFile))
                .fieldSetMapper(new SPIDFieldSetMapper())
                .linesToSkip(1)
                .lineTokenizer(new DelimitedLineTokenizer())
                .build();

    }

    @Bean
    public RepositoryItemWriter<Spid> writer(){
        return new RepositoryItemWriterBuilder<Spid>().repository(SpidRepository).build();
    }

    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobRepository jobRepository,
                             JobCompletionNotificationListener listener, Step step1) {


        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager){
        return new StepBuilder("step1", jobRepository)
                .<Spid, Spid> chunk(10, transactionManager)
                .reader(reader(null))
                .processor(new Processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}