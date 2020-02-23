package com.lon.qingshe.mapper;

import com.lon.qingshe.dto.CollectionList;
import com.lon.qingshe.dto.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.JDBCType;
import java.util.List;

public interface GoodsMapper {
    @Select("select * from second_good ")
    List<Goods> findAllGoods();
    //mybatis映射注意模糊查询的特别方式
    @Select("select * from second_good where name like CONCAT('%',#{name,jdbcType=VARCHAR},'%')")
    List<Goods> search(@Param("name") String name);

    @Select("insert into collection values(#{user_id},#{goods_id},0)")
    void collect(@Param("user_id")String user_id,@Param("goods_id")int goods_id);

    @Select("select * from collection where user_id=#{user_id} and collect_id=#{goods_id} and kind=0 ")
    List<CollectionList> ifCollect(@Param("user_id")String user_id,@Param("goods_id")int goods_id);
}
