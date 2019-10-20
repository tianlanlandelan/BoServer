package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.UserScores;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserScoresMapper extends BaseMapper<UserScores> {
}
