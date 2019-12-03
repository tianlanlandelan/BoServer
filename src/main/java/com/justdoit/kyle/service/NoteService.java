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


    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CONTENT_MD = "contentMD";

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
     * 获取笔记列表
     * @param notesId 笔记本id
     * @return List<ChapterNote>
     */
    public ResultData getByNotesId(int notesId){
        Note info = new Note();
        info.setNotesId(notesId);
        //不获取笔记内容，因为内容字段可能较大，且在列表中不展示
        info.setBaseKyleDetailed(false);
        //获取笔记列表
        List<Note> list = mapper.baseSelectByCondition(info);
        //获取目录列表
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

    /**
     *
     * @param id
     * @param name
     * @param value
     * @return
     */
    public ResultData setField(int id,String name,String value){
        Note result = mapper.baseSelectById(new Note(id));
        if(result == null){
            return ResultData.error(Languages.NO_TOPIC);
        }
        if(TITLE.equals(name)){
            result.setTitle(value);
        }else if(CONTENT_MD.equals(name)){
            result.setContentMD(value);
        }else if(CONTENT.equals(name)){
            result.setContent(value);
        }else {
            return ResultData.error("参数异常");
        }
        mapper.baseUpdateById(result);
        return ResultData.success();
    }

    /**
     * 获取字段值，因为笔记内容可能比较大，添加单独的获取笔记内容的方法
     * @param id
     * @param name
     * @return
     */
    public ResultData getField(int id,String name){
        Note result = mapper.baseSelectById(new Note(id));
        if(result == null){
            return ResultData.error(Languages.NO_TOPIC);
        }
        if(CONTENT_MD.equals(name)){
            return ResultData.success(result.getContentMD());
        }
        if(CONTENT.equals(name)){
            return ResultData.success(result.getContent());
        }
        return ResultData.error("参数异常");
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
