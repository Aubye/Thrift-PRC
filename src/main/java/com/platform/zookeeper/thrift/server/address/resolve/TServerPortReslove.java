package com.platform.zookeeper.thrift.server.address.resolve;

public interface TServerPortReslove {

    void setStartPort();

    int getFreePort();

    void resetPort();

}
