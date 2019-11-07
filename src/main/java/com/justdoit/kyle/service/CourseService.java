package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.CourseInfo;
import com.justdoit.kyle.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程相关业务逻辑
 */
@Service
public class CourseService {
    @Resource
    private CourseMapper mapper;

    public ResultData save(CourseInfo courseInfo){
         mapper.baseInsertAndReturnKey(courseInfo);
         return ResultData.success(courseInfo.getId());
    }
    public ResultData getAll(){
        List<CourseInfo> list = mapper.baseSelectAll(new CourseInfo());
        return ResultData.success(list);
    }

}
