package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.Rate;
import com.example.demo.view.UserScores;
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
    /**
     * 更新timer，每秒保存用户答题时间倒计时，可能把值改为null
     * @param rate
     * @return
     */
    @Update("update rate set timer=#{timer} where id =#{id}")
    Integer updateTimer(Rate rate);

    /**
     * 查询比自己高的得分，不区分用户类型
     * select b.firstName,b.lastName,b.avatarId,a.score
     * 	from rate a,user_info b
     * where a.id = b.id
     * order by a.score desc
     * limit 0,10;
     * @param rate
     * @return
     */
    @Select("SELECT a.id, b.firstName,b.lastName,b.avatarId,a.score " +
            "FROM rate a,user_info b " +
            "WHERE a.id = b.id AND a.score > #{score} " +
            "ORDER BY a.score DESC " +
            "LIMIT #{baseKyleStartRows},#{baseKylePageSize}")
    List<UserScores> selectUp(Rate rate);

    @Select("SELECT a.id, b.firstName,b.lastName,b.avatarId,a.score " +
            "FROM rate a,user_info b " +
            "WHERE a.id = b.id AND a.score < #{score} " +
            "ORDER BY a.score DESC " +
            "LIMIT #{baseKyleStartRows},#{baseKylePageSize}")
    List<UserScores> selectDown(Rate rate);


    /**
     * 查询自己的排名，不区分用户类型
     * select count(1)
     * from rate a,user_info b
     * where a.id = b.id and a.score > 288
     * order by a.score desc;
     * @param rate
     * @return
     */
    @Select("SELECT COUNT(1) " +
            "FROM rate a,user_info b " +
            "WHERE a.id = b.id AND b.type=1 AND a.score >#{score}")
    Integer selectSort(Rate rate);

}
