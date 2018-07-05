package com.platform.zookeeper.factory;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

/**
 * 获取Zookeeper客户端链接
 * @author sunjc
 * @date 2018-07-05
 */
public class ZookeeperFactory implements FactoryBean<CuratorFramework> {
    private static final String ROOT = "thrift-rpc";

    private String zkHosts;

    /** 超时时间 */
    private int sessionTimeout = 30000;
    private int connectionTimeout = 30000;

    private int baseSleepTime = 1000;

    /** 共享一个zk链接 */
    private boolean singleton = true;

    /**全局path前缀,常用来区分不同的应用 */
    private String namespace;


    private CuratorFramework zkClient;

    public void setZkHosts(String zkHosts) {
        this.zkHosts = zkHosts;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setBaseSleepTime(int baseSleepTime) {
        this.baseSleepTime = baseSleepTime;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public CuratorFramework getObject() throws Exception {
        if (singleton) {
            if (zkClient == null) {
                zkClient = create();
                zkClient.start();
            }
        }
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    @Override
    public boolean isSingleton() {
        return singleton;
    }

    public CuratorFramework create() {
        if (StringUtils.isEmpty(namespace)) {
            namespace = ROOT;
        } else {
            namespace = ROOT +"/"+ namespace;
        }
        return create(zkHosts, sessionTimeout, connectionTimeout, namespace, baseSleepTime);
    }

    public static CuratorFramework create(String connectString,
                                          int sessionTimeout,
                                          int connectionTimeout,
                                          String namespace,
                                          int baseSleepTime) {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        return builder
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeout)
                .connectionTimeoutMs(connectionTimeout)
                .canBeReadOnly(true)
                .namespace(namespace)
                .retryPolicy(new ExponentialBackoffRetry(baseSleepTime, Integer.MAX_VALUE))
                .defaultData(null)
                .build();
    }

    public void close() {
        if (zkClient != null) {
            zkClient.close();
        }
    }

}
