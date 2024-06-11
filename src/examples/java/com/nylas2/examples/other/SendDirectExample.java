package com.nylas2.examples.other;

import com.nylas2.Draft;
import com.nylas2.Drafts;
import com.nylas2.Message;
import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.Tracking;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendDirectExample {

	private static final Logger log = LogManager.getLogger(SendDirectExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));
		Drafts drafts = account.drafts();

		Draft draft = new Draft();
		draft.setFrom(conf.getNameEmail("send.from"));
		draft.setTo(conf.getNameEmailList("send.to"));
		draft.setCc(conf.getNameEmailList("send.cc"));
		draft.setBcc(conf.getNameEmailList("send.bcc"));
		draft.setSubject(conf.get("send.subject"));
		draft.setBody(conf.get("send.body"));
		
		Tracking tracking = new Tracking();
		tracking.setOpens(true);
		tracking.setLinks(true);
		tracking.setThreadReplies(true);
		tracking.setPayload("send-direct-example");
		draft.setTracking(tracking);
		
		Message sentMessage = drafts.send(draft);
		log.info("Sent message: " + sentMessage);
	}

}
