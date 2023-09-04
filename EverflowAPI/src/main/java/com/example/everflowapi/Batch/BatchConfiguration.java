package com.example.everflowapi.Batch;

import com.example.everflowapi.UploadType;
import com.example.everflowapi.models.Spid;
import com.example.everflowapi.repositories.SpidRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private SpidRepository SpidRepository;

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
    public Job Job( JobRepository jobRepository,
                   JobCompletionNotificationListener listener, Step step1) {

        UploadType type = getType(null);

        switch (type) {
            case SPID -> {return new JobBuilder("SpidJob", jobRepository)
                    .listener(listener)
                    .start(step1)
                    .build();}
            case METERREADING -> {return null;}
        }
        return null;
    }

    @Bean
    @JobScope
    public String getType(@Value("#{jobParameters[UploadType]}") String typeIn){
        return typeIn;
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