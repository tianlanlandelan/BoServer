package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.Rate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RateMapper extends BaseMapper<Rate> {
    /**
     * 更新timer，每秒保存用户答题时间倒计时，可能把值改为null
     * @param rate
     * @return
     */
    @Update("update rate set timer=#{timer} where id =#{id}")
    Integer updateTimer(Rate rate);

}
