package com.platform.zookeeper.thrift.exception;

public class ThriftException extends Exception {

    public ThriftException() {
    }

    public ThriftException(String message) {
        super(message);
    }

    public ThriftException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThriftException(Throwable cause) {
        super(cause);
    }

    public ThriftException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
