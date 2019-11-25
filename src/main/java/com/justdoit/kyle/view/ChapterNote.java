package com.justdoit.kyle.view;

import com.justdoit.kyle.entity.Chapter;
import com.justdoit.kyle.entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yangkaile
 * @date 2019-11-25 15:26:03
 */
public class ChapterNote extends Chapter {
    private List<Note> list = new ArrayList<>();


    public ChapterNote(Chapter chapter) {
        this.setId(chapter.getId());
        this.setNotesId(chapter.getNotesId());
        this.setName(chapter.getName());
        this.setSort(chapter.getSort());
        this.setCreateTime(chapter.getCreateTime());
    }

    public List<Note> getList() {
        return list;
    }

    public void setList(List<Note> list) {
        this.list = list;
    }
}
