package com.example.hystrix;

import com.example.hystrix.service.TestService;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCircuitBreaker
public class HystrixApplication {
    public static void main(String[] args) throws InterruptedException {
        HystrixPlugins.getInstance().registerEventNotifier(new MyHystrixNotifier());
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(HystrixApplication.class, args);
        TestService testService = configurableApplicationContext.getBean(TestService.class);
        //1
        testService.test1(1);
        //3
        testService.test1(0);
        //2
        testService.test1(2);
        //4
        testService.test2(1);


//        for (int i = 0; i < 30; i ++){
//            testService.test1(i < 20 ? 0 : 1);
//            TimeUnit.MILLISECONDS.sleep(300);
//        }

//        for (int i = 0; i < 5; i++){
//            testService.test3(i<3?0:1);
//            TimeUnit.MILLISECONDS.sleep(250);
//        }

//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        for (int i = 0; i < 20; i++){
//            int tempI = i;
//            executorService.submit(()->testService.test7(tempI+1));
//        }
//        executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.SECONDS);
//        System.out.println("---wait 5 sec-");
//        TimeUnit.SECONDS.sleep(5);
//        testService.test7(1);
//        System.out.println("---wait 5 sec-");
//        TimeUnit.SECONDS.sleep(5);
//        testService.test7(1);
    }
}
