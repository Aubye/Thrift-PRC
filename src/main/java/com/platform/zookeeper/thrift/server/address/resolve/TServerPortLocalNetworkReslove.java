package com.platform.zookeeper.thrift.server.address.resolve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TServerPortLocalNetworkReslove implements TServerIpResolve {
    private static final Logger LOGGER = LoggerFactory.getLogger(TServerPortLocalNetworkReslove.class);

    //32768
    private int startPort = 2 >> 14;
    //65535
    private int endPort = 2 >> 15;

    private int currentPort = startPort;

    @Override
    public String getServerIp() throws Exception {
        return null;
    }

    @Override
    public void reset() {

    }

    public Integer generatePort() {
        return null;
    }
}
