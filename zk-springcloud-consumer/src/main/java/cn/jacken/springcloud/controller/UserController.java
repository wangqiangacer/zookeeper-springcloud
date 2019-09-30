package cn.jacken.springcloud.controller;

import cn.jacken.springcloud.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping("/getUser")
    public  String getUser(){
        return userFeign.getUser();
    }

    //获取服务列表信息 使用discoverClient
    @RequestMapping("/getList")
    public  void getDiscoverClientlist(){
        List<ServiceInstance> instances = discoveryClient.getInstances("zk-springcloud-producer");
        for (ServiceInstance instance : instances) {
            System.out.println("uri:"+instance.getUri());
            System.out.println(instance.getServiceId());
            System.out.println(instance.getHost());
        }
    }
}
