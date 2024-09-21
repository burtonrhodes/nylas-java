package com.nylas2.examples.other;

import com.nylas2.Drafts;
import com.nylas2.Message;
import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendRawMimeExample {

	private static final Logger log = LogManager.getLogger(SendRawMimeExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));
		Drafts drafts = account.drafts();
		String rawMime = conf.get("send.rawmime");
		log.info("Sending raw mime:");
		log.info(rawMime);
		Message sentMessage = drafts.sendRawMime(rawMime);
		log.info("Sent message: " + sentMessage);
	}

}
