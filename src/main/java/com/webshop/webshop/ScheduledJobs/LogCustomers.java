package com.webshop.webshop.ScheduledJobs;

import com.webshop.webshop.Customer.CustomerDao;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class LogCustomers extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(LogCustomers.class);

    private final CustomerDao customerService;

    public LogCustomers(CustomerDao customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer currentUsersCount = customerService.CountCurrentUsers();

        log.info(" ");
        log.info(String.format("There currently exists %s customers.", currentUsersCount));
        log.info(" ");
    }
}