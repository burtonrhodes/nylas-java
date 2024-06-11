package com.nylas2.examples.other;

import com.nylas2.JobStatus;
import com.nylas2.JobStatusQuery;
import com.nylas2.JobStatuses;
import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.RemoteCollection;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobStatusExample {

	private static final Logger log = LogManager.getLogger(JobStatusExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));
		JobStatuses jobStatuses = account.jobStatuses();
		
		long count = jobStatuses.count(new JobStatusQuery());
		log.info("Job status count: " + count);
		
		JobStatusQuery query = new JobStatusQuery().limit(50);
		RemoteCollection<JobStatus> allJobStatuses = jobStatuses.list(query);
		JobStatus lastStatus = null;
		for (JobStatus jobStatus: allJobStatuses) {
			log.info(jobStatus);
			lastStatus = jobStatus;
		}
		
		if (lastStatus != null) {
			JobStatus jobStatus = jobStatuses.get(lastStatus.getId());
			log.info("status: " + jobStatus);
		}
		
	}

}
