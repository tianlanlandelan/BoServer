package com.example.demo.controller;
import com.example.demo.common.PublicConfig;
import com.example.demo.common.RequestConfig;
import com.example.demo.common.response.MyResponse;
import com.example.demo.common.util.Console;
import com.example.demo.common.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
        readRequest(params);
        try {
            return restTemplate.getForEntity(getUrl(params), String.class,params);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }

    /**
     * 表单形式的GET请求
     * @return
     */
    @GetMapping("/form")
    public ResponseEntity get(){
        readRequest();
        Map<String,String> map = getParam();
        try {
            String url = getUrl(map);
            Console.println("form GET",url);
            if(map == null){
                return restTemplate.getForEntity(url, String.class);
            }
            return restTemplate.getForEntity(url, String.class,map);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }

    /**
     * JSON  形式的 POST 请求
     * @param params
     * @return
     */
    @PostMapping
    public ResponseEntity post(@RequestBody Map<String,String> params){
        Console.println("post Request");
        readRequest(params);
        try {
            ResponseEntity response =
                    restTemplate.postForEntity(getUrl(params),null,String.class,params);
            Console.println("post Response",response);
            return response;
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }

    /**
     * 表单形式的 POST 请求
     * @return
     */
    @PostMapping("/form")
    public ResponseEntity post(){
        readRequest();
        Map<String,String> map = getParam();
        try {
            return restTemplate.postForEntity(getUrl(map),null,String.class,map);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
    }

    /**
     * JSON 格式的 PUT 请求
     * @param params
     * @return
     */
    @PutMapping
    public ResponseEntity put(@RequestBody Map<String,String> params){
        readRequest(params);
        try {
            restTemplate.put(getUrl(params),null,params);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return MyResponse.ok();
    }

    /**
     * 表单形式的 PUT  请求
     * @return
     */
    @PutMapping("/form")
    public ResponseEntity put(){
        readRequest();
        Map<String,String> params = getParam();
        try {
            restTemplate.put(getUrl(params),null,params);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return MyResponse.ok();
    }

    /**
     * JSON 形式的 DELETE 请求
     * @param params
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(@RequestBody Map<String,String> params){
        readRequest(params);
        try {
            restTemplate.delete(getUrl(params),params);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return  MyResponse.ok();
    }


    /**
     * 表单形式的 DELETE 请求
     * @return
     */
    @DeleteMapping("/form")
    public ResponseEntity delete(){
        readRequest();
        Map<String,String> map = getParam();
        try {
            restTemplate.delete(getUrl(map),map);
        }catch (HttpClientErrorException e){
            return ResponseUtils.getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return  MyResponse.ok();
    }

    public void readRequest(Map<String,String> params){
        Console.print("header",getHeader());
        Console.print("param",params);
    }
    public void readRequest(){
        Console.print("header",getHeader());
        Console.print("param",getParam());
    }

    public HashMap<String,String> getHeader(){
        HashMap<String,String> headerMap = new HashMap<>(16);
        //请求的URL地址
        headerMap.put(RequestConfig.URL,request.getRequestURL().toString());
        //请求的资源
        headerMap.put(RequestConfig.URI,request.getRequestURI());
        //请求方式 GET/POST
        headerMap.put(RequestConfig.REQUEST_METHOD,request.getMethod());

        //来访者的IP地址
        headerMap.put(RequestConfig.REMOTE_ADDR,request.getRemoteAddr());
        //来访者的HOST
        headerMap.put(RequestConfig.REMOTE_HOST,request.getRemoteHost());
        //来访者的端口
        headerMap.put(RequestConfig.REMOTE_PORT,request.getRemotePort() + "");
        //来访者的用户名
        headerMap.put(RequestConfig.REMOTE_USER,request.getRemoteUser());


        //自定义的Header （接口名）
        headerMap.put(RequestConfig.METHOD,request.getHeader(RequestConfig.METHOD));
        //自定义的Header （TOKEN）
        headerMap.put(RequestConfig.TOKEN,request.getHeader(RequestConfig.TOKEN));
        return headerMap;
    }

    public Map<String,String> getParam(){
        Map<String,String> paramMap = new HashMap<>(16);
        //request对象封装的参数是以Map的形式存储的
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry :map.entrySet()){
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for (int i = 0; paramValueArr!=null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length-1) {
                    paramValue += paramValueArr[i];
                }else {
                    paramValue += paramValueArr[i]+",";
                }
            }
            paramMap.put(paramName,paramValue);
        }
        if(paramMap.size() == 0){
            return null;
        }
        return paramMap;
    }
    private String getUrl(Map<String,String> params){
        HashMap<String,String> headers = getHeader();
        StringBuilder builder = new StringBuilder();

        builder.append(PublicConfig.SERVICE_URL)
                .append("/")
                .append(headers.get(RequestConfig.METHOD));
        if(params == null){
            return builder.toString();
        }
        builder.append("?");
        for(String key :params.keySet()){
            builder.append(key)
                    .append("={")
                    .append(key)
                    .append("}&");
        }
        Console.info(builder.toString());
        return builder.toString();
    }
}
