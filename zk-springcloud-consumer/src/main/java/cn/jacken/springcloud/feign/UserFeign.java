package cn.jacken.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "zk-springcloud-consumer",fallback = UserFeign.UserFeignFallBack.class)
public interface UserFeign {
    @PostMapping("/user/getUser")
      String getUser();

      class UserFeignFallBack implements  UserFeign{


          @Override
          public String getUser() {
              return "系统错误，请稍后重试！";
          }
      }
}
