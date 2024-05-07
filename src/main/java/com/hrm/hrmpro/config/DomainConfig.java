package com.hrm.hrmpro.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.hrm.hrmpro.domain")
@EnableJpaRepositories("com.hrm.hrmpro.repos")
@EnableTransactionManagement
public class DomainConfig {
}
