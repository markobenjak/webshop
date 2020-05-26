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
	}
}
