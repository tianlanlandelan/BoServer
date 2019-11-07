package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.UserExercise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserExerciseMapper extends BaseMapper<UserExercise> {

    /**
     * select userId,group_concat(exerciseId,',',answer,',',score,',',time separator ';')
     * from user_exercise,user_info
     * where user_info.id = user_exercise.userId and user_info.type = 1
     * group by userId;
     * @return
     */
    @Select("select userId,exerciseId,score,time " +
            "from user_exercise,user_info " +
            "where user_info.id = user_exercise.userId and user_info.type = #{type}")
    List<UserExercise> selectByUserType(int type);
}
