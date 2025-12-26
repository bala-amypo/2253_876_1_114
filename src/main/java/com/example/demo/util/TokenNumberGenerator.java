package com.example.demo.util;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenNumberGenerator {

    private static final AtomicInteger counter = new AtomicInteger(1000);

    private TokenNumberGenerator() {
        // utility class
    }
    

    public static String generateTokenNumber() {
        return "T" + counter.incrementAndGet();
    }
}

