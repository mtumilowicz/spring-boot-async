package com.example.slowcalculator;

import com.google.common.math.IntMath;
import org.springframework.stereotype.Service;

/**
 * Created by mtumilowicz on 2018-07-26.
 */
@Service
public class ComputationsService {
    public int add(int x, int y) throws InterruptedException {
        Thread.sleep(5000);
        return IntMath.checkedAdd(x, y);
    }
}
