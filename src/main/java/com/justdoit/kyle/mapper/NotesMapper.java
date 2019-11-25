package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.Notes;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程的持久层
 * @author yangkaile
 * @date 2019-11-07 09:30:17
 */
@Mapper
public interface NotesMapper extends BaseMapper<Notes> {
}
