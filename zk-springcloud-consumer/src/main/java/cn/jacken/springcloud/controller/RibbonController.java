package cn.jacken.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 自定义负载均衡算法你 实现ribbon效果
 * ribbon和nginx的区别
 * ribbon原理:在调用接口的时候，会在eureka注册中心上获取注册列表信息，获取到之后，缓存在jvm本地中。
 * feign客服端调用的缺点：应该重构接口信息
 */
@RestController
public class RibbonController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    //接口的请求总数
    private  int  reqCount;
    @RequestMapping("/ribbon")
    public  String ribbon(){
        String instances = getInstances()+"/user/getUser";
        System.out.println("Instances:"+instances);
        //使用HTTPClient技术远程调用
        String forObject = restTemplate.getForObject(instances, String.class);
        return forObject;

    }

    private  String getInstances(){
        //从注册中心中获取服务列表信息
        List<ServiceInstance> list = discoveryClient.getInstances("zk-springcloud-producer");
        if(list==null||list.size()==0){
            return null;
        }
        //获取服务器集群的个数
        int  instanceSize=list.size();
        //获取服务器下标
        int serviceIndex=reqCount%instanceSize;
        reqCount++;

        return list.get(serviceIndex).getUri().toString();
    }
}
