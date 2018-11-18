package com.wsheng.order.controller;

import com.wsheng.order.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wsheng
 * @Date: 2018/10/30 17:44
 * @Description:
 */
@RestController
public class GirlController {

    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/gitl/print")
    public String girlprint(){
        return "name" + girlConfig.getName() + "age" +girlConfig.getAge();
    }
}
