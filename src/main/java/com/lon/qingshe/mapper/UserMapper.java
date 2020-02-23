package com.lon.qingshe.mapper;

import com.lon.qingshe.dto.CollectionList;
import com.lon.qingshe.dto.Coupon;
import com.lon.qingshe.dto.Goods;
import com.lon.qingshe.dto.Orderlist;

import com.lon.qingshe.pojo.User;
import com.lon.qingshe.util.JsonR;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserMapper {
    @Select("SELECT * FROM user WHERE id=#{id} limit 1")
    User selectByID(@Param("id") String id);

    @Insert("insert into user values(#{id},#{password},#{nickname},0,'upload/1.jpg')")
    void addUser(@Param("id") String id, @Param("password")
            String password, @Param("nickname") String nickname);

    @Select("select * from orderlist where consumer_id=#{id} and kind=0")
    List<Orderlist> getProviderID(@Param("id") String id);

    @Select("select * from orderlist where consumer_id=#{id} and kind=1")
    List<Orderlist> getProviderID1(@Param("id") String id);

    //注解形式时，script标签来实现动态sql（循环查询）    （与xml中类似）（动态sql以后尽量用xml）
    @Select({"<script>",
            "select * from second_good ",
            "where id in ",
            "<foreach collection='ids' separator = ',' open = '(' close = ')' item = 'id'> ",
            "#{id} ",
            "</foreach>",
            "</script>"})
    List<Goods> getGoods(@Param("ids") List<Integer> ids);

    @Select({"<script>",
            "select * from service ",
            "where id in ",
            "<foreach collection='ids' separator = ',' open = '(' close = ')' item = 'id'> ",
            "#{id} ",
            "</foreach>",
            "</script>"})
    List<Goods> getService(@Param("ids") List<Integer> ids);

    @Select("select * from collection where user_id=#{id} and kind=0")
    List<CollectionList> getCollectionID(@Param("id") String id);

    @Select("select *from coupon where user_id=#{id}")
    List<Coupon> getCoupon(@Param("id") String id);

    @Select("update user set password=#{password},icon=#{iconUrl}," +
            "nickname=#{nickname} where id=#{id}")
    void updateUser(@Param("id") String id, @Param("password") String password,
                    @Param("iconUrl") String iconUrl, @Param("nickname") String nickname);

}

