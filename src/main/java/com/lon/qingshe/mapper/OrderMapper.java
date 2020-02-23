package com.lon.qingshe.mapper;

import com.lon.qingshe.util.JsonR;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface OrderMapper {

    @Insert("insert into orderlist(consumer_id,provider_id,state,kind) " +
            "values(#{consumer_id},#{provider_id},#{state},#{kind})")
    void buy1(@Param("consumer_id") String consumer_id,@Param("provider_id")int provider_id,
                  @Param("state") int state, @Param("kind") int kind);
    @Update("update user set points=points+1 where id=#{consumer_id}")
    void buy2(@Param("consumer_id") String consumer_id,@Param("provider_id")int provider_id,
              @Param("state") int state, @Param("kind") int kind);

}
