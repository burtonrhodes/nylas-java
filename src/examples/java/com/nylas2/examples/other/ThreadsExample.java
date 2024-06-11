package com.nylas2.examples.other;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.Thread;
import com.nylas2.ThreadQuery;
import com.nylas2.Threads;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadsExample {

	private static final Logger log = LogManager.getLogger(ThreadsExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));
		Threads threads = account.threads();
		
		Instant end = ZonedDateTime.now().toInstant();
		Instant start = end.minus(1, ChronoUnit.DAYS);
		ThreadQuery query = new ThreadQuery()
				.limit(55)
				.lastMessageAfter(start)
				.lastMessageBefore(end)
				;
		List<Thread> allThreads = threads.list(query).chunkSize(10).fetchAll();
		log.info("result thread count: " + allThreads.size());
		for (Thread thread : allThreads) {
			log.info(thread);
		}
	}
}
