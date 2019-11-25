package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.Notes;
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



    /**
     * 保存课程，如果有课程id，则更新，否则新增
     * @param notes
     * @return
     */
    public ResultData save(Notes notes){
        if(notes.getId() <= 0){
            notes.setId(0);
            mapper.baseInsertAndReturnKey(notes);
        }else{
            Notes result = mapper.baseSelectById(notes);
            if(result == null){
                return ResultData.error("没有找到指定课程");
            }
            mapper.baseUpdateById(notes);
        }

         return ResultData.success(notes.getId());
    }
    public ResultData getAll(){
        Notes notes = new Notes();
        notes.setBaseKyleDetailed(false);
        List<Notes> list = mapper.baseSelectAll(notes);
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
