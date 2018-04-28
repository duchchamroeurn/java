package com.sangkhim.spring.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class Job1Scheduler implements JobScheduler {
	
	ThreadPoolTaskScheduler scheduler;
	
	Runnable task;
	
	@Autowired
	ApplicationContext	appContext;
	
	@Autowired
	Job1 job1;

	public void executeSchedule() {
		appContext 	= new AnnotationConfigApplicationContext(ThreadPoolTaskScheduler.class);
		scheduler  	= (ThreadPoolTaskScheduler) appContext.getBean("threadPoolTaskScheduler", ThreadPoolTaskScheduler.class);

		task	 	= (Runnable) job1;
		
		/**
		 * http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
		 * Expression					Meaning
		 * "0 0 12 * * ?"	 			Fire at 12pm (noon) every day
		 * "0 15 10 ? * *"	 			Fire at 10:15am every day
		 * "0 15 10 * * ?"	 			Fire at 10:15am every day
		 * "0 15 10 * * ? *"	 		Fire at 10:15am every day
		 * "0 15 10 * * ? 2005"	 		Fire at 10:15am every day during the year 2005
		 * "0 * 14 * * ?"	 			Fire every minute starting at 2pm and ending at 2:59pm, every day 
		 * "0 0/5 14 * * ?"	 			Fire every 5 minutes starting at 2pm and ending at 2:55pm, every day
		 * "0 0/5 14,18 * * ?"	 		Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day
		 * "0 0-5 14 * * ?"	 			Fire every minute starting at 2pm and ending at 2:05pm, every day
		 * "0 10,44 14 ? 3 WED"	 		Fire at 2:10pm and at 2:44pm every Wednesday in the month of March.
		 * "0 15 10 ? * MON-FRI"		Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday
		 * "0 15 10 15 * ?"	 			Fire at 10:15am on the 15th day of every month
		 * "0 15 10 L * ?"	 			Fire at 10:15am on the last day of every month
		 * "0 15 10 ? * 6L"	 			Fire at 10:15am on the last Friday of every month
		 * "0 15 10 ? * 6L"	 			Fire at 10:15am on the last Friday of every month
		 * "0 15 10 ? * 6L 2002-2005"	Fire at 10:15am on every last Friday of every month during the years 2002, 2003, 2004 and 2005
		 * "0 15 10 ? * 6#3"	 		Fire at 10:15am on the third Friday of every month
		 */
		CronTrigger trigger = new CronTrigger("3/5 * * * * ?");
		scheduler.setBeanName("job1");
    	scheduler.schedule(task, trigger);
    	
    	if(false) {
    		executeCancel();
    	}
	}
	
	public void executeCancel() {
		scheduler.shutdown();
	}
	
}