package com.wsheng.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Auther: wsheng
 * @Date: 2018/11/9 14:46
 * @Description:
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    /**
     * 服务降级，不只是目标服务无法提供正常服务，采取的降级。还可以是自己服务降级。
     * @return
     */
    //超时时间的配置（默认1S）
    /*@HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/

    //熔断的配置
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    //@HystrixCommand
    @GetMapping("/getlistForOrder")
    public String getlistForOrder(@RequestParam("number") Integer number){
        if (number % 2 == 0){
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.postForObject("http://127.0.0.1:8001/product/listForOrder",
                Arrays.asList("123456"),
                String.class);
        //throw new RuntimeException("服务异常。。。。");(也可以降级)
    }

    //服务的降级
    public String fallback(){
        return "服务拥挤，请稍等。。。。。。";
    }

    //默认降级
    public String defaultFallback(){
        return "默认降级。请稍后。。。。。。";
    }
}
