package cn.sangluo.gateway.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.sangluo.util.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
    private final WebClient.Builder webClientBuilder;
    @Autowired
    public StpInterfaceImpl(WebClient.Builder webClientBuilder) {this.webClientBuilder = webClientBuilder;}

    ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> strs = new ArrayList<>();
        try {
            String url = "http://127.0.0.1:48081/role/getAllPermissionList?userId=" + loginId;
            WebClient webClient = webClientBuilder.build();
            Mono<ResultJsonUtil<Object>> mono =
                    webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<>() {});
            Future<ResultJsonUtil<Object>> future = executorService.submit(() -> mono.block());
            ResultJsonUtil<Object> roleRes = future.get(5, TimeUnit.SECONDS);
            strs = (List<String>) roleRes.getData();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return strs;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> strs = new ArrayList<>();
        try {
            String url = "http://127.0.0.1:48081/role/verifyRoleByUserId?userId=" +loginId;
            WebClient webClient = webClientBuilder.build();
            Mono<ResultJsonUtil<Object>> mono =
                    webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<>() {});
            Future<ResultJsonUtil<Object>> future = executorService.submit(() -> mono.block());
            ResultJsonUtil<Object> roleRes = future.get(5, TimeUnit.SECONDS);
            strs = (List<String>) roleRes.getData();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return strs;
    }
}
