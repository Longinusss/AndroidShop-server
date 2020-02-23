package com.lon.qingshe.controller;


import com.lon.qingshe.mapper.ServiceMapper;
import com.lon.qingshe.util.JsonR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceMapper serviceMapper  ;
    @RequestMapping("/showService")
    public JsonR showService(int kind ){
        try{
            if(kind<1||kind>5)
                throw new Exception("种类错误！");
            return JsonR.createSuccess(serviceMapper.findService(kind));

        }catch (Exception e){
            return JsonR.createFail(e.getMessage());
        }

    }











}
