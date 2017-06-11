package com.testZookeeper.run;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

import util.ZkConnectionWatcher;

import com.testZookeeper.config.Constants;

/**
 * 创建组
 * @author Administrator
 *
 */
public class CreateGroup extends ZkConnectionWatcher{
	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws KeeperException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		CreateGroup cGroup = new CreateGroup();
		cGroup.connect(Constants.zkAddress);
		cGroup.create("zoo");
		cGroup.close();
	}

	
	public void create(String groupName) throws KeeperException, InterruptedException{
		String path = "/" + groupName;
		if(zk.exists(path, false) == null){
			zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.printf("group:%s,创建成功\n", groupName );	
		}else{
			System.out.printf("group:%s,创建失败，原因：已经存在", groupName );	
		}
	}

}
