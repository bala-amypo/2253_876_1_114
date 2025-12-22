package com.example.demo.util;

import java.util.concurrent.atomic.AtomicLong;

public class TokenNumberGenerator {

    private static final AtomicLong counter = new AtomicLong(1);

    private TokenNumberGenerator() {
        // utility class → no objects allowed
    }

    public static Long generateTokenNumber() {
        return counter.getAndIncrement();
    }
}
