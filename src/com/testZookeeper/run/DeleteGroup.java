package com.testZookeeper.run;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;

import util.ZkConnectionWatcher;

import com.testZookeeper.config.Constants;

/**
 * É¾³ý×é
 * @author Administrator
 *
 */
public class DeleteGroup extends ZkConnectionWatcher{
	
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		DeleteGroup dGroup = new DeleteGroup();
		dGroup.connect(Constants.zkAddress);
		dGroup.deleteGroup("zoo/dog");
		dGroup.deleteGroup("zoo");
		dGroup.close();
	}

	
	public void deleteGroup(String groupName) throws InterruptedException, KeeperException{
		String path = "/" + groupName;
		zk.delete(path, -1);
	}

}
