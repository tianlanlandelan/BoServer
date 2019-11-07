package com.justdoit.kyle.controller;
import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2019-08-28 09:05:54
 * 接口转发
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class ApiController {

    @Resource
    HttpServletRequest request;

    @Autowired
    RestTemplate restTemplate;

    // TODO 转发前校验JWT和用户权限

    /**
     * json 格式的GET请求
     * @param params
     * @return
     */
    @GetMapping
    public ResponseEntity get(@RequestBody Map<String,String> params){

        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(RequestUtil.getUrl(params,request), String.class,params);
            RequestUtil.successLog(request,params,responseEntity);
        }catch (Exception e){
            responseEntity = ResponseUtils.getResponseFromException(e);
            RequestUtil.errorLog(request,params,responseEntity);
        }
        return responseEntity;
    }


    /**
     * JSON  形式的 POST 请求
     * @param params
     * @return
     */
    @PostMapping
    public ResponseEntity post(@RequestBody Map<String,String> params){
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.postForEntity(RequestUtil.getUrl(params,request),null,String.class,params);
            RequestUtil.successLog(request,params,responseEntity);
        }catch (Exception e){
            responseEntity = ResponseUtils.getResponseFromException(e);
            RequestUtil.errorLog(request,params,responseEntity);
        }
        return responseEntity;
    }

    /**
     * JSON 格式的 PUT 请求
     * @param params
     * @return
     */
    @PutMapping
    public ResponseEntity put(@RequestBody Map<String,String> params){
        ResponseEntity responseEntity = MyResponse.ok();
        try {
            restTemplate.put(RequestUtil.getUrl(params,request),null,params);
            RequestUtil.successLog(request,params,responseEntity);
        }catch (Exception e){
            responseEntity = ResponseUtils.getResponseFromException(e);
            RequestUtil.errorLog(request,params,responseEntity);
        }
        return responseEntity;
    }

    /**
     * JSON 形式的 DELETE 请求
     * @param params
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(@RequestBody Map<String,String> params){
        ResponseEntity responseEntity = MyResponse.ok();
        try {
            restTemplate.delete(RequestUtil.getUrl(params,request),params);
            RequestUtil.successLog(request,params,responseEntity);
        }catch (Exception e){
            responseEntity = ResponseUtils.getResponseFromException(e);
            RequestUtil.errorLog(request,params,responseEntity);
        }
        return responseEntity;
    }
}
