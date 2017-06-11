package com.testZookeeper.run;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.zookeeper.KeeperException;

import util.ZkConnectionWatcher;

import com.testZookeeper.config.Constants;

/**
 * ���úͻ�ȡ����
 * @author Administrator
 *
 */
public class GetSetData extends ZkConnectionWatcher{
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		GetSetData gsGroup = new GetSetData();
		gsGroup.connect(Constants.zkAddress);
		gsGroup.setData("zoo", "cat", "С��è");
		gsGroup.getData("zoo", "cat");
		gsGroup.setData("zoo", "cat", "��è");
		gsGroup.getData("zoo", "cat");
		gsGroup.close();
	}
	
	public void setData(String groupName, String memberName, String data) throws KeeperException, InterruptedException{
		String path = "/" + groupName + "/" + memberName;
		zk.setData(path, data.getBytes(Charset.forName("UTF-8")), -1);
	}
	public void getData(String groupName, String memberName) throws KeeperException, InterruptedException{
		String path = "/" + groupName + "/" + memberName;
		byte[] b = zk.getData(path, false, null);
		System.out.println( "����Ϊ:" + new String(b, Charset.forName("UTF-8")) );
	}
	
}
