package com.springbatchprocessing.batchprocessor.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.springbatchprocessing.batchprocessor.etlstep.CustomProcessor;
import com.springbatchprocessing.batchprocessor.etlstep.CustomWriter;
import com.springbatchprocessing.batchprocessor.listener.JobCompletionListener;
import com.springbatchprocessing.batchprocessor.model.*;

@Configuration
@EnableBatchProcessing
public class BatchProcessingConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	
	@Bean
	public FlatFileItemReader<Movie> reader() 
	{
	    //Create reader instance
	    FlatFileItemReader<Movie> reader = new FlatFileItemReader<Movie>();
	     
	    //Set input file location
	    reader.setResource(new ClassPathResource("movies.csv"));
	     
	    //Set number of lines to skips. Use it if file has header rows.
	    reader.setLinesToSkip(1);   
	     
	    //Configure how each line will be parsed and mapped to different values
	    reader.setLineMapper(new DefaultLineMapper<Movie>() {
	        {
	            //3 columns in each row
	            setLineTokenizer(new DelimitedLineTokenizer() {
	                {
	                    setNames(new String[] { "id", "title", "genres" });
	                }
	            });
	            //Set values in Movies class
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {
	                {
	                    setTargetType(Movie.class);
	                }
	            });
	        }
	    });
	    return reader;
	}    

	
	@Bean
	public CustomProcessor processor() {
	    return new CustomProcessor();
	}
	
	@Bean
	public CustomWriter writer() {
		return new CustomWriter();
	}

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(movieImport()).end().build();
	}

	@Bean
	public Step movieImport() {
		return  stepBuilderFactory.get("step")
				.<Movie, Movie> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}
