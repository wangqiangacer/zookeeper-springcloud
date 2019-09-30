package cn.jacken.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 *
 *
 * 创建临时节点
 * zookeeper 没有服务保护机制  临时节点和生命周期关联的 当服务断开连接之后，该节点会自动被剔除
 *
 * ribbon本地客服端负载均衡器
 *  负载均衡算法：请求数%服务器数量 得到服务器下标
 *
 */
@SpringBootApplication
@EnableDiscoveryClient //表示向注册中心注册服务
@EnableFeignClients(basePackages = "cn.jacken.springcloud.feign")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
