package com.nylas2.examples.other;

import java.util.List;

import com.nylas2.Folder;
import com.nylas2.FolderQuery;
import com.nylas2.Folders;
import com.nylas2.NylasAccount;
import com.nylas2.NylasClient;
import com.nylas2.RequestFailedException;
import com.nylas2.Thread;
import com.nylas2.ThreadQuery;
import com.nylas2.Threads;
import com.nylas2.examples.ExampleConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FoldersExample {

	private static final Logger log = LogManager.getLogger(FoldersExample.class);
	
	public static void main(String[] args) throws Exception {
		ExampleConf conf = new ExampleConf();
		NylasClient client = new NylasClient();
		NylasAccount account = client.account(conf.get("access.token"));
		
		Folders folders = account.folders();
		
		FolderQuery fQuery = new FolderQuery()
				.limit(5)
				.offset(0);
		
		List<Folder> allFolders = folders.list(fQuery).fetchAll();
		Folder inbox = null;
		for (Folder folder : allFolders) {
			log.info(folder);
			if ("inbox".equals(folder.getName())) {
				inbox = folder;
			}
		}

		Folder newFolder = folders.create("Example Folder 7");
		log.info(newFolder);
		newFolder.setDisplayName("My Renamed Folder");
		Folder updatedFolder = folders.update(newFolder);
		log.info(updatedFolder);
		
		Threads threads = account.threads();
		List<Thread> threadList = threads.list(new ThreadQuery().limit(1)).fetchAll();
		if (threadList.isEmpty()) {
			log.info("No threads");
			return;
		}
		
		String threadId = threadList.get(0).getId();

		Thread thread = threads.setFolderId(threadId, updatedFolder.getId());
		log.info(thread);
		
		try {
			folders.delete(updatedFolder.getId());
		} catch (RequestFailedException rfe) {
			log.info(rfe.getErrorMessage());
		}
		
		
		thread = threads.setFolderId(threadId, inbox.getId());
		log.info(thread);
		
		folders.delete(updatedFolder.getId());
	}
}
