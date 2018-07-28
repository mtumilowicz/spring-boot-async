package com.example.slowuserservice.infrastructure.utils;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
public class ThreadUtils {
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // only for showcase purpose
        }
    }
}
