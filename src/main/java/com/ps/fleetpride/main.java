package com.ps.fleetpride;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {
        var start = Instant.now();
        System.out.println("get diff");
        //Thread.sleep(2000);
        var end = Instant.now();
        System.out.println(ChronoUnit.MILLIS.between(start,end));
    }
}
