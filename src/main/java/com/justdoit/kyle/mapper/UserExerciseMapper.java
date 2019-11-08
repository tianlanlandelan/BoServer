package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.UserExercise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserExerciseMapper extends BaseMapper<UserExercise> {

}
