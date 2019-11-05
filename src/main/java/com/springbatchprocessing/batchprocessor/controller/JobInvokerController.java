package com.springbatchprocessing.batchprocessor.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatchprocessing.batchprocessor.model.Movie;
import com.springbatchprocessing.batchprocessor.repository.MovieRepository;

@RestController
public class JobInvokerController {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
    
	@Autowired
	MovieRepository repo;
 
    @RequestMapping("/invokejob")
    public String handle() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(processJob, jobParameters);
 
        return "Batch job has been invoked";
    }
    
    @RequestMapping("/testjpa")
    public Movie store(@RequestBody Movie m) throws Exception {
 
          
 
        return  repo.save(m);
    }
}