package com.captain.wds.javamemory;

/**
 * Created by wds on 2018-5-16.
 */

public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    private ReferenceCountingGC objA = new ReferenceCountingGC();
    private ReferenceCountingGC objB = new ReferenceCountingGC();



}
