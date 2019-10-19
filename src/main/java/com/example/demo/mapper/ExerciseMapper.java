package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.ExerciseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseMapper extends BaseMapper<ExerciseInfo> {

    String exercise = "exercise_info";
    @Select("select id,topicId,sort,title from " + exercise)
    List<ExerciseInfo> selectAll();
}
