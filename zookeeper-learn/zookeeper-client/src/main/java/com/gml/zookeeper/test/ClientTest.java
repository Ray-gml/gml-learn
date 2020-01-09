package com.gml.zookeeper.test;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author gml
 * @description
 * @date 2019/11/21
 */

public class ClientTest {

    ZooKeeper zooKeeper;
    CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void init() throws IOException, InterruptedException {
        System.out.println("开始连接zk");
        String conn = "47.95.223.104:2181";
        zooKeeper = new ZooKeeper(conn, 10000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if (Watcher.Event.KeeperState.SyncConnected.equals(watchedEvent.getState())){
                    latch.countDown();
                    System.out.println("连接成功" + watchedEvent);
                }
            }
        });
        latch.await();
    }

    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/test", false, null);
        System.out.println(new String(data));
    }

    @Test
    public void getData2() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/test", true, null);
        System.out.println(data);
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void getData3() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/test", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    zooKeeper.getData(watchedEvent.getPath(), this, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(watchedEvent.getPath());
            }
        }, null);
        System.out.println(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }

}
