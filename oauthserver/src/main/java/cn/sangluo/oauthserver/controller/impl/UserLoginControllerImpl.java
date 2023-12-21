package cn.sangluo.oauthserver.controller.impl;

import cn.sangluo.constant.ResponseStatusCodeConstant;
import cn.sangluo.oauthserver.controller.UserLoginController;
import cn.sangluo.util.ResultJsonUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName UserLoginControllerImpl
 * @Description 认证校验
 * @Author 月痕江离
 * @Date 2023/5/29 3:09
 * @Version 1.0
 */
@RestController
public class UserLoginControllerImpl implements UserLoginController {

    private final WebClient.Builder webClientBuilder;
    @Autowired
    public UserLoginControllerImpl(WebClient.Builder webClientBuilder) {this.webClientBuilder = webClientBuilder;}
    ExecutorService executorService = Executors.newCachedThreadPool();
    @Override
    public ResultJsonUtil<Object> login(Map<String,Object> userInfo) throws ExecutionException, InterruptedException, TimeoutException {
        ResultJsonUtil<Object> resultJsonUtil = null;
        ResultJsonUtil<Object> responseJsonUtil = new ResultJsonUtil<>();
        try{
            String url = "http://127.0.0.1:48081/userInfo/verifyUserByName";
            WebClient webClient = webClientBuilder.build();
            Mono<ResultJsonUtil<Object>> userVerifymono =
                    webClient.post().uri(url).
                            contentType(MediaType.APPLICATION_JSON_UTF8).
                            body(BodyInserters.fromValue(userInfo)).
                            retrieve().
                            bodyToMono(new ParameterizedTypeReference<>() {});
            Future<ResultJsonUtil<Object>> userVerifyfuture = executorService.submit(() -> userVerifymono.block());
            resultJsonUtil = userVerifyfuture.get(5, TimeUnit.SECONDS);
            if(resultJsonUtil.getCode() == 1){
                Map userMap = (Map) resultJsonUtil.getData();
                String userId = userMap.get("userId").toString();
                //注销当前所需要登录id（注销token）
                StpUtil.logout(userId, "PC");
                StpUtil.login(userId,"PC");
                String token = StpUtil.getTokenValue();
                Map<String,Object> setTokenInfo = new HashMap<>();
                setTokenInfo.put("userId",userId);
                setTokenInfo.put("token",token);
                String setTokenUrl = "http://127.0.0.1:48081/userInfo/setUserToken";
                Mono<ResultJsonUtil<Object>> setTokenmono =
                        webClient.post().uri(setTokenUrl).
                                contentType(MediaType.APPLICATION_JSON_UTF8).
                                body(BodyInserters.fromValue(setTokenInfo)).
                                retrieve().
                                bodyToMono(new ParameterizedTypeReference<>() {});
                Future<ResultJsonUtil<Object>> setTokenfuture = executorService.submit(() -> setTokenmono.block());
                ResultJsonUtil<Object> setToken = setTokenfuture.get(5, TimeUnit.SECONDS);
                responseJsonUtil = setToken;
            }else{
                responseJsonUtil.fail(resultJsonUtil.getMsg());
            }
        }catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return responseJsonUtil;
    }

    @Override
    public ResultJsonUtil<Object> logout(String id) {
        StpUtil.logout(id);
        return new ResultJsonUtil<>().customized(ResponseStatusCodeConstant.SUCCESSFUL_CODE,"你已下线","");
    }
}
