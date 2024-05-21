package cn.sangluo.gateway.config;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName StpInterfaceImpl
 * @Description TODO
 * @Author 月痕江离
 * @Date 2023/5/27 15:39
 * @Version 1.0
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    /**
     *当前全部是模拟数据，真实情况使用根据loginId动态查询对应角色和权限
     */
    private final RedisTemplate<String, String> redisTemplate;
    @Autowired
    public StpInterfaceImpl(RedisTemplate<String, String> redisTemplate) {this.redisTemplate = redisTemplate;}

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String companyId = redisTemplate.opsForValue().get(loginId.toString());
        String roleJson = redisTemplate.opsForValue().get(Objects.requireNonNull(companyId));
        JSONArray jsonArray = JSON.parseArray(roleJson);
        assert jsonArray != null;
        List<String> roles = jsonArray.toJavaList(String.class);;
        return roles;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //        try {
//            strs = (List<String>) roleRes.getData();
//        } catch (InterruptedException | TimeoutException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        return new ArrayList<>();
    }
}
