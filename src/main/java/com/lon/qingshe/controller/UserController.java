package com.lon.qingshe.controller;

import com.lon.qingshe.dto.CollectionList;
import com.lon.qingshe.dto.Coupon;
import com.lon.qingshe.dto.Orderlist;
import com.lon.qingshe.mapper.UserMapper;
import com.lon.qingshe.util.JsonR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/showUser")
    public JsonR showUser(String id, HttpServletRequest request) {
        try {
            String header = request.getHeader("receipt");
            if (header == null || header.length() == 0)
                return JsonR.createFail("未登录!");
            return JsonR.createSuccess(userMapper.selectByID(id));
        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }
    }

    @RequestMapping("/goodsOrder")
    public JsonR getGoodsOrder(String id, HttpServletRequest request) {
        try {
            List<Orderlist> orderlist;
            List<Integer> ids = new ArrayList<>(); //注意实体化为动态list
            String header = request.getHeader("receipt");
            if (header == null || header.length() == 0)
                return JsonR.createFail("未登录!");
            orderlist = userMapper.getProviderID(id);
            if(orderlist.size()==0)
                throw new Exception("无闲置订单！");
            for (Orderlist o : orderlist) {
                ids.add(o.getProvider_id()); //赋值
            }

            return JsonR.createSuccess(userMapper.getGoods(ids));

        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }
    }

    @RequestMapping("/serviceOrder")
    public JsonR getServiceOrder(String id, HttpServletRequest request) {
        try {
            List<Orderlist> orderlist;
            List<Integer> ids = new ArrayList<>(); //注意实体化为动态list
            String header = request.getHeader("receipt");
            if (header == null || header.length() == 0)
                return JsonR.createFail("未登录!");
            orderlist = userMapper.getProviderID1(id);
            if(orderlist.size()==0)
                throw new Exception("无养护订单！");
            for (Orderlist o : orderlist) {
                ids.add(o.getProvider_id()); //赋值
            }
            return JsonR.createSuccess(userMapper.getService(ids));

        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }

    }

    @RequestMapping("/collection")
    public JsonR getCollection(String id, HttpServletRequest request) {
        try {
            List<CollectionList> colist = new ArrayList<>();
            List<Integer> ids = new ArrayList<>(); //注意实体化为动态list
            String header = request.getHeader("receipt");
            if (header == null || header.length() == 0)
                return JsonR.createFail("未登录!");

            if ((colist = userMapper.getCollectionID(id)) == null)
                throw new Exception("无结果！");
            for (CollectionList collectionList : colist) {
                ids.add(collectionList.getCollect_id());
            }
            return JsonR.createSuccess(userMapper.getGoods(ids));

        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }
    }

    @RequestMapping("/mycoupon")
    public JsonR getCoupon(String id, HttpServletRequest request) {
        List<Coupon> coupons = new ArrayList<>();
        String header = request.getHeader("receipt");
        if (header == null || header.length() == 0)
            return JsonR.createFail("未登录!");
        try {
            if ((userMapper.getCoupon(id) == null || userMapper.getCoupon(id).size() == 0))
                return JsonR.createFail("无优惠券");
            return JsonR.createSuccess(userMapper.getCoupon(id));
        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }
    }

    @RequestMapping("/updateUser")
    public JsonR updateUser(String id, String password, String iconUrl, String nickname, HttpServletRequest
            request) {
        try {
            String header=request.getHeader("receipt");
            if (header == null || header.length() == 0)
                return JsonR.createFail("未登录!");
            userMapper.updateUser(id, password, iconUrl, nickname);
            return JsonR.createSuccess();

        } catch (Exception e) {
            return JsonR.createFail(e.getMessage());
        }

    }

}
