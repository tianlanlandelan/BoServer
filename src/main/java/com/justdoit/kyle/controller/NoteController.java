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
 * 笔记
 * 核心问题：笔记内容可能很大（几千字、几万字都有可能）
 * 内容过大会导致的问题：
 *  1. 提交post请求时报异常：java.lang.IllegalArgumentException: Request header is too large
 *      解决方案：需要在application.properties中配置server.max-http-header-size = 10240000
 *  2. 保存到mySQL数据库时报字段超长，因为TEXT类型的字段最大能保存65535 bytes，约64kb，
 *      解决方案：在保存长文章时最好用MEDIUMTEXT类型，最大长度16,777,215 bytes，约16MB
 *  3. 获取笔记列表时，特别慢，耗流量
 *      解决方案：获取笔记列表时，只获取概要，如：标题、创建时间等
 *  4. 添加、保存笔记时慢
 *      解决方案：将添加和保存分开做，添加笔记时，只创建一个包含标题空笔记。
 *              保存笔记时，每一次只保存一个字段，如：标题、内容、MD内容
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
//    @PostMapping
//    public ResponseEntity save(Integer id,Integer userId,Integer notesId,Integer chapterId,String title,
//                                    String contentMD,String content){
//        if(StringUtils.isEmpty(title) || RequestUtil.notValidInteger(userId,notesId,chapterId)){
//            return MyResponse.badRequest();
//        }
//
//        Note note = new Note(userId,notesId,chapterId,title);
//        note.setId(id);
//        note.setContentMD(contentMD);
//        note.setContent(content);
//        return MyResponse.ok(service.save(note));
//    }

    /**
     * 添加笔记
     * @param userId
     * @param notesId
     * @param chapterId
     * @param title
     * @return
     */
    @PostMapping
    public ResponseEntity add(Integer userId,Integer notesId,Integer chapterId,String title){
        if(StringUtils.isEmpty(title) || RequestUtil.notValidInteger(userId,notesId,chapterId)){
            return MyResponse.badRequest();
        }

        Note note = new Note(userId,notesId,chapterId,title);
        return MyResponse.ok(service.save(note));
    }

    /**
     * 保存笔记，可分别保存 标题、内容、MD内容
     * @param id
     * @param name
     * @param value
     * @return
     */
    @PutMapping("/save")
    public ResponseEntity save(Integer id,String name,String value){
        if(RequestUtil.notValidInteger(id) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.setField(id,name,value));
    }

    /**
     * 获取笔记内容，可分别获取内容、MD内容
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getContent")
    public ResponseEntity getContent(Integer id,String name){
        if(RequestUtil.notValidInteger(id) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getField(id,name));
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
