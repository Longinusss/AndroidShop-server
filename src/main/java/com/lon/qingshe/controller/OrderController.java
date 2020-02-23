package com.lon.qingshe.controller;

import com.lon.qingshe.mapper.OrderMapper;
import com.lon.qingshe.util.JsonR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController

public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @RequestMapping("/buy")
    public JsonR buy(String id, int goods_id, int kind, HttpServletRequest request){
        String header;
        try{
            header=request.getHeader("receipt");
            if(header==null || header.length()==0)
                throw new Exception("请先登录！");
            if(kind==0){
               orderMapper.buy1(id,goods_id,0,0);
               orderMapper.buy2(id,goods_id,0,0);
               return JsonR.createSuccess();
            }
            else if(kind==1){
                orderMapper.buy1(id,goods_id,0,1);
                orderMapper.buy2(id,goods_id,0,1);
                return JsonR.createSuccess();
            }
            throw new Exception("kind值出错！");

        }catch (Exception e){
            return JsonR.createFail(e.getMessage());
        }
    }






}
