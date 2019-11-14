package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.CourseInfo;
import com.justdoit.kyle.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程相关业务逻辑
 * @author yangkaile
 * @date 2019-11-13 11:27:55
 */
@Service
public class CourseService {
    @Resource
    private CourseMapper mapper;

    /**
     * 保存课程，如果有课程id，则更新，否则新增
     * @param courseInfo
     * @return
     */
    public ResultData save(CourseInfo courseInfo){
        if(courseInfo.getId() <= 0){
            courseInfo.setId(0);
            mapper.baseInsertAndReturnKey(courseInfo);
        }else{
            CourseInfo result = mapper.baseSelectById(courseInfo);
            if(result == null){
                return ResultData.error("没有找到指定课程");
            }
            mapper.baseUpdateById(courseInfo);
        }

         return ResultData.success(courseInfo.getId());
    }
    public ResultData getAll(){
        List<CourseInfo> list = mapper.baseSelectAll(new CourseInfo());
        return ResultData.success(list);
    }
    public ResultData getById(int id){
        CourseInfo courseInfo = mapper.baseSelectById(new CourseInfo(id));
        if(courseInfo == null){
            return ResultData.error("没有找到指定课程");
        }else {
            return ResultData.success(courseInfo);
        }
    }

}
