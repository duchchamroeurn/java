package com.sangkhim.spring.batch;

import java.util.Map;

public class ScheduleLoader {
	
	private Map<String, JobScheduler> jobSchedulerMap;
	
	public void setJobSchedulerMap(Map<String, JobScheduler> jobSchedulerMap) {
		this.jobSchedulerMap = jobSchedulerMap;
	}
	
	public void init() {
		scheduleStartBuild();
	}

	private final synchronized boolean scheduleStartBuild() {
		try {
			executeJob();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	final public void executeJob() {
		for (int i = 1; i <= 2; i++) {
			String key = "job" + i;
			JobScheduler jobScheduler = jobSchedulerMap.get(key);
			if(jobScheduler != null) jobScheduler.executeSchedule();
		}
	}
	
	final public void destroyJob() {
		for (int i = 1; i <= 2; i++) {
			String key = "job" + i;
			JobScheduler jobScheduler = jobSchedulerMap.get(key);
			if(jobScheduler != null) jobScheduler.executeCancel();
		}
	}
	
	public final synchronized boolean scheduleRebuild() {
        try {
        	destroyJob();
        	executeJob();
        } catch (Exception e) {
            return false;
        }
        return true;
	}
	
}