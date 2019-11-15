package com.justdoit.kyle.view;

import com.justdoit.kyle.entity.ChapterInfo;
import com.justdoit.kyle.entity.TopicInfo;

import java.util.ArrayList;
import java.util.List;

public class ChapterTopic extends ChapterInfo {
    private List<TopicInfo> list = new ArrayList<>();


    public ChapterTopic(ChapterInfo chapter) {
        this.setId(chapter.getId());
        this.setCourseId(chapter.getCourseId());
        this.setName(chapter.getName());
        this.setSort(chapter.getSort());
        this.setCreateTime(chapter.getCreateTime());
    }

    public List<TopicInfo> getList() {
        return list;
    }

    public void setList(List<TopicInfo> list) {
        this.list = list;
    }
}
