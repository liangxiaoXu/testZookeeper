package util;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZkConnectionWatcher implements Watcher{

	private static final int SESSION_TIMEOUT = 5000;
	protected ZooKeeper zk ;
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	
	/**
	 * ����zk
	 * ʹ��CountDownLatch��Ŀ���ǵ�zk���ӻ�ȡ��֮�����ʹ��
	 * @param hosts
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void connect(String hosts) throws IOException, InterruptedException{
		zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
		countDownLatch.await();
	}
	
	@Override
	public void process(WatchedEvent event) {
		if(event.getState() == KeeperState.SyncConnected){
			countDownLatch.countDown();
		}
	}
	
	public void close() throws InterruptedException{
		zk.close();
	}

}
