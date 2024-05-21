package cn.sangluo.oauthserver.feign;
import cn.sangluo.util.ResultJsonUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @ClassName UserFeignService
 * @Description TODO
 * @Author 月痕江离
 * @Date 2023/6/8 23:33
 * @Version 1.0
 */
@FeignClient(name = "UserService", url = "http://localhost:48081")
public interface UserFeignService {
    @PostMapping("/userInfo/verifyUserByName")
    ResultJsonUtil<Object> verifyUserByName(@RequestBody Map<String,Object> userInfo);
    @PostMapping("/userInfo/setUserToken")
    ResultJsonUtil<Object> setUserToken(@RequestBody Map<String,Object> Info);
    @PostMapping("/userInfo/Test")
    ResultJsonUtil<Object> Test(@RequestBody Map<String,Object> Info);
}
