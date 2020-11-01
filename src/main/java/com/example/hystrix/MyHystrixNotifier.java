package com.example.hystrix;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;

public class MyHystrixNotifier extends HystrixEventNotifier {
    @Override
    public void markEvent(HystrixEventType eventType, HystrixCommandKey commandKey){
        System.out.printf("-->Event, type=%s, Key=%s%n", eventType, commandKey);
    }
}
