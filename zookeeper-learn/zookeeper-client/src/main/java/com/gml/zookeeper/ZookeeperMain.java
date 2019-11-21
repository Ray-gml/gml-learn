package com.gml.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author gml
 * @description
 * @date 2019/11/21
 */

public class ZookeeperMain {
    private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String conn = "47.95.223.104:2181";
        ZooKeeper zooKeeper = new ZooKeeper(conn, 5000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                latch.countDown();
                System.out.println("已经获得了连接");
            }
        });
        //连接完成之前先等待
        latch.await();
        ZooKeeper.States states = zooKeeper.getState();
        System.out.println(states);
        byte[] data = zooKeeper.getData("/test", false, null);
        System.out.println(new String (data));
    }

}
