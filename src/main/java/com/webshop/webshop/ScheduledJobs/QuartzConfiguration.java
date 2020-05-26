package com.webshop.webshop.ScheduledJobs;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail scheduleJob() {
        return JobBuilder.newJob(LogSubmittedOrders.class).storeDurably().withIdentity("orderCount")
                .withDescription("Count Of Submitted Orders").build();
    }

    @Bean
    public Trigger scheduleTrigger() {
        return newTrigger()
                .withIdentity("orderCountTrigger")
                .forJob(scheduleJob())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
    }
}
