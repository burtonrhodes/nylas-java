package com.nylas2.examples.other;

import java.nio.file.Path;

import com.nylas2.File;
import com.nylas2.FileQuery;
import com.nylas2.Files;
import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.RemoteCollection;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilesExample {

	private static final Logger log = LogManager.getLogger(FilesExample.class);

	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));
		Files files = account.files();
		RemoteCollection<File> allFiles = files.list(new FileQuery());
		for (File file : allFiles) {
			log.info("File: " + file);
		}
		
		File first = allFiles.iterator().next();
		byte[] fileBytes = files.downloadBytes(first.getId());
		
		Path tmpfile = java.nio.file.Files.createTempFile(first.getFilename(), null);
		java.nio.file.Files.write(tmpfile, fileBytes);
		
		File uploaded = files.upload(first.getFilename(), first.getContentType(), fileBytes);
		log.info("Uploaded: " + uploaded);
		
		files.delete(uploaded.getId());
		log.info("deleted");
	}

}
