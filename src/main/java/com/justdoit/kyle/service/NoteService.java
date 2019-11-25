package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.*;
import com.justdoit.kyle.mapper.*;
import com.justdoit.kyle.view.ChapterNote;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 笔记
 * @author yangkaile
 * @date 2019-10-17 14:48:08
 */
@Service
public class NoteService {
    @Resource
    private MyConfig myConfig;

    @Resource
    private NoteMapper mapper;

    @Resource
    private ChapterService chapterService;

    @Resource
    private NotesService notesService;

    /**
     * 保存笔记，有则更新，无则添加
     * @param note
     * @return
     */
    public ResultData save(Note note){
        //添加笔记时，修改笔记本的笔记数量
        if(note.getId() == 0){
            mapper.baseInsertAndReturnKey(note);
            notesService.addNoteNumber(note.getNotesId());
        }else{
            //修改笔记
            Note result = mapper.baseSelectById(note);
            if(result == null){
                return ResultData.error("没有找到指定笔记");
            }
            mapper.baseUpdateById(note);
        }
        return ResultData.success(note.getId());
    }

    /**
     *
     * @param notesId 笔记本id
     * @return List<ChapterNote>
     */
    public ResultData getByNotesId(int notesId){
        Note info = new Note();
        info.setNotesId(notesId);
        //获取课时列表，不获取课时明细（课时内容），因为课时内容字段可能较大，且在列表中不展示
        info.setBaseKyleDetailed(false);
        //获取课程列表
        List<Note> list = mapper.baseSelectByCondition(info);
        //获取章节列表
        List<Chapter> chapterList = chapterService.getListByNotesId(notesId);

        List<ChapterNote> result = new ArrayList<>();
        /**
         * 将笔记放入目录中
         */
        for(Chapter chapter :chapterList){
            ChapterNote chapterNote = new ChapterNote(chapter);
            for(Note note :list){
                if(note.getChapterId() == chapter.getId()){
                    chapterNote.getList().add(note);
                }
            }
            result.add(chapterNote);
        }
        if(result.size() > 0){
            return ResultData.success(result);
        }
        return ResultData.error(Languages.NO_TOPIC);
    }

    /**
     * 获取目录下的笔记
     * @param chapterId
     * @return
     */
    public List<Note> getByChapterId(int chapterId){
        Note info = new Note();
        info.setChapterId(chapterId);
        //获取笔记列表，不获取明细
        info.setBaseKyleDetailed(false);
        return mapper.baseSelectByCondition(info);
    }

    /**
     * 修改笔记所属目录或排序
     * @param note
     * @return
     */
    public ResultData updateChapterOrSort(Note note){
        Note result = mapper.baseSelectById(note);
        if(result == null){
            return ResultData.error(Languages.NO_TOPIC);
        }
        result.setChapterId(note.getChapterId());
        result.setSort(note.getSort());
        mapper.baseUpdateById(result);
        return ResultData.success();
    }

    public ResultData getById(int id){
        Note note = new Note(id);
        note = mapper.baseSelectById(note);
        if(note == null){
            return ResultData.error(Languages.NO_TOPIC);
        }
        return ResultData.success(note);
    }


    public ResultData delete(int id) {
        mapper.baseDeleteById(new Note(id));
        return ResultData.success();
    }
}
