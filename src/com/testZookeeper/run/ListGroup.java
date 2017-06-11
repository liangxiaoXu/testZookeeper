package com.testZookeeper.run;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;

import util.ZkConnectionWatcher;

import com.testZookeeper.config.Constants;

public class ListGroup extends ZkConnectionWatcher{
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ListGroup lGroup = new ListGroup();
		lGroup.connect(Constants.zkAddress);
		lGroup.listGroup("zoo");
		lGroup.close();
	}
	
	public void listGroup(String groupName) throws KeeperException, InterruptedException{
		String path = "/" + groupName;
		if(zk.exists(path, false) == null){
			System.out.printf("group:%s is not exists", path);
			System.exit(1);
		}
		List<String> children = zk.getChildren(path, false);
		if(children.isEmpty()){
			System.out.printf("group:%s is empty", path);
			System.exit(1);
		}
		for(String s : children){
			System.out.println( s );
		}
		
	}
}
