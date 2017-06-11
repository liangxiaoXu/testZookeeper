package com.testZookeeper.run;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

import util.ZkConnectionWatcher;

import com.testZookeeper.config.Constants;

/**
 * 加入组
 * @author Administrator
 *
 */
public class JoinGroup extends ZkConnectionWatcher{
	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws KeeperException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		JoinGroup cGroup = new JoinGroup();
		cGroup.connect(Constants.zkAddress);
		cGroup.join("zoo", "dog");
		cGroup.join("zoo", "cat");
		cGroup.close();
	}

	
	public void join(String groupName, String memberName) throws KeeperException, InterruptedException{
		String path = "/" + groupName + "/" + memberName;
		if(zk.exists(path, false) == null){
			zk.create(path, new String("这是数据").getBytes(Charset.forName("UTF-8")), 
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.printf("创建成功。path:%s\n", path);
		}else{
			System.out.printf("创建失败。path:%s已经存在\n", path);
		}
		
		
	}

}
