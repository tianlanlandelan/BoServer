package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.ExerciseInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExerciseMapper extends BaseMapper<ExerciseInfo> {
}
