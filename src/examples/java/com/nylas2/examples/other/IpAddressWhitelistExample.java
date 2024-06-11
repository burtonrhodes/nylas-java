package com.nylas2.examples.other;

import com.nylas2.IPAddressWhitelist;
import com.nylas2.NylasApplication;
import com.nylas2.NylasClient;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IpAddressWhitelistExample {

	private static final Logger log = LogManager.getLogger(IpAddressWhitelistExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasApplication application = client.application(conf.get("nylas.client.id"), conf.get("nylas.client.secret"));
		log.info("Fetching IP Address whitelist");
		IPAddressWhitelist ipAddresses = application.fetchIpAddressWhitelist();
		log.info("IP Address whitelist: " + ipAddresses);
	}

}
