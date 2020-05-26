package com.webshop.webshop;

/*
import com.webshop.webshop.ScheduledJobs.SimpleJob;
*/
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebshopApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(WebshopApplication.class, args);
	/*	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();

			JobDetail job = JobBuilder.newJob(SimpleJob.class)
					.withIdentity("myJob", "group1")
					.usingJobData("jobSays", "Hello World!")
					.usingJobData("myFloatValue", 3.141f)
					.build();

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("myTrigger", "group1")
					.startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
					.build();

			scheduler.scheduleJob(job, trigger);
			scheduler.start();

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	*/
	}
}
