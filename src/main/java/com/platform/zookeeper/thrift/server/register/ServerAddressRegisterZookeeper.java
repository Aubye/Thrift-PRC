package com.platform.zookeeper.thrift.server.register;

import com.platform.zookeeper.thrift.exception.ThriftException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 注册服务列表到zookeeper
 */
public class ServerAddressRegisterZookeeper implements ServerAddressRegister {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerAddressRegisterZookeeper.class);

    private CuratorFramework zkClient;

    public ServerAddressRegisterZookeeper() {
    }

    public ServerAddressRegisterZookeeper(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void register(String service, String version, String address) throws ThriftException {
        if(zkClient.getState() == CuratorFrameworkState.LATENT){
            zkClient.start();
        }
        if(StringUtils.isEmpty(version)){
            version="1.0.0";
        }
        //临时节点
        try {
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath("/"+service+"/"+version+"/"+address);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("register service address to zookeeper exception:{}", e);
            throw new ThriftException("register service address to zookeeper exception: address UnsupportedEncodingException", e);
        } catch (Exception e) {
            LOGGER.error("register service address to zookeeper exception:{}", e);
            throw new ThriftException("register service address to zookeeper exception:{}", e);
        }
    }

    public void close() {
        zkClient.close();
    }

}
