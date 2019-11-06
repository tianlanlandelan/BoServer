package com.example.demo.common.util;

import com.example.demo.common.response.MyResponse;
import com.example.demo.common.response.ResultData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author yangkaile
 * @date 2019-10-28 14:16:07
 */
public class ResponseUtils {
    /**
     * 根据组件返回的错误码重组应答报文
     * @param exception
     * @return
     */
    public static ResponseEntity getResponseFromException(Exception exception){
        ResponseEntity response;
        if(exception instanceof HttpClientErrorException){
            HttpClientErrorException errorException = (HttpClientErrorException) exception;
            switch (errorException.getStatusCode()){
                case FORBIDDEN:  response = MyResponse.forbidden(); break;
                case BAD_REQUEST: response = MyResponse.badRequest();break;
                case UNAUTHORIZED: response = MyResponse.unauthorized();break;
                case INTERNAL_SERVER_ERROR: response = MyResponse.error();break;
                default:{
                    ResultData resultData = ResultData.error("ERROR");
                    response = ResponseEntity.status(errorException.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(resultData);
                }
            }

        }else {
            response = MyResponse.badRequest();
        }
        return  response;
    }

    public static ResultData getResultDataFromException(HttpClientErrorException exception){
        return  (ResultData)getResponseFromException(exception).getBody();
    }
}
