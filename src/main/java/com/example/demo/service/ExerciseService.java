package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.entity.TopicInfo;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.TopicInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-10-17 16:43:54
 */
@Service
public class ExerciseService {
    @Resource
    private ExerciseMapper mapper;

    @Resource
    private TopicInfoMapper topicInfoMapper;

    @Resource
    private MyConfig myConfig;

    public ResultData save(ExerciseInfo exerciseInfo){
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setId(exerciseInfo.getTopicId());
        topicInfo = topicInfoMapper.baseSelectById(topicInfo);
        if(topicInfo == null){
            return ResultData.error("Topic NotExist");
        }
        mapper.baseInsertAndReturnKey(exerciseInfo);
        return ResultData.success(exerciseInfo.getId());
    }

    public ResultData getAll(){
        List<ExerciseInfo> list = mapper.baseSelectAll(new ExerciseInfo());
        if(list == null || list.size() < 1){
            return ResultData.error("No Excise");
        }
        return ResultData.success(list);
    }

    public ResultData getById(int id){
        ExerciseInfo exercise = new ExerciseInfo();
        exercise.setId(id);
        exercise = mapper.baseSelectById(exercise);
        if(exercise == null){
            return ResultData.error("No Excise");
        }
        if(StringUtils.isNotEmpty(exercise.getImg())){
            exercise.setImg(myConfig.NGINX_PREFIX + exercise.getImg());
        }
        return ResultData.success(exercise);
    }
}
