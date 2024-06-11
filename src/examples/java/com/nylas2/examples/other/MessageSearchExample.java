package com.nylas2.examples.other;

import com.nylas2.Message;
import com.nylas2.Messages;
import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.RemoteCollection;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageSearchExample {

	private static final Logger log = LogManager.getLogger(MessageSearchExample.class);
	
	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));

		Messages messages = account.messages();
		
		// search
		RemoteCollection<Message> results = messages.search("twitter", 5, 0);
		for (Message message : results) {
			log.info(message);
		}
		
	}
}
