package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.VideoInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangkaile
 * @date 2019-10-02 07:36:35
 */
@Mapper
public interface VideoMapper extends BaseMapper<VideoInfo> {

}
