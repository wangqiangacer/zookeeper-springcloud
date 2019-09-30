package cn.jacken.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getUser")
    public  String getUser(){
        return "springboot+zookeeper";
    }
}
