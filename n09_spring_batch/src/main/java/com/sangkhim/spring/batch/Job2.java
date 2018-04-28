package com.sangkhim.spring.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Job2 implements Runnable {
	
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Job2 : " + dateFormat.format(date));
	}
	
}