package com.wsheng.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wsheng
 * @Date: 2018/10/22 17:02
 * @Description:
 */
@RestController
@Slf4j
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "this is product msg";
    }
}
