package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.EmailLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailLogMapper extends BaseMapper<EmailLog> {
}
