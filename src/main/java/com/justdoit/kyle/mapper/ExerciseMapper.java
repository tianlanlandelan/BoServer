package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.ExerciseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseMapper extends BaseMapper<ExerciseInfo> {

    String exercise = "exercise_info";
    String score = "score_info";
    @Select("select id,topicId,sort,title from " + exercise)
    List<ExerciseInfo> selectAll();







}
