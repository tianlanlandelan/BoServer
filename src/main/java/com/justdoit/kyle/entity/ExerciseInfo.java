package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

/**
 * @author yangkaile
 * @date 2019-10-20 14:00:10
 * 练习题
 */
@TableAttribute(name = "exercise_info", comment = "练习题(单选题)")
public class ExerciseInfo extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute
    @IndexAttribute
    private Integer topicId;

    @FieldAttribute
    @SortAttribute
    private Integer sort;

    @FieldAttribute(value = "标题",length = 200)
    private String title;

    @FieldAttribute(value = "内容",length = 5000)
    private String content;

    @FieldAttribute(value = "图片地址",length = 200)
    private String img;


    @FieldAttribute(value = "A选项",length = 1000)
    private String optionA;

    @FieldAttribute(value = "B选项",length = 1000)
    private String optionB;

    @FieldAttribute(value = "C选项",length = 1000)
    private String optionC;

    @FieldAttribute(value = "D选项",length = 1000)
    private String optionD;

    @FieldAttribute(value = "答案",length = 10)
    private String answer;

    private int status;
    private Integer timer;

    public ExerciseInfo() {
    }

    public ExerciseInfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "ExerciseInfo{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", timer=" + timer +
                '}';
    }
}
