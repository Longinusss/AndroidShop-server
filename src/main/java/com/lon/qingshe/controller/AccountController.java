package com.lon.qingshe.controller;

import com.lon.qingshe.mapper.UserMapper;
import com.lon.qingshe.pojo.User;
import com.lon.qingshe.util.Functions;
import com.lon.qingshe.util.JsonR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;

//@RestController注解，所以springmvc会将返回的JsonR对象自动转json返回给前端（底层默认是使用
// jsckson来实现数据格式转换的）
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonR login(String id, String password, HttpServletRequest httpServletRequest){
    String receipt;
        try{
            User user;
            if((user=userMapper.selectByID(id))==null)
                throw new Exception("账户不存在,请先注册");
            else{
                //判断密码
                if(user.getPassword().equals(password)){
                    //产生登陆凭证
                    Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                    String hash= Functions.md5(id+"qingshe"+timestamp,"its unbroken");
                    receipt=id+"|"+timestamp+"|"+hash;
                    //Base64 编码（加密）
                    Base64.Encoder encoder=Base64.getEncoder();
                    receipt=encoder.encodeToString(receipt.getBytes(StandardCharsets.UTF_8));
                    return JsonR.createSuccess(receipt);//返回登陆凭证
                }else
                    throw new Exception("密码错误");
            }

        }catch (Exception e){
             return JsonR.createFail(e.getMessage());//返回JsonR类型
        }
    }
    /**
     * override 2020.02.23
     * 由于@RestController自动转化json
     * 直接返回HashMap格式即可，不用使用自定义的JsonR类！
     */
//    public HashMap login(String id, String password, HttpServletRequest httpServletRequest){
//        String receipt;
//        HashMap res=new HashMap();
//        try{
//            User user;
//            if((user=userMapper.selectByID(id))==null)
//                throw new Exception("账户不存在,请先注册");
//            else{
//                //判断密码
//                if(user.getPassword().equals(password)){
//                    //产生登陆凭证
//                    Timestamp timestamp=new Timestamp(System.currentTimeMillis());
//                    String hash= Functions.md5(id+"qingshe"+timestamp,"its unbroken");
//                    receipt=id+"|"+timestamp+"|"+hash;
//                    //Base64 编码（加密）
//                    Base64.Encoder encoder=Base64.getEncoder();
//                    receipt=encoder.encodeToString(receipt.getBytes(StandardCharsets.UTF_8));
//                    res.put("errorCode",0);
//                    res.put("errorMessage",null);
//                    res.put("data",receipt);
//                    return res;//返回登陆凭证
//                }else
//                    throw new Exception("密码错误");
//            }
//
//        }catch (Exception e){
//            res.put("errorCode",0);
//            res.put("errorMessage",e.getMessage());
//            res.put("data",null);
//            return res;//返回JsonR类型
//        }
//    }
    @RequestMapping(value = "/regist",method =RequestMethod.POST)
    public JsonR regist(String id,String password,String nickname,String yzm){
        User user;
        try{
            if((user=userMapper.selectByID(id))!=null)
                throw new Exception("账号已存在");
            else if(yzm!=null)
            {
                userMapper.addUser(id,password,nickname);
                return JsonR.createSuccess();
            }
            else throw new Exception("验证码为空");
        } catch (Exception e) {
           return JsonR.createFail(e.getMessage());
        }
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public JsonR logout(String id, HttpServletRequest httpServletRequest){
        String header;
        try{
            if (userMapper.selectByID(id)==null)
                throw new Exception("无此账号！");
            header= (String) httpServletRequest.getHeader("receipt");//获取登陆凭证
            if(header!=null &&header.length()!=0)  //注意判定条件缺一不可
                return JsonR.createSuccess();
            else
                throw new Exception("未登录！");
        }catch (Exception e){
            return JsonR.createFail(e.getMessage());
        }
    }








}
