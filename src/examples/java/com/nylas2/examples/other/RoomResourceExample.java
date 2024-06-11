package com.nylas2.examples.other;

import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.RoomResource;
import com.nylas2.RoomResources;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RoomResourceExample {

	private static final Logger log = LogManager.getLogger(RoomResourceExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));

		RoomResources roomResource = account.roomResources();
		List<RoomResource> roomResourceList = roomResource.list();
		for(RoomResource resource : roomResourceList) {
			log.info(resource);
		}
	}
}
