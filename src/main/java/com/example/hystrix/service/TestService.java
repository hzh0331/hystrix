package com.example.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public void test1(int input){
        System.out.println("test1 input:"+input);
        System.out.println("test1 output:"+1/input);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public void test2(int input) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1500);
        System.out.println("test2 "+input);
    }

    public void fallbackMethod(int input, Throwable throwable){
        System.out.println("execute fail, the input is "+input + ", the exception is " + throwable);
    }
}
