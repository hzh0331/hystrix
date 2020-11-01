package com.example.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    /**
     * divide by zero exception
     * @param input
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public void test1(int input){
        System.out.println("test1 input:"+input);
        System.out.println("test1 output:"+1/input);
    }

    /**
     * time out exception
     * @param input
     * @throws InterruptedException
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public void test2(int input) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("test2 "+input);
    }

    /**
     * configuration with annotation
     * @param input
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod",
    commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "500"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "1"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000")

    })
    public void test3(int input){
        System.out.println("test3 output:"+1/input);
    }

    /**
     * configure by application.properties file
     * @param input
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandKey = "tt")
    public void test4(int input){
        System.out.println("test3 output:"+1/input);
    }

    /**
     * thread pool test
     * @param input
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public void test5(int input){
        System.out.println("thread: "+Thread.currentThread().getName()+" input: "+input+" output"+1/input);
    }

    /**
     * thread pool test, change property by application.properties file
     * @param input
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod", threadPoolKey = "myThreadPool")
    public void test6(int input){
        System.out.println("thread: "+Thread.currentThread().getName()+" input: "+input+" output"+1/input);
    }

    /**
     * thread pool test, change property by annotation
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod", threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "3"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
            }
    )
    public void test7(int input){
        System.out.println("thread: "+Thread.currentThread().getName()+" input: "+input+" output"+1/input);
    }

    /**
     * fall back method without throwable
     * @param input
     */
//    public void fallbackMethod(int input){
//        System.out.println("execute fail, the input is "+ input);
//    }

    /**
     * fall back method with throwable
     * @param input
     * @param throwable
     */
//    public void fallbackMethod(int input, Throwable throwable){
//        System.out.println("execute fail, the input is "+input + ", the exception is " + throwable);
//    }

    /**
     * thread pool fall back method
     * @param input
     * @param throwable
     */
    public void fallbackMethod(int input, Throwable throwable){
        System.out.println("execute fail, thd is "+ Thread.currentThread().getName() + ",the input is "+input + ", the exception is " + throwable);
    }
}
