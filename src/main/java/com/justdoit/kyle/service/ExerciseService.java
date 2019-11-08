package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.ExerciseInfo;
import com.justdoit.kyle.entity.TopicInfo;
import com.justdoit.kyle.mapper.ExerciseMapper;
import com.justdoit.kyle.mapper.TopicInfoMapper;
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


    public ResultData getAll(){
        List<ExerciseInfo> list = mapper.baseSelectAll(new ExerciseInfo());
        if(list == null || list.size() < 1){
            return ResultData.error(Languages.NO_EXERCISE);
        }
        return ResultData.success(list);
    }

    public ResultData getById(int id){
        ExerciseInfo exercise = new ExerciseInfo();
        exercise.setId(id);
        exercise = mapper.baseSelectById(exercise);
        if(exercise == null){
            return ResultData.error(Languages.NO_EXERCISE);
        }
        if(StringUtils.isNotEmpty(exercise.getImg())){
            exercise.setImg(myConfig.NGINX_PREFIX + exercise.getImg());
        }
        return ResultData.success(exercise);
    }
}
