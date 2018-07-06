package com.platform.zookeeper.thrift.server.address.register;

import com.platform.zookeeper.thrift.exception.ThriftException;

public interface TServerAddressRegister {

    /**
     * 发布服务接口
     * @param service 服务接口名称，一个产品中不能重复
     * @param version 服务接口的版本号，默认1.0.0
     * @param address 服务发布的地址和端口
     */
    void register(String service, String version, String address) throws ThriftException;

}
