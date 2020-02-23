package com.lon.qingshe.mapper;

import com.lon.qingshe.pojo.Service;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ServiceMapper {
@Select("select *from service where kind=#{kind}")
    List<Service> findService(@Param("kind") int kind);
}
