package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.Note;
import com.justdoit.kyle.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-17 14:49:21
 */
@RestController
@RequestMapping("/note")
public class NoteController {
    @Resource
    private NoteService service;

    /**
     * 保存笔记
     * @param id id
     * @param userId 用户ID
     * @param notesId 笔记本ID
     * @param chapterId 目录ID
     * @param title 标题
     * @param contentMD MD内容
     * @param content 内容
     * @return
     */
    @PostMapping
    public ResponseEntity save(Integer id,Integer userId,Integer notesId,Integer chapterId,String title,
                                    String contentMD,String content){
        if(StringUtils.isEmpty(title) || RequestUtil.notValidInteger(userId,notesId,chapterId)){
            return MyResponse.badRequest();
        }

        Note note = new Note(userId,notesId,chapterId,title);
        note.setId(id);
        note.setContentMD(contentMD);
        note.setContent(content);
        return MyResponse.ok(service.save(note));
    }

    /**
     * 获取笔记列表
     * @param notesId
     * @return
     */
    @GetMapping("/getList")
    public ResponseEntity getByNotesId(Integer notesId){
        if(RequestUtil.notValidInteger(notesId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getByNotesId(notesId));
    }
    /**
     * 修改笔记所属目录或排序
     * @param id id
     * @param chapterId 目录id
     * @param sort 排序
     * @return
     */
    @PutMapping
    public ResponseEntity update(Integer id,Integer chapterId,Integer sort){
        if(RequestUtil.notValidInteger(id,chapterId,sort)){
            return MyResponse.badRequest();
        }
        Note note = new Note(id);
        note.setChapterId(chapterId);
        note.setSort(sort);
        return MyResponse.ok(service.updateChapterOrSort(note));

    }

    @GetMapping
    public ResponseEntity getById(Integer id){
        if(id == null || id < 0){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getById(id));
    }
    @DeleteMapping
    public ResponseEntity delete(Integer id){
        if(id == null || id < 0){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.delete(id));
    }
}
