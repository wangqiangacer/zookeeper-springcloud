package cn.jacken.springcloud.controller;

import cn.jacken.springcloud.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserFeign userFeign;
    @RequestMapping("/getUser")
    public  String getUser(){
        return userFeign.getUser();
    }
}
