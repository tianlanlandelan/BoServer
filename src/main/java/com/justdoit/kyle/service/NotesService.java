package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.Chapter;
import com.justdoit.kyle.entity.Notes;
import com.justdoit.kyle.mapper.ChapterMapper;
import com.justdoit.kyle.mapper.NotesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程相关业务逻辑
 * @author yangkaile
 * @date 2019-11-13 11:27:55
 */
@Service
public class NotesService {
    @Resource
    private NotesMapper mapper;

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 保存课程，如果有课程id，则更新，否则新增
     * @param notes
     * @return
     */
    public ResultData save(Notes notes){
        if(notes.getId() <= 0){
            insert(notes);
        }else{
            Notes result = mapper.baseSelectById(notes);
            if(result == null){
                return ResultData.error("没有找到指定笔记本");
            }
            mapper.baseUpdateById(notes);
        }

         return ResultData.success(notes.getId());
    }

    private Notes insert(Notes notes){
        mapper.baseInsertAndReturnKey(notes);
        Chapter chapter = new Chapter(notes.getId(),"默认目录");
        chapterMapper.baseInsertAndReturnKey(chapter);
        return notes;
    }


    /**
     * 获取用户的笔记本，如果没有，新建一个笔记本
     * @param userId
     * @return
     */
    public ResultData getListByUserId(int userId){
        Notes notes = new Notes();
        notes.setUserId(userId);
        List<Notes> list = mapper.baseSelectByCondition(notes);
        //新建笔记本
        if(list == null || list.size() < 1){
            notes = new Notes(userId,"默认笔记本");
            notes.setSubTitle("系统默认的笔记本");
            notes = insert(notes);
            list.add(notes);
        }
        return ResultData.success(list);
    }
    public ResultData getById(int id){
        Notes notes = mapper.baseSelectById(new Notes(id));
        if(notes == null){
            return ResultData.error("没有找到指定课程");
        }else {
            return ResultData.success(notes);
        }
    }

    /**
     * 修改笔记本中的笔记数量
     * @param id
     */
    public void addNoteNumber(int id){
        Notes result = mapper.baseSelectById(new Notes(id));
        if(result == null){
            return;
        }
        result.setNoteNumber(result.getNoteNumber() + 1);
        mapper.baseUpdateById(result);
    }

}
