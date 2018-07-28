package com.example.slowuserservice;

/**
 * Created by mtumilowicz on 2018-07-28.
 */
class ThreadUtils {
    static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // only for showcase purpose
        }
    }
}
