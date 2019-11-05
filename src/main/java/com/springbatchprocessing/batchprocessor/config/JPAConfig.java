package com.springbatchprocessing.batchprocessor.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
public class JPAConfig {

    private final DataSource dataSource;

//    SELECT * FROM BATCH_JOB_EXECUTION;
//    SELECT * FROM BATCH_JOB_EXECUTION_CONTEXT;
//    SELECT * FROM BATCH_JOB_EXECUTION_PARAMS;
//    SELECT * FROM BATCH_JOB_INSTANCE;
//    SELECT * FROM BATCH_STEP_EXECUTION ;
//    SELECT * FROM BATCH_STEP_EXECUTION_CONTEXT ;
//    SELECT * FROM MOVIE;
    
    @Autowired
    public JPAConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
