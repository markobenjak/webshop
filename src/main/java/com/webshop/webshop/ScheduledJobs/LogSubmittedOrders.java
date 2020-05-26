package com.webshop.webshop.ScheduledJobs;

import com.webshop.webshop.Order.OrderDao;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class LogSubmittedOrders extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(LogSubmittedOrders.class);

    private final OrderDao orderService;

    public LogSubmittedOrders(OrderDao orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer numberOfSubmittedOrders = orderService.countSubmittedOrders();
        Integer numberOfDraftOrders = orderService.countDraftOrders();


        log.info("------------------------------");
        log.info("Number of submitted orders: " + numberOfSubmittedOrders);
        log.info("Number of draft orders: " + numberOfDraftOrders);
        log.info("------------------------------");
    }
}
