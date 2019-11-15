package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.ChapterInfo;
import com.justdoit.kyle.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 章节管理
 * @author yangkaile
 * @date 2019-11-14 09:35:39
 */
@Service
public class ChapterService {
    @Resource
    private ChapterMapper mapper;

    /**
     * 保存章节，有则更新，无则添加
     * @param info
     * @return
     */
    public ResultData save(ChapterInfo info){
        if(info.getId() == 0){
            mapper.baseInsertAndReturnKey(info);
        }else{
            ChapterInfo result = mapper.baseSelectById(info);
            if(result == null){
                return ResultData.error("没有找到指定章节");
            }
            mapper.baseUpdateById(info);
        }
        return ResultData.success(info.getId());

    }
    /**
     * 修改章节
     * @param chapterInfo
     * @return
     */
    public ResultData update(ChapterInfo chapterInfo){
        ChapterInfo result = mapper.baseSelectById(chapterInfo);
        if(result == null){
            return ResultData.error("没有找到指定章节");
        }
        result.setSort(chapterInfo.getSort());
        result.setName(chapterInfo.getName());
        mapper.baseUpdateById(result);
        return ResultData.success();

    }
    public ResultData getByCourseId(int courseId){
        List<ChapterInfo> list = getListByCourseId(courseId);
        if(list == null || list.size() == 0){
            return ResultData.error("没有章节");
        }
        return ResultData.success(list);

    }

    public List<ChapterInfo> getListByCourseId(int courseId){
        ChapterInfo info = new ChapterInfo();
        info.setCourseId(courseId);
        return mapper.baseSelectByCondition(info);
    }
}
