package com.easc01.disastermediaapi.util;

import java.util.concurrent.atomic.AtomicLong;

public class IDUtil {

    private static final AtomicLong httpRequestId = new AtomicLong(1);

    public static Long generateHttpRequestId() {
        return httpRequestId.getAndIncrement();
    }

}
