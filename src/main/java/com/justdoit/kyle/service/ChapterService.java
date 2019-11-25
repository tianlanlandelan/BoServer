package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.Chapter;
import com.justdoit.kyle.entity.Note;
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

    @Resource
    private NoteService noteService;

    /**
     * 保存章节，有则更新，无则添加
     * @param info
     * @return
     */
    public ResultData save(Chapter info){
        if(info.getId() == 0){
            mapper.baseInsertAndReturnKey(info);
        }else{
            Chapter result = mapper.baseSelectById(info);
            if(result == null){
                return ResultData.error("没有找到指定章节");
            }
            mapper.baseUpdateById(info);
        }
        return ResultData.success(info.getId());

    }
    /**
     * 修改章节
     * @param chapter
     * @return
     */
    public ResultData update(Chapter chapter){
        Chapter result = mapper.baseSelectById(chapter);
        if(result == null){
            return ResultData.error("没有找到指定章节");
        }
        result.setSort(chapter.getSort());
        result.setName(chapter.getName());
        mapper.baseUpdateById(result);
        return ResultData.success();

    }

    /**
     * 获取笔记本的章节
     * @param notesId
     * @return
     */
    public ResultData getByNotesId(int notesId){
        List<Chapter> list = getListByNotesId(notesId);
        if(list == null || list.size() == 0){
            return ResultData.error("没有章节");
        }
        return ResultData.success(list);

    }

    /**
     * 删除目录，如果目录下面有笔记，删除失败
     * @param id
     * @return
     */
    public ResultData deleteById(int id){
        Chapter info = getById(id);
        if(info == null){
            return ResultData.success();
        }
        List<Note> list = noteService.getByChapterId(id);
        if(list != null && list.size() > 0){
            return ResultData.error("目录下面有笔记，不能删除");
        }
        mapper.baseDeleteById(info);
        return ResultData.success();
    }
    public Chapter getById(int id){
        return mapper.baseSelectById(new Chapter(id));
    }

    public List<Chapter> getListByNotesId(int notesId){
        Chapter info = new Chapter();
        info.setNotesId(notesId);
        return mapper.baseSelectByCondition(info);
    }
}
