package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.Rate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author yangkaile
 * @date 2019-10-23 10:44:05
 */
@Mapper
public interface RateMapper extends BaseMapper<Rate> {


}
